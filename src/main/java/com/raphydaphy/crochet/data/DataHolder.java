package com.raphydaphy.crochet.data;

import net.minecraft.nbt.CompoundTag;

public interface DataHolder
{
	CompoundTag getAdditionalData();

	void setAdditionalData(CompoundTag tag);

	default void markAdditionalDataDirty()
	{
	}
}
