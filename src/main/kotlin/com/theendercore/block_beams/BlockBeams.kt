package com.theendercore.block_beams


import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.particle.DustParticleEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Color

object BlockBeams {
    const val MODID = "block_beams"

    val log: Logger = LoggerFactory.getLogger(MODID)
    fun id(path: String): Identifier = Identifier.of(MODID, path)
    fun parseId(path: String): Identifier = Identifier.parse(path)
    fun getId(block: Block): Identifier = Registries.BLOCK.getId(block)
    fun config(): Config = Config.INSTANCE

    val PASSABLE_BLOCKS: TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("passable_blocks"))


    @Suppress("unused")
    fun onInitialize() {
        log.info("Entering the Blocktrix...")
        config().load()
        Keybinding.init()
    }

    @JvmStatic
    fun beamingTime(state: BlockState, world: World, pos: BlockPos) {
        val beams = config().config.blockBeams.mapKeys { (key, _) -> parseId(key) }
        val color = beams[getId(state.block)]
        if (color != null && canRender(world, pos)) {
            renderBeam(pos, color)
        }
    }

    private fun renderBeam(pos: BlockPos, color: String) {
        for (i in 0 until 12) {
            try {
                MinecraftClient.getInstance().particleManager.addParticle(
                    DustParticleEffect(Vec3d.unpackRgb(Color.decode(color).rgb).toVector3f(), 1f),
                    pos.x.floorDiv(1) + 0.5,
                    (pos.y + 1.2) + (0.25 * i),
                    pos.z.floorDiv(1) + 0.5,
                    0.0,
                    10.0,
                    0.0
                )
            } catch (throwable: Throwable) {
                log.warn("Could not spawn particle effect")
            }
        }

    }

    private fun canRender(world: World, pos: BlockPos): Boolean =
        (1..3).map { isPassable(world, pos, it) }.all { it }

    private fun isPassable(world: World, pos: BlockPos, dist: Int): Boolean {
        val c = config().config
        val state = world.getBlockState(pos.up(dist))

        return when (c.blockCheckType) {
            BlockCheckType.SERVER_ONLY -> state.isIn(PASSABLE_BLOCKS)
            BlockCheckType.CLIENT_ONLY -> clientOnlyCheck(state, c)
            BlockCheckType.SERVER_THEN_CLIENT -> state.isIn(PASSABLE_BLOCKS)
        }

    }

    private fun clientOnlyCheck(state: BlockState, c: ConfigData): Boolean {
        var value = false
        for (block in c.clientTag) {
            value = block.startsWith("#") && state.isIn(TagKey.of(RegistryKeys.BLOCK, parseId(block.removePrefix("#"))))
                    ||
                    !block.startsWith("#") && getId(state.block) == parseId(block)
            if (value) break
        }
        return value
    }
}