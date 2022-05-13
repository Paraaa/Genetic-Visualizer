package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CrossoverStrategyTest {


	var initializer = QuadrantInitial(-465.0,465.5,-335.0,335.0)
	var population= initializer.initialize(VectorGenome(), 500)

	var crossoverStrategy: CrossoverStrategy = CrossoverStrategy()

	@Test
	fun crossover() {
		var childPopulation1 = crossoverStrategy.crossover(population,300)
		assertEquals(0,childPopulation1.size)
		var childPopulation2 = crossoverStrategy.crossover(population,1)
		assertEquals(0,childPopulation2.size)
		var childPopulation3 = crossoverStrategy.crossover(mutableListOf(),1)
		assertEquals(0,childPopulation3.size)
		var childPopulation4 = crossoverStrategy.crossover(population,600)
		assertEquals(0,childPopulation4.size)
	}
}