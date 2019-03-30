package com.raphydaphy.crochet.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
public class PlayerData
{
	public static CompoundTag get(PlayerEntity player, String mod)
	{
		return ((DataHolder)player).getAdditionalData(mod);
	}

	public static void markDirty(PlayerEntity player)
	{
		 ((DataHolder)player).markAdditionalDataDirty();
	}
}
