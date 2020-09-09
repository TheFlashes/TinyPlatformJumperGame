package com.kubaja.tpjg.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.kubaja.tpjg.TinyCore

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.height = 720
        config.width = 405
        LwjglApplication(TinyCore(), config)
    }
}