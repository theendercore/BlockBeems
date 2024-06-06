package com.theendercore.block_beams.mixin;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({WetSpongeBlock.class, RedstoneOreBlock.class, CryingObsidianBlock.class, PointedDripstoneBlock.class, AbstractCandleBlock.class, LeavesBlock.class, RespawnAnchorBlock.class})
public class RandomDisplayTickOverrideMixin extends Block {
	public RandomDisplayTickOverrideMixin(Settings settings) {
		super(settings);
	}

	@Inject(at = @At("TAIL"), method = "randomDisplayTick")
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
		super.randomDisplayTick(state, world, pos, random);
	}
}
