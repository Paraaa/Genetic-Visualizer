package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.abs

/**
 * Implements the BoothMAX function
 * @author Andrej Schwanke
 */
class BoothMAX: OptimizationProblemStrategy() {

    override var contourPathJAR: String = "com/geneticVisualizer/img/BoothMAX.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/BoothMAX.png"
    override fun calculate(x: Double, y: Double): Double {
        return maxOf(abs(x +2*y-7),abs(2*x+y-5))
    }
}