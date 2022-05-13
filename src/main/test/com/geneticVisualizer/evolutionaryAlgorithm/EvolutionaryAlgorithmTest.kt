package com.geneticVisualizer.evolutionaryAlgorithm

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EvolutionaryAlgorithmTest {

    @Test
    fun solve() {
        var evolutionaryAlgorithm = EvolutionaryAlgorithm()
        evolutionaryAlgorithm.maxIterations = 1000
        var population1: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1000, population1.size)
        evolutionaryAlgorithm.maxIterations = 1
        var population2: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1, population2.size)
        evolutionaryAlgorithm.maxIterations = 5000
        var population3: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(5000, population3.size)
    }

    @Test
    fun solveWithoutMutation(){
        var evolutionaryAlgorithm = EvolutionaryAlgorithm()
        evolutionaryAlgorithm.mutationEnabled = false
        evolutionaryAlgorithm.maxIterations = 1000
        var population4: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1000, population4.size)
        evolutionaryAlgorithm.maxIterations = 1
        var population5: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1, population5.size)
        evolutionaryAlgorithm.maxIterations = 500
        var population6: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(500, population6.size)
    }

    @Test
    fun solveWithoutCrossover(){
        var evolutionaryAlgorithm = EvolutionaryAlgorithm()
        evolutionaryAlgorithm.mutationEnabled = true
        evolutionaryAlgorithm.crossoverEnabled = false
        evolutionaryAlgorithm.maxIterations = 1000
        var population1: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1000, population1.size)
        evolutionaryAlgorithm.maxIterations = 1
        var population2: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1, population2.size)
        evolutionaryAlgorithm.maxIterations = 500
        var population3: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(500, population3.size)
    }

    @Test
    fun solveWithoutCrossoverMutation(){
        var evolutionaryAlgorithm = EvolutionaryAlgorithm()
        evolutionaryAlgorithm.mutationEnabled = false
        evolutionaryAlgorithm.crossoverEnabled = false
        evolutionaryAlgorithm.maxIterations = 1000
        var population1: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1000, population1.size)
        evolutionaryAlgorithm.maxIterations = 1
        var population2: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(1, population2.size)
        evolutionaryAlgorithm.maxIterations = 500
        var population3: MutableList<Generation> = evolutionaryAlgorithm.solve()
        assertEquals(500, population3.size)
    }
}