package com.raphydaphy.crochet;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Crochet implements ModInitializer 
{
	public static final String DOMAIN = "crochet";
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() 
	{
		System.out.println("Hello World!");
	}

	public static Logger getLogger()
	{
		return LOGGER;
	}
}
