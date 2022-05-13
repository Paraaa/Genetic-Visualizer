package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.pow
/**
 * Implements the rosenbrock function
 * @author Andrej Schwanke
 */
class Rosenbrock: OptimizationProblemStrategy() {
    override var scale = 200
    override var contourPathJAR: String = "com/geneticVisualizer/img/Rosenbrock.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Rosenbrock.png"

    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return 100 * (xs.pow(2) - ys).pow(2) + (1 - xs).pow(2)
    }
}