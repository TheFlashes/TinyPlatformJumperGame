package com.kubaja.tpjg.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kubaja.tpjg.TinyCore

abstract class AbstractScreen(protected val tinyCore: TinyCore) : Screen {

    protected val extendViewPort = ExtendViewport(TinyCore.WORLD_WIDTH, TinyCore.WORLD_HEIGHT, TinyCore.WORLD_WIDTH, TinyCore.WORLD_HEIGHT * 1.15625f)

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.50f, 0.83f, 0.98f, 0f);
        //Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }
}