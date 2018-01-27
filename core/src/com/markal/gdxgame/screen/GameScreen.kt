package com.markal.gdxgame.screen

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.TimeUtils
import com.markal.gdxgame.MyGdxGame
import com.markal.gdxgame.`object`.AbstractObject
import com.markal.gdxgame.`object`.Bucket
import com.markal.gdxgame.`object`.RainDrop
import com.markal.gdxgame.font.Text


/**
 * Author: Martin Kalenda
 * Created: 1/26/2018
 */
class GameScreen(game: MyGdxGame) : AbstractScreen(game) {

    private val dropImage: Texture by lazy { assets.get("droplet.png", Texture::class.java) }
    private val dropSound: Sound by lazy { assets.get("drop.wav", Sound::class.java) }
    private val rainMusic: Music by lazy { assets.get("rain.mp3", Music::class.java) }
    private val bucket by lazy { Bucket(assets.get("bucket.png", Texture::class.java)) }
    private var lastDropTime: Long = 0
    private var dropsGathered: Int = 0
    private val raindrops: MutableList<RainDrop> = mutableListOf()
    override val objects: List<AbstractObject>
        get() = raindrops + bucket

    override val texts: List<Text>
        get() = listOf(Text("Drops Collected: " + dropsGathered, 0f, MyGdxGame.gameHeight))

    override fun prepareAssets() {
        // load the images for the droplet and the bucket, 64x64 pixels each
        assets.load("droplet.png", Texture::class.java)
        assets.load("bucket.png", Texture::class.java)

        // load the drop sound effect and the rain background "music"
        assets.load("drop.wav", Sound::class.java)
        assets.load("rain.mp3", Music::class.java)
    }

    override fun render(delta: Float) {
        super.render(delta)

        if (TimeUtils.nanoTime() - lastDropTime > 8000000000) spawnRaindrop()

        raindrops.iterator().let { iterator ->
            iterator.forEach { raindrop ->
                if (raindrop.position.y + 64 < 0) iterator.remove()
                if (raindrop.position.overlaps(bucket.position)) {
                    dropsGathered++
                    dropSound.play()
                    iterator.remove()
                }
            }
        }
    }

    override fun pause() {
        super.pause()
        rainMusic.pause()
    }

    override fun show() {
        super.show()

        rainMusic.isLooping = true
        rainMusic.play()
    }

    private fun spawnRaindrop() {
        raindrops.add(RainDrop(dropImage))
        lastDropTime = TimeUtils.nanoTime()
    }
}
