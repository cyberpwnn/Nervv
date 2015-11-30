package com.ulticraft.nervv;

import java.util.ArrayList;

public class NerveSystem
{
	private ArrayList<Nerve> nerves;
	private InformationBank bank;
	private boolean running;
	
	public NerveSystem(int nerveCount)
	{
		bank = new InformationBank();
		
		if(nerveCount < 1)
		{
			nerveCount = 4;
		}
		
		this.nerves = new ArrayList<Nerve>();
		
		for(int i = 0; i < nerveCount; i++)
		{
			nerves.add(new Nerve(this));
		}
	}
	
	public void start(String start)
	{
		running = true;
		
		Artifact a = new Artifact(start);
		bank.add(a);
		
		while(running)
		{
			String k = bank.getUndocumentedKey();
			
			for(Nerve i : nerves)
			{
				if(i.getCurrentKey() != null && i.getCurrentKey().equals(k))
				{
					break;
				}
			}
			
			try
			{
				search(k);
			}
			
			catch(Exception e)
			{
				
			}
		}
	}
	
	public void search(String q)
	{
		for(Nerve i : nerves)
		{
			if(!i.isLocked())
			{
				if(bank.isDocumented(q))
				{
					return;
				}
				
				i.fire(q);
				return;
			}
		}
	}
	
	public InformationBank getBank()
	{
		return bank;
	}
	
	public void stop()
	{
		running = false;
	}
}
