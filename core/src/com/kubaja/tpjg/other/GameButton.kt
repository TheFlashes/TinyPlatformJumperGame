package com.kubaja.tpjg.other

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor

class GameButton(x: Float, y: Float, width: Float, height: Float,
                 private val arrow: Texture,
                 private val arrowRotate: Float) : Actor() {

    private val background: Texture

    private val arrowWidth: Float
    private val arrowHeight: Float

    var isDown = false
    private var justPressedRead = false
    private var alreadyPressed = false
    private var alreadyUnPressed = true

    init {
        this.x = x
        this.y = y
        this.width = width
        this.height = height

        val pixMap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixMap.setColor(1f, 1f, 1f, 1f)
        pixMap.fill()
        background = Texture(pixMap)

        val textureAspectRatio = arrow.width.toFloat() / arrow.height.toFloat()
        arrowWidth = Gdx.graphics.width / 8f
        arrowHeight = arrowWidth / textureAspectRatio
    }

    override fun act(delta: Float) {
        super.act(delta)
        val touchX = arrayOf(Gdx.input.getX(0), Gdx.input.getX(1))
        val touchY = arrayOf(-Gdx.input.getY(0) + Gdx.graphics.height, -Gdx.input.getY(1) + Gdx.graphics.height)

        isDown = ((Gdx.input.isTouched(0) && touchX[0] > x && touchX[0] < x + width && touchX[0] > y && touchY[0] < y + height) ||
                  (Gdx.input.isTouched(1) && touchX[1] > x && touchX[1] < x + width && touchX[1] > y && touchY[1] < y + height))
        if (!isDown) justPressedRead = false

        alreadyPressed = if (isDown) {
            if (!alreadyPressed) Gdx.input.vibrate(10)
            true
        } else false

        alreadyUnPressed = if (!isDown) {
            if (!alreadyUnPressed) Gdx.input.vibrate(2)
            true
        } else false
    }

    fun justPressed() : Boolean {
        return if (isDown && !justPressedRead) {
            justPressedRead = true
            true
        } else false
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (batch == null) return

        if (isDown) batch.setColor(1f, 1f, 1f, 0.05f)
        else batch.setColor(1f, 1f, 1f, 0f)
        batch.draw(background, x, y, width, height)
        if (isDown) batch.setColor(1f, 1f, 1f, 0.5f)
        else batch.setColor(1f, 1f, 1f, .1f)
        batch.draw(arrow, x + width / 2 - arrowWidth / 2, y + height / 2 - arrowHeight / 2, arrowWidth/2.0f, arrowHeight/2.0f, arrowWidth, arrowHeight, 1f, 1f, arrowRotate, 0, 0, arrow.width, arrow.height, false, false)
        batch.setColor(1f, 1f, 1f, 1f);
    }
}