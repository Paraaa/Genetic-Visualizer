package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OnePointArithmeticCrossoverTest {
    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
    var population= initializer.initialize(VectorGenome(), 500)

    var onePointArithmeticCrossover: OnePointArithmeticCrossover = OnePointArithmeticCrossover()
    @Test
    fun crossover() {
        var childPopulation1 = onePointArithmeticCrossover.crossover(population,300)
        assertEquals(300,childPopulation1.size)
        var childPopulation2 = onePointArithmeticCrossover.crossover(population,1)
        assertEquals(1,childPopulation2.size)
        var childPopulation3 = onePointArithmeticCrossover.crossover(mutableListOf(),1)
        assertEquals(0,childPopulation3.size)
        var childPopulation4 = onePointArithmeticCrossover.crossover(population,600)
        assertEquals(600,childPopulation4.size)
    }
}