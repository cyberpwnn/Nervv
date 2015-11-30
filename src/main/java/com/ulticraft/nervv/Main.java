package com.ulticraft.nervv;

public class Main
{
	public static void main(String[] arg)
	{
		o("Starting NerveSystem with 16 nerves");
		
		NerveSystem ns = new NerveSystem(256);
		ns.start("Life");
	}
	
	public static void o(String s)
	{
		System.out.println(s);
	}
}