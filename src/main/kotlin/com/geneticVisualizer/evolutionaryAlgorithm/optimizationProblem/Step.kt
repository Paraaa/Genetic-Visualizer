package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem
import kotlin.math.floor

/**
 * Implements the Step function
 * @author Andrej Schwanke
 */
class Step: OptimizationProblemStrategy() {
    override var scale = 50
    override var contourPathJAR: String = "com/geneticVisualizer/img/Step.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Step.png"

    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return floor(xs) + floor(ys)
    }
}