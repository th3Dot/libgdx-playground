package com.markal.gdxgame.screen

import com.markal.gdxgame.MyGdxGame
import com.markal.gdxgame.font.Text

/**
 * Author: Martin Kalenda
 * Created: 1/26/2018
 */
class MainMenuScreen(game: MyGdxGame) : AbstractScreen(game) {

    override val texts: List<Text> = mutableListOf(
            Text("Welcome to Drop", MyGdxGame.gameWidth / 6, MyGdxGame.gameHeight / 5),
            Text("Tap anywhere to begin!", MyGdxGame.gameWidth / 6, MyGdxGame.gameWidth / 5 - 50)
    )

    override fun render(delta: Float) {
        super.render(delta)

        touchPoint()?.let { _ ->
            game.screenManager.goToGame()
            dispose()
        }
    }
}
