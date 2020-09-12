package com.kubaja.tpjg.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.kubaja.tpjg.TinyCore
import com.kubaja.tpjg.stages.GameplayStage
import com.kubaja.tpjg.stages.GameplayUIStage
import java.util.Collections.copy
import kotlin.math.roundToInt


class GameplayScreen(core: TinyCore) : AbstractScreen(core) {

    private lateinit var gameplayStage: GameplayStage
    private lateinit var gameplayUIStage: GameplayUIStage

    override fun show() {
        initFont()
        gameplayStage = GameplayStage(extendViewPort, tinyCore.manager)
        gameplayUIStage = GameplayUIStage(tinyCore.manager)
    }

    override fun render(delta: Float) {
        super.render(delta)

        if (gameplayUIStage.leftButton.isPressed || Gdx.input.isKeyPressed(Input.Keys.A)) { gameplayStage.hero.CONTROL_LEFT = true }
        if (gameplayUIStage.rightButton.isPressed ||Gdx.input.isKeyPressed(Input.Keys.D)) gameplayStage.hero.CONTROL_RIGHT = true
        if (gameplayUIStage.jumpButton.isPressed ||Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) gameplayStage.hero.CONTROL_JUMP = true

        gameplayStage.act(delta)
        gameplayStage.draw()
        gameplayUIStage.act(delta)
        gameplayUIStage.draw()
        //gameplayStage.debugRenderer.render(gameplayStage.world, gameplayStage.camera.combined)

        batch.begin()
            pixelFont.draw(batch, "x: " + gameplayStage.hero.x.roundToInt() + "\ny: " + gameplayStage.hero.y.roundToInt() + "\nFPS: " + Gdx.graphics.framesPerSecond, 5f, Gdx.graphics.height.toFloat() - 5f)
        //batch.draw(tinyCore.manager.get("hero.png", Texture::class.java), 0f, 0f)
        batch.end()
    }
}