package com.kubaja.tpjg

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.kubaja.tpjg.screens.LoadingScreen

class TinyCore : Game() {

    companion object {
        const val PIXEL_MULTIPLER: Float = 2f
        const val WORLD_HEIGHT = 21.33333f //3.5  9  16  28.5  21.33333
        const val WORLD_WIDTH = 12f       //2  5  9   16  12
    }

    lateinit var manager: AssetManager

    override fun create() {
        setScreen(LoadingScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        getScreen().dispose()
    }
}