package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.abs


/**
 * Implements the SphereAbs function
 * @author Andrej Schwanke
 */
class SphereAbs: OptimizationProblemStrategy() {
    override var scale = 200
    override var contourPathJAR: String = "com/geneticVisualizer/img/SphereAbs.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/SphereAbs.png"
    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return abs(xs) + abs(ys)
    }
}