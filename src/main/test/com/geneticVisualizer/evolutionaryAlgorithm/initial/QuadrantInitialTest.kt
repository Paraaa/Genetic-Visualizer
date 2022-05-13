package com.geneticVisualizer.evolutionaryAlgorithm.initial

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuadrantInitialTest {

    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)

    @Test
    fun initialize() {
        for(i in 1..10000){
            var population = initializer.initialize(VectorGenome(), i)
            assertTrue(population.size == i)
        }
    }
}