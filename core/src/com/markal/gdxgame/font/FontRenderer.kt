package com.markal.gdxgame.font

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Author: Martin Kalenda
 * Created: 1/27/2018
 */
class FontRenderer(
        private val renderBatch: SpriteBatch,
        private val bitmapFont: BitmapFont
) {
    fun render(list: List<Text>) {
        with(renderBatch) {
            begin()
            list.forEach { font ->
                bitmapFont.draw(renderBatch, font.text, font.x, font.y)
            }
            end()
        }
    }
}
