package com.kubaja.tpjg.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
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
    var isInJump = true

    var jumpQueue = false
    var jumpQueueSteps = 0
    var jumpStartYPos = 0
    var jumpHighestYPos = 0

    var CONTROL_LEFT = false
    var CONTROL_RIGHT = false
    var CONTROL_JUMP = false

    private val SLOW_DOWN_X_VELOCITY = 10f

    init {
        val polygonBodyCords = arrayOf(Vector2(-0.5f, -0.434f), Vector2(0.5f, -0.434f), Vector2(0.5f, 0.5f), Vector2(-0.5f, 0.5f))
        val polygonBodyShape = PolygonShape()
        polygonBodyShape.set(polygonBodyCords)

        val fixtureBodyDef = FixtureDef()
        fixtureBodyDef.shape = polygonBodyShape
        fixtureBodyDef.density = 0.5f
        fixtureBodyDef.friction = 0.0f
        fixtureBodyDef.restitution = 0.0f
        fixtureBodyDef.isSensor = true

        val polygonFootCords = arrayOf(Vector2(-0.5f, -0.44f), Vector2(0.5f, -0.44f), Vector2(0.5f, -0.435f), Vector2(-0.5f, -0.435f))
        val polygonFootShape = PolygonShape()
        polygonFootShape.set(polygonFootCords)

        val fixtureFootDef = FixtureDef()
        fixtureFootDef.shape = polygonFootShape
        fixtureFootDef.density = 0.5f
        fixtureFootDef.friction = 0.0f
        fixtureFootDef.restitution = 0.0f

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.fixedRotation = true
        bodyDef.position.set(0f, 1f)

        body = world.createBody(bodyDef)
        body.createFixture(fixtureBodyDef)
        body.createFixture(fixtureFootDef)
        body.userData = "hero"

        polygonBodyShape.dispose()
        polygonFootShape.dispose()
    }

    override fun act(delta: Float) {
        super.act(delta)

        if (CONTROL_LEFT) {
            if (body.linearVelocity.x > -7) body.applyForceToCenter(-SPEED_UP_X_VELOCITY, 0.0f, true)
            else body.applyForceToCenter(-15.0f, 0.0f, true)
        }
        if (CONTROL_RIGHT) {
            if (body.linearVelocity.x < 7) body.applyForceToCenter(SPEED_UP_X_VELOCITY, 0.0f, true);
            else body.applyForceToCenter(15.0f, 0.0f, true)
        }
        if (jumpQueue && jumpQueueSteps > 0 && !isInJump && body.linearVelocity.y == 0.0f) {
            jump()
            jumpQueue = false
        } else if (CONTROL_JUMP) {
            if (!isInJump && body.linearVelocity.y == 0.0f) jump()
            else if (!jumpQueue) {
                jumpQueue = true
                jumpQueueSteps = 10
            }
        }
        if (jumpQueue) {
            jumpQueueSteps--
            if (jumpQueueSteps == 0) jumpQueue = false
        }

        CONTROL_LEFT = false
        CONTROL_RIGHT = false
        CONTROL_JUMP = false

        if (body.linearVelocity.x > -1.0f && body.linearVelocity.x < 1.0f) { body.setLinearVelocity(0.0f, body.linearVelocity.y) }
        if (body.linearVelocity.x > 0) { body.applyForceToCenter(-SLOW_DOWN_X_VELOCITY, 0.0f,true) }
        if (body.linearVelocity.x < 0) { body.applyForceToCenter(SLOW_DOWN_X_VELOCITY, 0.0f,true) }
        if (body.linearVelocity.x > MAX_VELOCITY) { body.setLinearVelocity(MAX_VELOCITY, body.linearVelocity.y) }
        if (body.linearVelocity.x < -MAX_VELOCITY) { body.setLinearVelocity(-MAX_VELOCITY, body.linearVelocity.y) }
    }

    override fun draw (batch: Batch?, parentAlpha: Float) {
        if (batch == null) return
        batch.draw(texture, x, y, width/2.0f, height/2.0f, width, height, 1f, 1f, rotation, 0, 0, texture.width, texture.height, false, false)
    }

    private fun jump() {
        jumpStartYPos = y.toInt()
        body.applyForceToCenter(0f, 750.0f,true)
    }
}