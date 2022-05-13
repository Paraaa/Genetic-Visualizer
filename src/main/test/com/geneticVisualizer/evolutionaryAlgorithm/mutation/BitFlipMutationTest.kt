package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BitFlipMutationTest {

    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
    var population= initializer.initialize(BinaryGenome(), 500)

    var bitFlipMutation: BitFlipMutation = BitFlipMutation()
    @Test
    fun mutation() {
        var childPopulation1 = bitFlipMutation.mutation(population,100.0)
        assertEquals(500,childPopulation1.size)
        var childPopulation2 = bitFlipMutation.mutation(population,0.0)
        assertEquals(500,childPopulation2.size)
    }
}