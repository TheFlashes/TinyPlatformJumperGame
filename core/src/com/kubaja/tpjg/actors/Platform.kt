package com.kubaja.tpjg.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class Platform(texture: Texture, world: World) : AbstractActor(texture) {

    var isLast = false

    init {
        val polygonCords = arrayOf(Vector2(-1.5f, 0.2f), Vector2(1.5f, 0.2f), Vector2(1.5f, 0.25f), Vector2(-1.5f, 0.25f))
        val polygonShape = PolygonShape()
        polygonShape.set(polygonCords)

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.StaticBody
        bodyDef.position.set(0f, 0f)

        body = world.createBody(bodyDef)

        val fixtureDef = FixtureDef()
        fixtureDef.shape = polygonShape
        fixtureDef.density = 0.5f
        fixtureDef.friction = 0.0f
        fixtureDef.restitution = 0.0f // Make it bounce a little bit

        body.createFixture(fixtureDef)
        body.userData = "platform"

        polygonShape.dispose()
    }

    override fun act(delta: Float) {
        super.act(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (batch == null) return
        batch.draw(texture, x, y, width/2.0f, height/2.0f, width, height, 1f, 1f, rotation, 0, 0, texture.width, texture.height, false, false)
    }
}