package com.markal.gdxgame.`object`

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Shape2D
import com.badlogic.gdx.math.Vector3
import java.awt.Point

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
abstract class AbstractObject(
        val position: Rectangle,
        val texture: Texture
) {

    abstract fun update(delta: Float)
    abstract fun processTouch(touchPoint: Vector3)
}