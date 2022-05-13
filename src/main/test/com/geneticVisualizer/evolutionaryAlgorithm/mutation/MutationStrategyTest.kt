package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MutationStrategyTest {

	var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
	var population= initializer.initialize(BinaryGenome(), 500)

	var mutationStrategy: MutationStrategy = MutationStrategy()
	@Test
	fun mutation() {
		var childPopulation1 = mutationStrategy.mutation(population,100.0)
		assertEquals(0,childPopulation1.size)
		var childPopulation2 = mutationStrategy.mutation(population,0.0)
		assertEquals(0,childPopulation2.size)
	}
}