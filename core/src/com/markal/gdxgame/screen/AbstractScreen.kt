package com.markal.gdxgame.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.markal.gdxgame.MyGdxGame

/**
 * Author: Martin Kalenda
 * Created: 1/26/2018
 */
abstract class AbstractScreen(protected val game: MyGdxGame) : Screen {

    protected val assets: AssetManager = AssetManager()
    private var assetsLoaded: Boolean = false
    protected val camera = OrthographicCamera().apply {
        setToOrtho(false, 800f, 480f)
    }

    protected open fun prepareAssets() {
    }

    override fun hide() {
    }

    override fun show() {
        if (!assetsLoaded) {
            prepareAssets()
            loadAssets()
        }
    }

    private fun loadAssets() {
        assets.finishLoading()
        assetsLoaded = true
    }

    override fun render(delta: Float) {
        clearScreen()
        prepareCamera()
    }

    override fun pause() {
        game.screenManager.goToMainMenu()
    }

    override fun resume() {
        assets.finishLoading()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

    private fun prepareCamera() {
        camera.update()
        game.batch.projectionMatrix = camera.combined
    }

    private fun clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
}
