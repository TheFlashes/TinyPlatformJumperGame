package com.kubaja.tpjg.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class Platform(texture: Texture, world: World) : AbstractActor(texture) {

    init {
        val polygonCords = arrayOf(Vector2(-3f, -0.5f), Vector2(3f, -0.5f), Vector2(3f, 0.5f), Vector2(-3f, 0.5f))
        val polygonShape = PolygonShape()
        polygonShape.set(polygonCords)

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.StaticBody
        bodyDef.position.set(0f, -3f)

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