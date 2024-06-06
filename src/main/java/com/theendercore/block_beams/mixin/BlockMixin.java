package com.theendercore.block_beams.mixin;

import com.theendercore.block_beams.BlockBeams;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.theendercore.block_beams.BlockBeams.getId;


@Mixin(Block.class)
public class BlockMixin {
	@Inject(at = @At("TAIL"), method = "randomDisplayTick")
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
		var beams = BlockBeams.config().getConfig().getBlockBeams();
		var key = getId(state.getBlock()).toString();
		if (beams.containsKey(key) && BlockBeams.canRender(world, pos)) {
			BlockBeams.beam(pos, key);
		}
	}
}
