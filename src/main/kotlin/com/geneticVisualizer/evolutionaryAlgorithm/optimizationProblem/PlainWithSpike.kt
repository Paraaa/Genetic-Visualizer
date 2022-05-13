package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.floor

/**
 * Implements the PlainWithSpike function
 * @author Andrej Schwanke
 */
class PlainWithSpike: OptimizationProblemStrategy() {
    override var scale = 1
    override var contourPathJAR: String = "com/geneticVisualizer/img/PlainWithSpike.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/PlainWithSpike.png"
    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        if(xs < 50 && xs > -50 && ys < 50 && ys > -50) return 0.0
        return 1000.0
    }

}