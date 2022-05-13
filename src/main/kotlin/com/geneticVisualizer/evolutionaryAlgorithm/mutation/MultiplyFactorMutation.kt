package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import kotlin.random.Random
/**
 * Implements the multiply by 5 logic
 * @author Andrej Schwanke
 */
class MultiplyFactorMutation(var factor: Int): MutationStrategy() {
	/**
	 * This function to execute the multiply by 5 logic. Multiplies the solution of the genome
	 * by a factor of -5 or +5.
	 * @param childPopulation List of children which have a probability to mutate
	 * @param probability The probability in which the mutation can occur
	 * @return returns a list of mutated genomes. Genomes which a not mutated will be put in this list
	 * too. A new instance of the [GenomeStrategy] is created if a mutation occurs.
	 */
	override fun mutation(childPopulation: MutableList<GenomeStrategy>, probability: Double): MutableList<GenomeStrategy> {
		var mutatedChildPopulation: MutableList<GenomeStrategy> = mutableListOf()
		var factor = mutableListOf(-factor,factor)
		childPopulation.forEach { genome ->
			if(Random.nextDouble(0.0, 1.0) > probability){
				mutatedChildPopulation.add(genome)
				return@forEach
			}
			var newX: Double = genome.X() * factor.random()
			var newY: Double = genome.Y() * factor.random()
			var newGenome = VectorGenome()
			newGenome.solution = mutableListOf(newX,newY)
			mutatedChildPopulation.add(newGenome)
		}
		return mutatedChildPopulation
	}
}