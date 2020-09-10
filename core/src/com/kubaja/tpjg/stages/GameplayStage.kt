package com.kubaja.tpjg.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kubaja.tpjg.actors.Hero
import com.kubaja.tpjg.actors.Platform
import kotlin.random.Random

class GameplayStage(viewport: ExtendViewport, manager: AssetManager) : Stage(viewport) {

    val distanceBetweenPlatforms = 4
    var platformIterations = 0

    val world = World(Vector2(0f, -10f), true)
    val debugRenderer = Box2DDebugRenderer()

    val hero: Hero
    val platforms : Array<Platform>

    init {
        camera.position.set(0f,0f,0f)

        hero = Hero(manager.get("hero.png", Texture::class.java), world)
        platforms = Array(6) { Platform(manager.get("platform2.png", Texture::class.java), world) }

        for ((i, p) in platforms.withIndex()) {
            val pX = Random.nextDouble(-3.0, 3.0).toFloat()
            val pY = (i.toFloat() * 4)
            platformIterations++
            p.body.setTransform(pX, pY, 0f)
            if (i == 0) p.isLast = true
            addActor(p)
        }
        addActor(hero)
    }

    private fun keyboardControl() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (hero.body.linearVelocity.x < 7) hero.body.applyForceToCenter(hero.SPEED_UP_X_VELOCITY, 0.0f,true);
            else hero.body.applyForceToCenter(15.0f, 0.0f,true)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (hero.body.linearVelocity.x > -7) hero.body.applyForceToCenter(-hero.SPEED_UP_X_VELOCITY, 0.0f,true)
            else hero.body.applyForceToCenter(-15.0f, 0.0f,true)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && hero.body.linearVelocity.y > -hero.MAX_VELOCITY) {
            hero.body.applyForceToCenter(0.0f, 11.0f,true)
        }

        //Gdx.app.log("linearVelocity.x", hero.body.linearVelocity.x.toString())

        if (hero.body.linearVelocity.x > hero.MAX_VELOCITY) {
            hero.body.setLinearVelocity(hero.MAX_VELOCITY, hero.body.linearVelocity.y)
        }
        if (hero.body.linearVelocity.x < -hero.MAX_VELOCITY) hero.body.setLinearVelocity(-hero.MAX_VELOCITY, hero.body.linearVelocity.y)
    }

    override fun act(delta: Float) {
        super.act(delta)
        camera.position.set(0f,hero.y,0f)

        for ((i, platform) in platforms.withIndex()) {
            if (platform.isLast) {
                if (hero.y - platform.y > ((platforms.size / 2) * distanceBetweenPlatforms)) {
                    val pX = Random.nextDouble(-3.0, 3.0).toFloat()
                    val pY = platformIterations.toFloat() * distanceBetweenPlatforms.toFloat()
                    platform.body.setTransform(pX, pY, 0f)
                    platform.isLast = false
                    platformIterations++

                    if (i + 1 == platforms.size) platforms[0].isLast = true
                    else platforms[i + 1].isLast = true
                }
            }
        }

        keyboardControl()

        //Gdx.app.log("Player velocity", hero.body.linearVelocity.x.toString())

        world.step(1/60f, 6, 2)
    }
}