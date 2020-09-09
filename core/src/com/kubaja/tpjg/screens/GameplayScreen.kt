package com.kubaja.tpjg.screens

import com.kubaja.tpjg.TinyCore
import com.kubaja.tpjg.stages.GameplayStage

class GameplayScreen(core: TinyCore) : AbstractScreen(core) {

    private lateinit var gameplayStage: GameplayStage

    override fun show() {
        gameplayStage = GameplayStage(extendViewPort, tinyCore.manager)
    }

    override fun render(delta: Float) {
        super.render(delta)

        gameplayStage.act(delta)
        gameplayStage.draw()
        gameplayStage.debugRenderer.render(gameplayStage.world, gameplayStage.camera.combined)
    }

}