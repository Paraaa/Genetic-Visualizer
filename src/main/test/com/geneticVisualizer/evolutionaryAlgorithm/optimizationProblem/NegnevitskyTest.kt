package com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NegnevitskyTest {

    @Test
    fun calculate() {
        var problem = Negnevitsky()
        var solution = problem.calculate(5.0,1.0)
        assertEquals(0.3210426718834939, solution)
        solution = problem.calculate(-150.0,-150.0)
        assertEquals(1.6088019560245623,solution)
    }
}