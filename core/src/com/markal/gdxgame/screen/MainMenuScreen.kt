package com.markal.gdxgame.screen

import com.badlogic.gdx.Gdx
import com.markal.gdxgame.MyGdxGame

/**
 * Author: Martin Kalenda
 * Created: 1/26/2018
 */
class MainMenuScreen(game: MyGdxGame) : AbstractScreen(game) {

    override fun render(delta: Float) {
        super.render(delta)

        with(game) {
            batch.begin()
            font.draw(batch, "Welcome to Drop", 100f, 150f)
            font.draw(batch, "Tap anywhere to begin!", 100f, 100f)
            batch.end()
        }

        if (Gdx.input.isTouched) {
            game.screenManager.goToGame()
            dispose()
        }
    }
}
