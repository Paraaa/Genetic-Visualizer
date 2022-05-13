package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem


import kotlin.math.exp
import kotlin.math.pow

/**
 * Implements the negnevitsky function.
 * Function was found in the book: Artificial Intelligence - A Guid to Intelligent Systems by
 * Micheal Negnevitsky  on page 227
 * @author Andrej Schwanke
 */
class Negnevitsky: OptimizationProblemStrategy() {
    override var scale: Int = 200

    override var contourPathJAR: String = "com/geneticVisualizer/img/Negnevitsky.png"

    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Negnevitsky.png"

    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        return (1-xs).pow(2) *
                exp(-1 * xs.pow(2)-(ys+1).pow(2))-
                (xs - xs.pow(3) - ys.pow(3))*
                exp(-xs.pow(2)-ys.pow(2))
    }
}