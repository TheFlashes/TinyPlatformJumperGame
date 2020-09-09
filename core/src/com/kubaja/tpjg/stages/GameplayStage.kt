package com.kubaja.tpjg.stages

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.kubaja.tpjg.actors.Hero
import com.kubaja.tpjg.actors.Platform

class GameplayStage(viewport: ExtendViewport, manager: AssetManager) : Stage(viewport) {

    val world = World(Vector2(0f, -10f), true)
    val debugRenderer = Box2DDebugRenderer()

    private val hero: Hero
    private val platform: Platform

    init {
        camera.position.set(0f,0f,0f)

        hero = Hero(manager.get("hero.png", Texture::class.java), world)
        platform = Platform(manager.get("wood_platform.png", Texture::class.java), world)

        addActor(hero)
        addActor(platform)
    }

    override fun act(delta: Float) {
        super.act(delta)
        world.step(1/60f, 6, 2)
    }
}