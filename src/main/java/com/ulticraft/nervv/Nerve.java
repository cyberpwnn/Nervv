package com.ulticraft.nervv;

import java.lang.Thread.State;

public class Nerve
{
	private NetworkThread t;
	private NerveSystem s;
	private String currentKey;
	
	public Nerve(NerveSystem s)
	{
		this.s = s;
	}
	
	public void fire(String dest)
	{
		t = new NetworkThread(dest, s.getBank());
		currentKey = dest;
		t.start();
	}
	
	public boolean isLocked()
	{
		if(t == null)
		{
			return false;
		}
		
		if(t.getState().equals(State.RUNNABLE))
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public String getCurrentKey()
	{
		return currentKey;
	}
}
