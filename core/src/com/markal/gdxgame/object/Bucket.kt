package com.markal.gdxgame.`object`

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.markal.gdxgame.MyGdxGame

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class Bucket(texture: Texture) : AbstractObject(
        Rectangle(
                MyGdxGame.gameWidth / 2 - 64 / 2,
                MyGdxGame.gameHeight / 20,
                64f,
                64f
        ),
        texture
) {

    init {
        assert(texture.height == 64)
        assert(texture.width == 64)
    }

    override fun update(delta: Float) {
    }

    override fun processTouch(touchPoint: Vector3) {
        position.x = touchPoint.x - position.width / 2

        // make sure the bucket stays within the screen bounds
        if (position.x < 0) position.x = 0f
        if (position.x > MyGdxGame.gameWidth - position.width) position.x = MyGdxGame.gameWidth - position.width
    }
}
