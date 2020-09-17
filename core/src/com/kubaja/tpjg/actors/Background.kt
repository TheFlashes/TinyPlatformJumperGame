package com.kubaja.tpjg.actors

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor

class Background(manager: AssetManager) : Actor() {

    private val bgs = arrayOf("background1.png", "background2.png")
    private val images: Array<Image>
    var heroY = 0f

    private class Image(val texture: Texture) : Rectangle() {
        init {
            val textureAspectRatio = texture.width.toFloat() / texture.height.toFloat()
            width = (texture.width.toFloat() * (1.0f/16.0f)) + 0.025f
            height = width / textureAspectRatio
            x -= width / 2
        }
    }

    init {
        images = Array(bgs.size) {i -> Image(manager.get(bgs[i], Texture::class.java)) }
    }

    override fun act(delta: Float) {
        super.act(delta)

    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (batch == null) return

        for (imageIndex in images.indices) {
            images[imageIndex].y = (heroY / 1.20f - images[0].height / 2) + (imageIndex * images[0].height)
            batch.draw(images[imageIndex].texture, images[imageIndex].x, images[imageIndex].y, images[imageIndex].width, images[imageIndex].height)
        }
    }

}