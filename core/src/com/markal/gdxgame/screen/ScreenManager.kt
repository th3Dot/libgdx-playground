package com.markal.gdxgame.screen

import com.badlogic.gdx.Screen
import com.markal.gdxgame.MyGdxGame

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class ScreenManager(private val game: MyGdxGame) {
    private val gameScreen by lazy { GameScreen(game) }
    private val mainMenuScreen by lazy { MainMenuScreen(game) }

    private var lastScreen: Screen? = null

    fun goToMainMenu() {
        game.screen = mainMenuScreen
        lastScreen = mainMenuScreen
    }

    fun goToGame() {
        game.screen = gameScreen
        lastScreen = gameScreen
    }

    fun goToPrevious() {
        game.screen = lastScreen ?: mainMenuScreen
    }

    fun dispose() {
        gameScreen.dispose()
        mainMenuScreen.dispose()
    }
}
