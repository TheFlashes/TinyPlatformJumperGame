package com.kubaja.tpjg.stages

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kubaja.tpjg.other.GameButton

class GameplayUIStage(manager: AssetManager) : Stage() {

    private val BUTTON_WIDTH = width / 4f
    private val BUTTON_HEIGHT = width / 1.75f

    val leftButton = GameButton(0f, 0f, BUTTON_WIDTH, BUTTON_HEIGHT, manager.get("buttonArrow.png", Texture::class.java), 90f)
    val rightButton = GameButton(BUTTON_WIDTH, 0f, BUTTON_WIDTH, BUTTON_HEIGHT, manager.get("buttonArrow.png", Texture::class.java), -90f)
    val jumpButton = GameButton(BUTTON_WIDTH * 2, 0f, BUTTON_WIDTH * 2, BUTTON_HEIGHT, manager.get("buttonArrow.png", Texture::class.java), 0f)

    init {
        addActor(leftButton)
        addActor(rightButton)
        addActor(jumpButton)
    }
}