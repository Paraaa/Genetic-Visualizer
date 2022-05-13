package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class KPointCrossoverTest {
    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
    var population= initializer.initialize(BinaryGenome(), 500)

    var kPointCrossover: KPointCrossover = KPointCrossover(5)
    @Test
    fun crossover() {
        var childPopulation1 = kPointCrossover.crossover(population,300)
        assertEquals(300,childPopulation1.size)
        var childPopulation2 = kPointCrossover.crossover(population,1)
        assertEquals(1,childPopulation2.size)
        var childPopulation3 = kPointCrossover.crossover(mutableListOf(),1)
        assertEquals(0,childPopulation3.size)
        var childPopulation4 = kPointCrossover.crossover(population,600)
        assertEquals(600,childPopulation4.size)
    }
}