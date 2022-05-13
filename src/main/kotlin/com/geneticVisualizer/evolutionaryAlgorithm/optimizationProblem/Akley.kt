package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.*

/**
 * Implements the Akley function
 * @author Andrej Schwanke
 */
class Akley: OptimizationProblemStrategy() {
    override var scale = 225
    override var contourPathJAR: String = "com/geneticVisualizer/img/Akley.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Akley.png"
    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return -20 * exp(-0.2*sqrt(1/2*(xs.pow(2)+ys.pow(2)))) -
                exp(1/2*(cos(2* PI * xs) + cos(2* PI * ys))) + 20 + E
    }
}