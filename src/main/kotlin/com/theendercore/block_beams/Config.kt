package com.theendercore.block_beams

import com.theendercore.block_beams.BlockBeams.MODID
import com.theendercore.block_beams.BlockBeams.log
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.fabricmc.loader.api.FabricLoader
import java.io.File
import java.io.FileReader
import java.io.FileWriter


class Config {
    private val configFile: File = FabricLoader.getInstance().configDir.resolve("$MODID.json").toFile()
    var config: ConfigData = ConfigData()

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json { prettyPrint = true; prettyPrintIndent = "\t" }

    fun load() {
        try {
            val stringData = FileReader(configFile).use { it.readText() }
            config = json.decodeFromString(stringData)
        } catch (e: Exception) {
            log.info("No config Found! Or config is Broked! Making a new one.")
            log.warn(e.toString())
            save(ConfigData())
        }
    }

    private fun save(config: ConfigData) {
        try {
            FileWriter(configFile).use { it.write(json.encodeToString(config)) }
        } catch (e: Exception) {
            log.error("Could not save config!!!")
        }
    }

    companion object {
        val INSTANCE = Config()
    }
}


@Serializable
data class ConfigData(
    val version: Int,
    val comment: String,
    val blockBeams: Map<String, String>,
    val commentTwo: String,
    val blockCheckType: BlockCheckType,
    val commentThree: String,
    val clientTag: List<String>,
) {
    constructor() : this(
        1,
        "A List of Blocks and the beam colors as Integers",
        mapOf(
            "nether_quartz_ore" to "#ffffff",
            "coal_ore" to "#000000",
            "deepslate_coal_ore" to "#000000",
            "copper_ore" to "#d16d5a",
            "deepslate_copper_ore" to "#d16d5a",
            "lapis_ore" to "#426ad1",
            "deepslate_lapis_ore" to "#426ad1",
            "iron_ore" to "#e3c0aa",
            "deepslate_iron_ore" to "#e3c0aa",
            "redstone_ore" to "#f21b1b",
            "deepslate_redstone_ore" to "#f21b1b",
            "gold_ore" to "#ebde46",
            "deepslate_gold_ore" to "#ebde46",
            "nether_gold_ore" to "#ebde46",
            "emerald_ore" to "#15b73f",
            "deepslate_emerald_ore" to "#15b73f",
            "minecraft:diamond_ore" to "#1cc2c7",
            "minecraft:deepslate_diamond_ore" to "#1cc2c7"
        ),
        "The way the mod check what blocks can be passed through." +
                " [SERVER_ONLY] - (Block list is gotten from the server only, none cheaty option), " +
                "[CLIENT_ONLY] - (Block list is gotten from the client only, the 'cheaty' option), ",
//                "[SERVER_THEN_CLIENT] - (Block list is gotten from the server and if that fails then client, the  compromise option)",
        BlockCheckType.SERVER_ONLY,
        "The list of blocks that be passed through on the client",
        listOf(
            "air",
            "minecraft:water",
            "minecraft:pointed_dripstone",
            "minecraft:tripwire",
            "lever",
            "lightning_rod",
            "slime_block",
            "honey_block",
            "lantern",
            "soul_lantern",
            "glow_lichen",
            "cobweb",
            "chain",
            "iron_bars",
            "conduit",
            "end_rod",
            "tripwire_hook",
            "ice",
            "torch",
            "wall_torch",
            "soul_torch",
            "soul_wall_torch",
            "redstone_torch",
            "redstone_wall_torch",

            "amethyst_cluster",
            "small_amethyst_bud",
            "medium_amethyst_bud",
            "large_amethyst_bud",

            "glass",
            "white_stained_glass",
            "orange_stained_glass",
            "magenta_stained_glass",
            "light_blue_stained_glass",
            "yellow_stained_glass",
            "lime_stained_glass",
            "pink_stained_glass",
            "gray_stained_glass",
            "light_gray_stained_glass",
            "cyan_stained_glass",
            "purple_stained_glass",
            "blue_stained_glass",
            "brown_stained_glass",
            "green_stained_glass",
            "red_stained_glass",
            "black_stained_glass",

            "glass_pane",
            "black_stained_glass_pane",
            "white_stained_glass_pane",
            "gray_stained_glass_pane",
            "light_gray_stained_glass_pane",
            "blue_stained_glass_pane",
            "light_blue_stained_glass_pane",
            "cyan_stained_glass_pane",
            "lime_stained_glass_pane",
            "green_stained_glass_pane",
            "orange_stained_glass_pane",
            "yellow_stained_glass_pane",
            "red_stained_glass_pane",
            "magenta_stained_glass_pane",
            "brown_stained_glass_pane",
            "purple_stained_glass_pane",
            "pink_stained_glass_pane",

            "#minecraft:leaves",
            "#minecraft:all_signs",
            "#minecraft:banners",
            "#minecraft:buttons",
            "#minecraft:cave_vines",
            "#minecraft:candles",
            "#minecraft:climbable",
            "#minecraft:corals",
            "#minecraft:flower_pots",
            "#minecraft:fences",
            "#minecraft:fence_gates",
            "#minecraft:rails"
        )
    )
}


enum class BlockCheckType {
    SERVER_ONLY,
    CLIENT_ONLY,
    SERVER_THEN_CLIENT
}
