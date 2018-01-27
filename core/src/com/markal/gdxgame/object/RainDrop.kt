package com.markal.gdxgame.`object`

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.markal.gdxgame.MyGdxGame

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class RainDrop(texture: Texture) : AbstractObject(
        Rectangle(
                MathUtils.random(0f, MyGdxGame.gameWidth - 64f),
                MyGdxGame.gameHeight,
                64f,
                64f
        ),
        texture
) {

    override fun update(delta: Float) {
        position.y -= 300 * delta * MyGdxGame.scale
    }

    override fun processTouch(touchPoint: Vector3) {
    }
}
