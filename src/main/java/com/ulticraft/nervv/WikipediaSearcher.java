package com.ulticraft.nervv;

public class WikipediaSearcher
{
	public static String investigate(String query)
	{
		return LangUtils.stripArtifacts(LangUtils.htmlToText(LangUtils.getContext("https://en.wikipedia.org/wiki/" + query.replace(' ', '_').toLowerCase().trim())));
	}
}
