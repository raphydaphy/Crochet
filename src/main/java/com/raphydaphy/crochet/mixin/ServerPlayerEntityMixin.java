package com.raphydaphy.crochet.mixin;

import com.raphydaphy.crochet.data.DataHolder;
import com.raphydaphy.crochet.network.PacketHandler;
import com.raphydaphy.crochet.network.PlayerDataUpdatePacket;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements DataHolder
{
	private boolean additionalDataNeedsSync = true;

	@Inject(at = @At("HEAD"), method = "method_14203")
	private void onPlayerClone(ServerPlayerEntity playerEntity, boolean keepEverything, CallbackInfo info) // copyFrom
	{
		this.setAllAdditionalData(((DataHolder) playerEntity).getAllAdditionalData());
		markAdditionalDataDirty();
	}

	@Inject(at = @At("TAIL"), method = "method_14226")
	private void syncAdditionalData(CallbackInfo info)
	{
		if (additionalDataNeedsSync)
		{
			additionalDataNeedsSync = false;
			PacketHandler.sendToClient(new PlayerDataUpdatePacket(this.getAllAdditionalData()), (ServerPlayerEntity) (Object) this);
		}
	}

	@Override
	public void markAdditionalDataDirty()
	{
		additionalDataNeedsSync = true;
	}
}