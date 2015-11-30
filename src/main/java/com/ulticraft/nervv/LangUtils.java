package com.ulticraft.nervv;

import java.io.IOException;
import java.io.InputStream;

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
	
	public static String htmlToText(String html)
	{
		return new HtmlToPlainText().getPlainText(Jsoup.parse(html));
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
