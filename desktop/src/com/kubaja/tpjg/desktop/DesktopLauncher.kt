package com.kubaja.tpjg.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.kubaja.tpjg.TinyCore

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setWindowSizeLimits(405, 720, 405, 720)
        Lwjgl3Application(TinyCore(), config)
    }
}