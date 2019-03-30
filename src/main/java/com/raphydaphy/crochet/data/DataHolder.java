package com.raphydaphy.crochet.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public interface DataHolder
{
	/**
	 * Returns the players data for a specific mod
	 * @param mod The modid which the data is stored by
	 * @return The data tag
	 */
	CompoundTag getAdditionalData(String mod);

	/**
	 * @return the players entire data tag. You should use getAdditionalData(mod).
	 */
	CompoundTag getAllAdditionalData();

	/**
	 * Don't use this method unless you know what you are doing
	 * @param tag A new data tag for the player
	 */
	void setAllAdditionalData(CompoundTag tag);

	/**
	 * If called on the server-side, this will ensure that the updated data tag is sent to the client
	 */
	default void markAdditionalDataDirty()
	{
	}
}
