package com.kubaja.tpjg.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kubaja.tpjg.actors.Background
import com.kubaja.tpjg.actors.Hero
import com.kubaja.tpjg.actors.Platform
import kotlin.math.min
import kotlin.random.Random

class GameplayStage(viewport: ExtendViewport, manager: AssetManager) : Stage(viewport) {

    private val distanceBetweenPlatforms = 4
    private var accumulator = 0f
    private val TIME_STEP = 1.0f/60.0f
    var platformIterations = 0

    val world = World(Vector2(0f, -40f), true)
    val debugRenderer = Box2DDebugRenderer()

    val background : Background
    val hero: Hero
    val platforms : Array<Platform>

    init {
        camera.position.set(0f, 0f, 0f)

        background = Background(manager)
        hero = Hero(manager.get("hero.png", Texture::class.java), world)
        platforms = Array(8) { Platform(manager.get("platform2.png", Texture::class.java), world) }


        addActor(background)
        for ((i, p) in platforms.withIndex()) {
            val pX = Random.nextDouble(-3.0, 3.0).toFloat()
            val pY = (i.toFloat() * 4)
            platformIterations++
            p.body.setTransform(pX, pY, 0f)
            if (i == 0) p.isLast = true
            addActor(p)
        }
        platforms.first().body.setTransform(0f, 0f, 0f)
        addActor(hero)

        world.setContactListener(object : ContactListener {
            override fun beginContact(contact: Contact?) {
                if (contact == null) return
                if ((contact.fixtureA.body.userData == "hero" && contact.fixtureB.body.userData == "platform") || (contact.fixtureA.body.userData == "platform" && contact.fixtureB.body.userData == "hero"))
                    hero.isTouchingPlatform = true
            }

            override fun endContact(contact: Contact?) {
                if (contact == null) return
                if ((contact.fixtureA.body.userData == "hero" && contact.fixtureB.body.userData == "platform") || (contact.fixtureA.body.userData == "platform" && contact.fixtureB.body.userData == "hero"))
                    hero.isTouchingPlatform = false
            }

            override fun preSolve(contact: Contact?, oldManifold: Manifold?) {}
            override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {}
        })
    }

    override fun act(delta: Float) {
        super.act(delta)
        camera.position.set(0f, hero.y, 0f)

        if (hero.body.linearVelocity.y > 0.0f) {
            for (platform in platforms) platform.body.fixtureList.first().isSensor = true
            hero.isInJump = true
        } else if (hero.body.linearVelocity.y <= 0.0f) {
            for (platform in platforms) platform.body.fixtureList.first().isSensor = false
            if (hero.isTouchingPlatform) hero.isInJump = false
        }

        val frameTime = min(delta, 0.25f)
        accumulator += frameTime
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, 6, 2)
            accumulator -= TIME_STEP
        }

        //world.step(1 / 60f, 6, 2)

        for ((i, platform) in platforms.withIndex()) {
            if (platform.isLast) {
                if (hero.y - platform.y > ((platforms.size / 2) * distanceBetweenPlatforms)) {
                    val pX = Random.nextDouble(-4.5, 4.5).toFloat()
                    val pY = platformIterations.toFloat() * distanceBetweenPlatforms.toFloat()
                    platform.body.setTransform(pX, pY, 0f)
                    platform.isLast = false
                    platformIterations++

                    if (i + 1 == platforms.size) platforms[0].isLast = true
                    else platforms[i + 1].isLast = true
                }
            }
        }

        background.heroY = hero.y
    }
}