package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.pow

/**
 * Implements the Beale function
 * @author Andrej Schwanke
 */
class Beale: OptimizationProblemStrategy() {
    override var scale = 220
    override var contourPathJAR: String = "com/geneticVisualizer/img/Beale.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Beale.png"
    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return (1.5 - xs * (1 - y)).pow(2) + (2.25 - xs *(1 - ys.pow(2))).pow(2) +
                (2.625 - xs * (1 - ys.pow(3))).pow(2)
    }
}