package com.ulticraft.nervv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class InformationBank
{
	private ArrayList<Artifact> artifacts;
	
	public InformationBank()
	{
		this.artifacts = new ArrayList<Artifact>();
	}
	
	public boolean has(String t)
	{
		for(Artifact a : artifacts)
		{
			if(t.toLowerCase().equals(a.getName().toLowerCase()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void add(Artifact a)
	{
		if(artifacts.contains(a))
		{
			return;
		}
		
		artifacts.add(a);
		
		if(artifacts.size() > 512)
		{
			artifacts.remove(0);
		}
	}
	
	public void add(Artifact a, Artifact... referencedBy)
	{
		for(Artifact i : artifacts)
		{
			for(Artifact j : referencedBy)
			{
				if(i.equals(j))
				{
					i.getLinks().add(a.getId());
				}
			}
		}
		
		add(a);
	}
	
	public int getArtifactSize()
	{
		return artifacts.size();
	}
	
	public boolean isDocumented(String t)
	{
		for(Artifact i : artifacts)
		{
			if(i.getName().toLowerCase().equals(t.toLowerCase()))
			{
				if(i.isLinked())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String getUndocumentedKey()
	{
		Collections.shuffle(artifacts);
		
		for(Artifact i : artifacts)
		{
			if(!i.isLinked())
			{
				if(i.getName().length() < 4)
				{
					continue;
				}
				
				System.out.print("What is " + i.getName() + "?         ");
				
				return i.getName();
			}
		}
		
		return null;
	}
}
