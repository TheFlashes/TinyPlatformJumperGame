package com.kubaja.tpjg.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Actor

class Hero(texture: Texture, world: World) : AbstractActor(texture) {

    val MAX_VELOCITY = 15f
    val SPEED_UP_X_VELOCITY = 50f

    var isTouchingPlatform = false
    var switchA = false
    var jumpEnd = Vector2(0f, 0f)

    private val SLOW_DOWN_X_VELOCITY = 5f

    init {
        val polygonCords = arrayOf(Vector2(-0.5f, -0.44f), Vector2(0.5f, -0.44f), Vector2(0.5f, 0.5f), Vector2(-0.5f, 0.5f))
        val polygonShape = PolygonShape()
        polygonShape.set(polygonCords)

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.fixedRotation = true
        bodyDef.position.set(0f, 0f)

        body = world.createBody(bodyDef)

        val fixtureDef = FixtureDef()
        fixtureDef.shape = polygonShape
        fixtureDef.density = 0.5f
        fixtureDef.friction = 0.0f
        fixtureDef.restitution = 0.0f

        body.createFixture(fixtureDef)
        body.userData = "hero"

        polygonShape.dispose()
    }

    override fun act(delta: Float) {
        super.act(delta)

        body.fixtureList.get(0).isSensor = body.linearVelocity.y > 0.01

        if (body.linearVelocity.x > 0) {
            body.applyForceToCenter(-SLOW_DOWN_X_VELOCITY, 0.0f,true)
        }
        if (body.linearVelocity.x < 0) {
            body.applyForceToCenter(SLOW_DOWN_X_VELOCITY, 0.0f,true)
        }

        if (body.linearVelocity.x > -1 && body.linearVelocity.x < 1) body.setLinearVelocity(0f, body.linearVelocity.y)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if (batch == null) return
        batch.draw(texture, x, y, width/2.0f, height/2.0f, width, height, 1f, 1f, rotation, 0, 0, texture.width, texture.height, false, false)
    }
}