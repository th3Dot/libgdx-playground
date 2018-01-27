package com.markal.gdxgame.`object`

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class ObjectRenderer(
        private val renderBatch: SpriteBatch
) {
    fun render(list: List<AbstractObject>) {
        with(renderBatch) {
            begin()
            list.forEach { renderedObject ->
                draw(
                        renderedObject.texture,
                        renderedObject.position.x,
                        renderedObject.position.y,
                        renderedObject.position.width,
                        renderedObject.position.height
                )
            }
            end()
        }
    }
}
