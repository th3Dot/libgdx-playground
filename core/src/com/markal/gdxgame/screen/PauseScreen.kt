package com.markal.gdxgame.screen

import com.markal.gdxgame.MyGdxGame
import com.markal.gdxgame.font.Text

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class PauseScreen(game: MyGdxGame) : AbstractScreen(game) {
    override val texts: List<Text>
        get() = listOf(
                Text("Game Paused", MyGdxGame.gameWidth / 2 - MyGdxGame.gameWidth / 10, MyGdxGame.gameHeight / 2),
                Text("Tap anywhere to continue", MyGdxGame.gameWidth / 2 - MyGdxGame.gameWidth / 6, MyGdxGame.gameHeight / 2 - 50)
        )

    override fun render(delta: Float) {
        super.render(delta)

        touchPoint()?.let { _ ->
            game.screenManager.goToGame()
            dispose()
        }
    }
}
