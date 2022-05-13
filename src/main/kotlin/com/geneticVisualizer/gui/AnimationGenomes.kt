package com.geneticVisualizer.gui

import javafx.animation.AnimationTimer

/**
 * This class extends the [AnimationTimer]. This is necessary to keep track
 * if an animation has been stopped or started.
 */
abstract class AnimationGenomes: AnimationTimer() {
    /**
     * Holds information about if the animation has started
     */
    var animationStarted: Boolean = false
    /**
     * Holds information about if the animation has stopped
     */
    var animationStopped: Boolean = false

    override fun start() {
        super.start()
    }
    override fun stop() {
        super.stop()
    }

    override fun handle(now: Long) { }
}