package com.raphydaphy.crochet.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

public class PlayerData
{
	public static CompoundTag get(PlayerEntity player)
	{
		return ((DataHolder)player).getAdditionalData();
	}

	public static void markDirty(PlayerEntity player)
	{
		 ((DataHolder)player).markAdditionalDataDirty();
	}
}
