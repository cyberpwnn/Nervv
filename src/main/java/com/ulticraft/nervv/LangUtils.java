package com.ulticraft.nervv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class LangUtils
{
	private static boolean loaded = false;
	private static SentenceDetectorME sd = null;
	private static Tokenizer tk = null;
	
	public static String[] getSentences(String text)
	{
		check();
		return sd.sentDetect(text);
	}
	
	public static String[] getTokens(String text)
	{
		check();
		return tk.tokenize(text);
	}
	
	public static String stripArtifacts(String text)
	{
		text = StringUtils.remove(text, "<>");
		text = StringUtils.remove(text, "^");
		text = StringUtils.remove(text, "*");
		text = text.replaceAll("\\r|\\n", " ");
		return text;
	}
	
	public static String getContext(String uri)
	{
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		
		try
		{
			url = new URL(uri);
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			String text = "";
			
			while ((line = br.readLine()) != null)
			{
				text = text + line + " ";
			}
			
			return stripArtifacts(htmlToText(text));
		}
		
		catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		finally
		{
			try
			{
				if (is != null)
				{
					is.close();
				}
			}
			
			catch (IOException ioe)
			{
				// nothing to see here
			}
		}
		
		return null;
	}
	
	public static String htmlToText(String html)
	{
		try
		{
			return new HtmlToPlainText().getPlainText(Jsoup.parse(html));
		}
		
		catch(Exception e)
		{
			return "server error";
		}
	}
	
	private static void check()
	{
		if (loaded)
		{
			return;
		}
		
		else
		{
			loaded = true;
		}
		
		InputStream modelIn = Main.class.getClassLoader().getResourceAsStream("en-sent.bin");
		SentenceModel smodel = null;
		TokenizerModel tmodel = null;
		
		try
		{
			smodel = new SentenceModel(modelIn);
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			if (modelIn != null)
			{
				try
				{
					modelIn.close();
				}
				
				catch (IOException e)
				{
				
				}
			}
		}
		
		modelIn = Main.class.getClassLoader().getResourceAsStream("en-token.bin");
		
		try
		{
			tmodel = new TokenizerModel(modelIn);
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			if (modelIn != null)
			{
				try
				{
					modelIn.close();
				}
				
				catch (IOException e)
				{
				
				}
			}
		}
		
		sd = new SentenceDetectorME(smodel);
		tk = new TokenizerME(tmodel);
	}
}
