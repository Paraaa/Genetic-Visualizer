package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.cos
import kotlin.math.sin
/**
 * Implements the bumbs function
 * @author Andrej Schwanke
 */
class Bumps: OptimizationProblemStrategy() {
    override var scale = 250

    override var contourPathJAR: String = "com/geneticVisualizer/img/Bumps.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Bumps.png"

    override fun calculate(x: Double, y: Double): Double {
        return (sin(5*(x/scale)) * cos(5*(y/scale)))/5
    }
}