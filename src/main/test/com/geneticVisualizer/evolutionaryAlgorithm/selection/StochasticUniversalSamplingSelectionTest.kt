package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.Sphere
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StochasticUniversalSamplingSelectionTest {

    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
    var population = initializer.initialize(VectorGenome(), 500)

    var stochasticUniversalSamplingSelection: StochasticUniversalSamplingSelection = StochasticUniversalSamplingSelection()

    @Test
    fun selection() {
        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1,Sphere())
        }
        var selectedPopulation1 = stochasticUniversalSamplingSelection.selection(population,600)
        assertEquals(600,selectedPopulation1.size)

        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1,Sphere())
        }
        var selectedPopulation2 = stochasticUniversalSamplingSelection.selection(population,500)
        assertEquals(500,selectedPopulation2.size)

        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1,Sphere())
        }
        var selectedPopulation3 = stochasticUniversalSamplingSelection.selection(population,261)
        assertEquals(261,selectedPopulation3.size)

        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1,Sphere())
        }
        var selectedPopulation4 = stochasticUniversalSamplingSelection.selection(population,1)
        assertEquals(1,selectedPopulation4.size)
    }
}