package com.geneticVisualizer.evolutionaryAlgorithm.initial

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class InitialStrategyTest {

	var initializer = InitialStrategy()


	@Test
	fun initialize() {
		for(i in 1..10000){
			var population = initializer.initialize(VectorGenome(), i)
			assertTrue(population.size == 0)
		}
	}
}