package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

/**
 * This class is used as a base class for optimization Problems.
 * To implement an optimization problem on has to inherit this class.
 * @author Andrej Schwanke
 */
open class OptimizationProblemStrategy {

    /**
     * Defines a scale factor by which the input to a function is scaled down.
     * The inheritance of this class override this variable is necessary.
     */
    open var scale: Int = 1

    /**
     * Path to the contour image in the jar
     */
    open var contourPathJAR: String = ""
    /**
     * Path to the contour image in the ide
     */
    open var contourPathIDE: String = ""

    /**
     * This method calculates the corresponding z-value of two independent variables.
     * @param x x value needed for calculating the solution
     * @param y y value needed for calculating the solution
     * @return Solution to a equation
     */
    open fun calculate(x: Double, y: Double): Double{
        return 0.0
    }
}