package com.markal.gdxgame.screen

import com.markal.gdxgame.MyGdxGame
import com.markal.gdxgame.font.Text

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class EndGameScreen(game: MyGdxGame) : AbstractScreen(game) {
    override val texts: List<Text>
        get() = listOf(
                Text("Game End", MyGdxGame.gameWidth / 2 - MyGdxGame.gameWidth / 8, MyGdxGame.gameHeight / 2),
                Text("Your score was: ${game.lastScore}", MyGdxGame.gameWidth / 2 - MyGdxGame.gameWidth / 4, MyGdxGame.gameHeight / 2 - 100),
                Text("Record is: ${game.getHighestScore()}", MyGdxGame.gameWidth / 2 - MyGdxGame.gameWidth / 4, MyGdxGame.gameHeight / 2 - 150),
                Text("Tap anywhere to start new game", MyGdxGame.gameWidth / 2 - MyGdxGame.gameWidth / 4, MyGdxGame.gameHeight / 2 - 250)
        )

    override fun render(delta: Float) {
        super.render(delta)

        touchPoint()?.let { _ ->
            game.screenManager.goToGame()
            dispose()
        }
    }
}
