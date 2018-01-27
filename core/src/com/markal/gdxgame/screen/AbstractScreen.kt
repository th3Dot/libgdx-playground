package com.markal.gdxgame.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import com.markal.gdxgame.MyGdxGame
import com.markal.gdxgame.`object`.AbstractObject
import com.markal.gdxgame.`object`.ObjectRenderer
import com.markal.gdxgame.font.FontRenderer
import com.markal.gdxgame.font.Text

/**
 * Author: Martin Kalenda
 * Created: 1/26/2018
 */
abstract class AbstractScreen(protected val game: MyGdxGame) : Screen {

    protected val assets: AssetManager = AssetManager()
    private var assetsLoaded: Boolean = false
    private val camera = OrthographicCamera().apply {
        setToOrtho(false, MyGdxGame.gameWidth, MyGdxGame.gameHeight)
    }
    private val objectRenderer = ObjectRenderer(game.batch)
    private val fontRenderer = FontRenderer(game.batch, game.font)
    protected open val objects: List<AbstractObject> by lazy { mutableListOf<AbstractObject>() }
    protected open val texts: List<Text> by lazy { mutableListOf<Text>() }
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

        objects.forEach { obj ->
            obj.update(delta)
            touchPoint()?.let { obj.processTouch(it) }
        }
        objectRenderer.render(objects)
        fontRenderer.render(texts)
    }

    override fun pause() {
        game.screenManager.goToPause()
    }

    override fun resume() {
        assets.finishLoading()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        assets.dispose()
    }

    private fun prepareCamera() {
        camera.update()
        game.batch.projectionMatrix = camera.combined
    }

    private fun clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    fun touchPoint(): Vector3? {
        return if (Gdx.input.isTouched) {
            Vector3().apply {
                set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
                camera.unproject(this)
            }
        } else {
            null
        }
    }
}
