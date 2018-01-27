package com.markal.gdxgame.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.TimeUtils
import com.markal.gdxgame.MyGdxGame


/**
 * Author: Martin Kalenda
 * Created: 1/26/2018
 */
class GameScreen(game: MyGdxGame) : AbstractScreen(game) {


    private val dropImage: Texture by lazy { assets.get("droplet.png", Texture::class.java) }
    private val bucketImage: Texture by lazy { assets.get("bucket.png", Texture::class.java) }
    private val dropSound: Sound by lazy { assets.get("drop.wav", Sound::class.java) }
    private val rainMusic: Music by lazy { assets.get("rain.mp3", Music::class.java) }
    private val bucket = Rectangle()
    private val raindrops: MutableList<Rectangle> = mutableListOf()
    private var lastDropTime: Long = 0
    private var dropsGathered: Int = 0

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

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin()
        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0f, 480f)
        game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height)
        for (raindrop in raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y)
        }
        game.batch.end()

        // process user input
        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)
            bucket.x = touchPos.x - 64 / 2
        }

        // make sure the bucket stays within the screen bounds
        if (bucket.x < 0) bucket.x = 0f
        if (bucket.x > 800f - 64) bucket.x = 800f - 64

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop()

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the
        // value our drops counter and add a sound effect.
        val iter = raindrops.iterator()
        while (iter.hasNext()) {
            val raindrop = iter.next()
            raindrop.y -= 200 * Gdx.graphics.deltaTime
            if (raindrop.y + 64 < 0)
                iter.remove()
            if (raindrop.overlaps(bucket)) {
                dropsGathered++
                dropSound.play()
                iter.remove()
            }
        }
    }

    override fun pause() {
        rainMusic.pause()
        super.pause()
    }

    override fun show() {
        super.show()

        rainMusic.isLooping = true
        rainMusic.play()

        positionBucket()
    }

    override fun dispose() {
        super.dispose()

        assets.dispose()
    }

    private fun spawnRaindrop() {
        val raindrop = Rectangle()
        raindrop.x = MathUtils.random(0, 800 - 64).toFloat()
        raindrop.y = 480f
        raindrop.width = 64f
        raindrop.height = 64f
        raindrops.add(raindrop)
        lastDropTime = TimeUtils.nanoTime()
    }

    private fun positionBucket() {
        // create a Rectangle to logically represent the bucket
        bucket.x = 800f / 2 - 64 / 2 // center the bucket horizontally
        bucket.y = 20f // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        bucket.width = 64f
        bucket.height = 64f
    }
}
