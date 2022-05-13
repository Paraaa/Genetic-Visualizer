package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.abs
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Implements the Schwefel function
 * @author Andrej Schwanke
 */
class Schwefel: OptimizationProblemStrategy() {
    override var scale = 50
    override var contourPathJAR: String = "com/geneticVisualizer/img/Schwefel.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Schwefel.png"
    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return 418.9829 * 2 + ((xs * sin(sqrt(abs(xs))+ (ys * sin(sqrt(abs(ys)))))))
    }
}