package com.theendercore.block_beams

import com.theendercore.block_beams.BlockBeams.config
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import com.mojang.blaze3d.platform.InputUtil
import net.minecraft.client.option.KeyBind
import org.lwjgl.glfw.GLFW

object Keybinding {
    private val configKey: KeyBind = KeyBindingHelper.registerKeyBinding(
        KeyBind(
            "key.block_beams.config",
            InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN,
            "category.block_beams.generic"
        )
    )

    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { if (configKey.wasPressed()) config().load() })
    }
}