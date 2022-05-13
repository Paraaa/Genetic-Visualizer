package com.geneticVisualizer.evolutionaryAlgorithm

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.Sphere
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GenerationTest {

    var testGeneration: Generation = Generation()

    @Test
    fun calcEmptyStatistics() {
        testGeneration.calcStatistics(Sphere())
        assertEquals(0.0, testGeneration.worstFitness)
        assertEquals(0.0, testGeneration.bestFitness)
        assertEquals(0.0, testGeneration.averageFitness)
        assertEquals(Pair(0.0,0.0), testGeneration.bestGenomeCoordinate)
        assertEquals(0.0, testGeneration.currentSolution)
    }
    @Test
    fun calcFullStatistics(){
        var genomeStrategy: VectorGenome = VectorGenome()
        var genome1 = genomeStrategy.createGenome(1.0,5.0)
        var genome2 = genomeStrategy.createGenome(6.0,8.0)
        genome1.calcFitness(1,Sphere())
        genome2.calcFitness(1,Sphere())
        testGeneration.survivorPopulation = mutableListOf(genome1,genome2)
        testGeneration.calcStatistics(Sphere())
        assertEquals(26.0, testGeneration.worstFitness)
        assertEquals(100.0, testGeneration.bestFitness)
        assertEquals(63.0, testGeneration.averageFitness)
        assertEquals(Pair(6.0,8.0), testGeneration.bestGenomeCoordinate)
        assertEquals(100.0, testGeneration.currentSolution)
    }
}