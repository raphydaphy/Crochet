package com.raphydaphy.crochet;

import com.raphydaphy.crochet.network.PlayerDataUpdatePacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

public class CrochetClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		ClientSidePacketRegistry.INSTANCE.register(PlayerDataUpdatePacket.ID, new PlayerDataUpdatePacket.Handler());
	}
}
