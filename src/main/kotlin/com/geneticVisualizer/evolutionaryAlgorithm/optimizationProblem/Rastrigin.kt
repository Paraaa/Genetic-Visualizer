package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow

class Rastrigin: OptimizationProblemStrategy() {
    override var scale = 85
    override var contourPathJAR: String = "com/geneticVisualizer/img/Rastrigin.png"
    override var contourPathIDE: String = "src/main/resources/com/geneticVisualizer/img/Rastrigin.png"


    override fun calculate(x: Double, y: Double): Double {
        val xs = x/scale
        val ys = y/scale
        val A = 10
        return ((xs).pow(2) - A * cos(2 * PI * (xs))) +
        ((ys).pow(2) - A * cos(2 * PI * (ys))) + 20
    }
}