package com.kubaja.tpjg.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Actor

class Hero(texture: Texture, world: World) : AbstractActor(texture) {

    init {
        val polygonCords = arrayOf(Vector2(-0.5f, -0.5f), Vector2(0.5f, -0.5f), Vector2(0.5f, 0.5f), Vector2(-0.5f, 0.5f))
        val polygonShape = PolygonShape()
        polygonShape.set(polygonCords)

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(0f, 4f)

        body = world.createBody(bodyDef)

        val fixtureDef = FixtureDef()
        fixtureDef.shape = polygonShape
        fixtureDef.density = 0.5f
        fixtureDef.friction = 0.4f
        fixtureDef.restitution = 0.6f // Make it bounce a little bit

        val fixture = body.createFixture(fixtureDef)

        polygonShape.dispose()
    }

    override fun act(delta: Float) {
        super.act(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (batch == null) return
        batch.draw(texture, x, y, width, height)

    }
}