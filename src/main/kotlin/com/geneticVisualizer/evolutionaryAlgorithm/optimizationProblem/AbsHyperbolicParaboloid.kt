package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.abs
import kotlin.math.pow


/**
 * Implements the abs hyperbolic paraboloid function
 * @author Andrej Schwanke
 */
class AbsHyperbolicParaboloid: OptimizationProblemStrategy() {
    override var contourPathJAR: String = "com/geneticVisualizer/img/AbsHyperbolicParaboloid.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/AbsHyperbolicParaboloid.png"

    override fun calculate(x: Double, y: Double): Double {
        return abs((x).pow(2) - (y).pow(2))
    }
}