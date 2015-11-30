package com.ulticraft.nervv;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Main
{
	public static void main(String[] arg)
	{
		InputStream modelIn = Main.class.getClassLoader().getResourceAsStream("en-sent.bin");
		SentenceModel model = null;
		
		try
		{
			model = new SentenceModel(modelIn);
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
		
		SentenceDetectorME sd = new SentenceDetectorME(model);
		String[] ss = sd.sentDetect("This is a test, in which i will do. This is the second sentence. This is the third. Fourth sentence here. Fifth or so.");
		
		for(String i : ss)
		{
			System.out.println(i);
		}
	}
}
