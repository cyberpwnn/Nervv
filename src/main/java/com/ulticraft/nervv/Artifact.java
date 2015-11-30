package com.ulticraft.nervv;

import java.util.ArrayList;

public class Artifact
{
	private String name;
	private ArrayList<String> information;
	private long id;
	private ArrayList<Long> links;
	private boolean linked;
	private static long idx = 0;
	
	public Artifact(String name)
	{
		idx++;
		
		this.name = name;
		this.information = new ArrayList<String>();
		this.id = idx;
		this.linked = false;
		this.links = new ArrayList<Long>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<String> getInformation()
	{
		return information;
	}

	public void setInformation(ArrayList<String> information)
	{
		this.information = information;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public ArrayList<Long> getLinks()
	{
		return links;
	}

	public void setLinks(ArrayList<Long> links)
	{
		this.links = links;
	}

	public static long getIdx()
	{
		return idx;
	}

	public static void setIdx(long idx)
	{
		Artifact.idx = idx;
	}

	public boolean isLinked()
	{
		return linked;
	}

	public void setLinked(boolean linked)
	{
		this.linked = linked;
	}
}
