package com.markal.gdxgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.markal.gdxgame.screen.ScreenManager

class MyGdxGame : Game() {

    companion object {
        const val scale = 2f
        const val gameHeight: Float = scale * 480
        const val gameWidth: Float = scale * 800
    }

    val batch by lazy { SpriteBatch() }
    val font by lazy {
        BitmapFont().apply { data.scale(scale) }
    }
    val screenManager = ScreenManager(this)

    override fun create() {
        screenManager.goToMainMenu()
    }

    override fun dispose() {
        // dispose of all the native resources
        batch.dispose()
        font.dispose()
        screenManager.dispose()
    }
}
