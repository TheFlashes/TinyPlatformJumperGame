package com.kubaja.tpjg.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Actor
import com.kubaja.tpjg.TinyCore


abstract class AbstractActor(protected val texture: Texture) : Actor() {

    protected var textureAspectRatio : Float = 0f
    lateinit var body: Body

    init {
        textureAspectRatio = texture.width.toFloat() / texture.height.toFloat()
        width = (texture.width.toFloat() * (1.0f/16.0f)) + 0.025f
        height = width / textureAspectRatio
    }

    override fun act(delta: Float) {
        super.act(delta)

        x = body.position.x - (width / 2)
        y = body.position.y - (height / 2)

        rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
    }
}