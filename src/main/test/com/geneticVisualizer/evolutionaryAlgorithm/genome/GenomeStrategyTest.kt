package com.geneticVisualizer.evolutionaryAlgorithm.genome

import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.Sphere
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GenomeStrategyTest{

	var genomeStrategy: GenomeStrategy = GenomeStrategy()

	@Test
	fun createEmptyGenome() {
		var genome = genomeStrategy.createEmptyGenome()
		assertTrue(genome is GenomeStrategy)
		assertEquals(0, genome.age)
		assertEquals(0.0, genome.fitness)
		assertTrue(genome.solution.isEmpty())
	}

	@Test
	fun createGenome() {
		var genome = genomeStrategy.createGenome(50.0,50.0)
		assertTrue(genome is GenomeStrategy)
		assertEquals(0, genome.age)
		assertEquals(0.0, genome.fitness)
		assertTrue(genome.solution.isEmpty())
		assertEquals(0.0, genome.X())
		assertEquals(0.0, genome.Y())
	}

	@Test
	fun calcFitness() {
		var genome = genomeStrategy.createGenome(50.0,50.0)
		assertTrue(genome is GenomeStrategy)
		assertEquals(0, genome.age)
		genome.calcFitness(1, Sphere())
		assertEquals(0.0, genome.fitness)
		assertTrue(genome.solution.isEmpty())
		assertEquals(0.0, genome.X())
		assertEquals(0.0, genome.Y())
	}
	@Test
	fun x() {
		var genome = genomeStrategy.createGenome(50.0,50.0)
		assertEquals(0.0, genome.X())
		assertEquals(0.0, genome.Y())
	}


}