package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.pow

/**
 * Implements the Booth function
 * @author Andrej Schwanke
 */
class Booth: OptimizationProblemStrategy() {
    override var contourPathJAR: String = "com/geneticVisualizer/img/Booth.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Booth.png"
    override fun calculate(x: Double, y: Double): Double {
        return (x +2*y-7).pow(2) + (2*x+y-5).pow(2)
    }
}