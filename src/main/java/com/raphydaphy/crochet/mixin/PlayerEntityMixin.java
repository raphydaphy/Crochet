package com.raphydaphy.crochet.mixin;


import com.raphydaphy.crochet.data.DataHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements DataHolder
{
	private static final String ADDITIONAL_DATA_TAG = "PlayerData";
	private CompoundTag additionalData = new CompoundTag();

	@Inject(at = @At("TAIL"), method = "readCustomDataFromTag")
	private void readCustomDataFromTag(CompoundTag tag, CallbackInfo info)
	{
		additionalData = tag.getCompound(ADDITIONAL_DATA_TAG);
	}

	@Inject(at = @At("TAIL"), method = "writeCustomDataToTag")
	private void writeCustomDataToTag(CompoundTag tag, CallbackInfo info)
	{
		tag.put(ADDITIONAL_DATA_TAG, additionalData);
	}

	@Override
	public CompoundTag getAdditionalData(String mod)
	{
		if (!additionalData.containsKey(mod))
		{
			additionalData.put(mod, new CompoundTag());
			markAdditionalDataDirty();
		}
		return (CompoundTag)additionalData.getTag(mod);
	}

	@Override
	public void setAllAdditionalData(CompoundTag tag)
	{
		this.additionalData = tag;
	}
}