package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GaussMutationTest {

    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
    var population= initializer.initialize(VectorGenome(), 500)

    var gaussMutation: GaussMutation = GaussMutation(4.0)
    @Test
    fun mutation() {
        var childPopulation1 = gaussMutation.mutation(population,100.0)
        assertEquals(500,childPopulation1.size)
        var childPopulation2 = gaussMutation.mutation(population,0.0)
        assertEquals(500,childPopulation2.size)
    }
}