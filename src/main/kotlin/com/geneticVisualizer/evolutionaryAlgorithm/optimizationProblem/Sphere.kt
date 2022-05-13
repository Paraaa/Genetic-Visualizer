package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.pow
/**
 * Implements the paraboloid function
 * @author Andrej Schwanke
 */
class Sphere : OptimizationProblemStrategy() {
    override var contourPathJAR: String = "com/geneticVisualizer/img/Paraboloid.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Paraboloid.png"

    override fun calculate(x: Double, y: Double): Double {
        return (x).pow(2) + (y).pow(2)
    }
}