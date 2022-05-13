package com.geneticVisualizer.enums

/**
 * This enum holds all types of optimization goals
 * @author Andrej Schwanke
 */
enum class Goals(var guiName: String) {
    /**
     * Searching for the maximum in the solution space
     */
    Maximize("Maximize"),
    /**
     * Searching for the minimum in the solution space
     */
    Minimize("Minimize")
}