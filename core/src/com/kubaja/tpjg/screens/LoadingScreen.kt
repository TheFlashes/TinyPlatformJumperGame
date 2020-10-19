package com.kubaja.tpjg.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter
import com.kubaja.tpjg.TinyCore


class LoadingScreen(core: TinyCore) : AbstractScreen(core) {

    init {
        tinyCore.manager = AssetManager()
        tinyCore.manager.load("hero.png", Texture::class.java)
        tinyCore.manager.load("badlogic.jpg", Texture::class.java)
        tinyCore.manager.load("platform2.png", Texture::class.java)
        tinyCore.manager.load("buttonArrow.png", Texture::class.java)
        tinyCore.manager.load("background1.png", Texture::class.java)
        tinyCore.manager.load("background2.png", Texture::class.java)

        val resolver: FileHandleResolver = InternalFileHandleResolver()
        tinyCore.manager.setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolver))
        tinyCore.manager.setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolver))

        tinyCore.manager.load("digit2000.ttf", FreeTypeFontGenerator::class.java)
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.app.log("Assets loading", tinyCore.manager.progress.toString())
        if (tinyCore.manager.update()) {
            tinyCore.screen = GameplayScreen(tinyCore)
        }
    }
}