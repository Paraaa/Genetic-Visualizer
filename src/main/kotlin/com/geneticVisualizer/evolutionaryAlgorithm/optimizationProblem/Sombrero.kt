package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
/**
 * Implements the sombero function
 * @author Andrej Schwanke
 */
class Sombrero: OptimizationProblemStrategy() {
    override var scale: Int = 50
    override var contourPathJAR: String = "com/geneticVisualizer/img/Sombero.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Sombero.png"

    override fun calculate(x: Double, y: Double): Double {
        return sin(sqrt((x/scale).pow(2) + (y/scale).pow(2))) / sqrt((x/scale).pow(2) + (y/scale).pow(2))
    }
}