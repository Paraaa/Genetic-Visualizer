package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.cos

import kotlin.math.pow
import kotlin.math.sin

/**
 * Implements the FletcherPowell function
 * @author Andrej Schwanke
 */
class FletcherPowell: OptimizationProblemStrategy() {
    override var scale = 100
    override var contourPathJAR: String = "com/geneticVisualizer/img/FletcherPowell.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/FletcherPowell.png"
    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale

        var A1 = (-46 * sin(-1.0882) + 45 * cos(-1.0882)) +
        (3 * sin(2.56) + -8 * cos(2.56))

        var B1 =  (-46 * sin(xs) + 45 * cos(xs)) +
        (3 * sin(ys) + -8 * cos(ys))

        var A2 = (-56 * sin(-1.0882) + 68 * cos(-1.0882)) +
        (-48 * sin(2.56) + -46 * cos(2.56))

        var B2 = (-56 * sin(xs) + 68 * cos(xs)) +
        (-48 * sin(ys) + -46 * cos(ys))

        return (A1 - B1).pow(2) + (A2 - B2).pow(2)
    }
}