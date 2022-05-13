package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.pow
/**
 * Implements the hyperbolic paraboloid function
 * @author Andrej Schwanke
 */
class HyperbolicParaboloid : OptimizationProblemStrategy() {
    override var contourPathJAR: String = "com/geneticVisualizer/img/HyperbolicParaboloid.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/HyperbolicParaboloid.png"

    override fun calculate(x: Double, y: Double): Double {
        return (x).pow(2) - (y).pow(2)
    }
}