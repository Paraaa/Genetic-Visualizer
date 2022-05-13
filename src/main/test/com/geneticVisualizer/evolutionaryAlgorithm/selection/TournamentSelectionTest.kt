package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.Sphere
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TournamentSelectionTest {

    var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
    var population = initializer.initialize(VectorGenome(), 500)

    var tournamentSelection: TournamentSelection = TournamentSelection(5)

    @Test
    fun selection() {
        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1, Sphere())
        }
        var selectedPopulation1 = tournamentSelection.selection(population,600)
        assertEquals(500,selectedPopulation1.size)

        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1, Sphere())
        }
        var selectedPopulation2 = tournamentSelection.selection(population,500)
        assertEquals(500,selectedPopulation2.size)

        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1, Sphere())
        }
        var selectedPopulation3 = tournamentSelection.selection(population,261)
        assertEquals(261,selectedPopulation3.size)

        population = initializer.initialize(VectorGenome(), 500)
        population.forEach { genome ->
            genome.calcFitness(1, Sphere())
        }
        var selectedPopulation4 = tournamentSelection.selection(population,1)
        assertEquals(1,selectedPopulation4.size)
    }
}