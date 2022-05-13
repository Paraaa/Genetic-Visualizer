package com.geneticVisualizer.evolutionaryAlgorithm.genome

import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.Sphere
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VectorGenomeTest {

    var genomeStrategy: VectorGenome = VectorGenome()

    @Test
    fun createEmptyGenome() {
        var genome = genomeStrategy.createEmptyGenome()
        assertTrue(genome is VectorGenome)
        assertEquals(0, genome.age)
        assertEquals(0.0, genome.fitness)
        assertTrue(genome.solution.isEmpty())
    }

    @Test
    fun createGenome() {
        var genome = genomeStrategy.createGenome(50.0,50.0)
        assertTrue(genome is VectorGenome)
        assertEquals(0, genome.age)
        assertEquals(0.0, genome.fitness)
        assertTrue(genome.solution.isNotEmpty())
        assertEquals(50.0, genome.X())
        assertEquals(50.0, genome.Y())
    }

    @Test
    fun calcFitness() {
        var genome = genomeStrategy.createGenome(50.0,50.0)
        assertTrue(genome is VectorGenome)
        assertEquals(0, genome.age)
        genome.calcFitness(1,Sphere())
        assertEquals(5000.0, genome.fitness)
        assertTrue(genome.solution.isNotEmpty())
        assertEquals(50.0, genome.X())
        assertEquals(50.0, genome.Y())
    }
    @Test
    fun x() {
        var genome = genomeStrategy.createGenome(50.0,50.0)
        assertEquals(50.0, genome.X())
        assertEquals(50.0, genome.Y())
    }
    @Test
    fun y() {
        var genome = genomeStrategy.createGenome(50.0,50.0)
        assertEquals(50.0, genome.X())
        assertEquals(50.0, genome.Y())
    }
}