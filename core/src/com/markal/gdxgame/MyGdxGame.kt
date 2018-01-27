package com.markal.gdxgame

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.markal.gdxgame.screen.ScreenManager

class MyGdxGame : Game() {

    val batch by lazy { SpriteBatch() }
    val font by lazy { BitmapFont() }
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
