package com.kubaja.tpjg.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.kubaja.tpjg.TinyCore
import com.kubaja.tpjg.stages.GameplayStage
import kotlin.math.roundToInt


class GameplayScreen(core: TinyCore) : AbstractScreen(core) {

    private lateinit var gameplayStage: GameplayStage

    override fun show() {
        initFont()
        gameplayStage = GameplayStage(extendViewPort, tinyCore.manager)
    }

    override fun render(delta: Float) {
        super.render(delta)

        gameplayStage.act(delta)
        gameplayStage.draw()
        gameplayStage.debugRenderer.render(gameplayStage.world, gameplayStage.camera.combined)

        batch.begin()
            pixelFont.draw(batch, "x: " + gameplayStage.hero.x.roundToInt() + "\ny: " + gameplayStage.hero.y.roundToInt(), 5f, Gdx.graphics.height.toFloat() - 5f)
        //batch.draw(tinyCore.manager.get("hero.png", Texture::class.java), 0f, 0f)
        batch.end()
    }

}