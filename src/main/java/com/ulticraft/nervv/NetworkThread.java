package com.ulticraft.nervv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkThread extends Thread
{
	private String uri;
	private InformationBank b;
	
	public NetworkThread(String uri, InformationBank b)
	{
		this.uri = uri;
		this.b = b;
	}
	
	public void start()
	{
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		
		try
		{
			url = new URL("https://en.wikipedia.org/wiki/" + uri.replace(' ', '_').toLowerCase().trim());
			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			String text = "";
			
			while ((line = br.readLine()) != null)
			{
				text = text + line + " ";
			}
			
			String d = LangUtils.stripArtifacts(LangUtils.htmlToText(text));
			String[] st = LangUtils.getSentences(d);
			String[][] tk = new String[st.length][];
			int tkl = 0;
			
			for(int i = 0; i < st.length; i++)
			{
				tk[i] = LangUtils.getTokens(st[i]);
				tkl += tk[i].length;
			}
			
			Artifact a = new Artifact(uri);
			a.setLinked(true);
			b.add(a);
			
			for(String[] i : tk)
			{
				for(String j : i)
				{
					Artifact aa = new Artifact(j);
					b.add(aa, a);
				}
			}
			
			System.out.println(uri + " Relates to " + tkl + " Artifacts");
			
			this.notifyAll();
			this.interrupt();
			
			return;
		}
		
		catch (MalformedURLException mue)
		{

		}
		
		catch (IOException ioe)
		{
			
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
	}
}
