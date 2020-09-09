package com.kubaja.tpjg.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.kubaja.tpjg.TinyCore

class LoadingScreen(core: TinyCore) : AbstractScreen(core) {

    init {
        tinyCore.manager = AssetManager()
        tinyCore.manager.load("hero.png", Texture::class.java)
        tinyCore.manager.load("badlogic.jpg", Texture::class.java)
        tinyCore.manager.load("wood_platform.png", Texture::class.java)
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.app.log("Assets loading", tinyCore.manager.progress.toString())
        if (tinyCore.manager.update()) {
            dispose()
            tinyCore.screen = GameplayScreen(tinyCore)
        }
    }
}