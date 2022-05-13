package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random


/**
 * Implements the swap mutation logic
 * @author Andrej Schwanke
 */
class SwapMutation: MutationStrategy() {
    /**
     * This function to execute the swap mutation logic. Randomly swaps two positions of the solution.
     * @param childPopulation List of children which have a probability to mutate
     * @param probability The probability in which the mutation can occur
     * @return returns a list of mutated genomes. Genomes which a not mutated will be put in this list
     * too. A new instance of the [GenomeStrategy] is created if a mutation occurs.
     */
    override fun mutation(childPopulation: MutableList<GenomeStrategy>, probability: Double): MutableList<GenomeStrategy> {
        var mutatedChildPopulation: MutableList<GenomeStrategy> = mutableListOf()
        childPopulation.forEach { genome ->
            if(Random.nextDouble(0.0, 1.0) > probability){
                mutatedChildPopulation.add(genome)
                return@forEach
            }
            var lowerIndex = Random.nextInt(0, genome.solution.size)
            var upperIndex = Random.nextInt(lowerIndex, genome.solution.size)

            var newSolution: MutableList<Double> = mutableListOf()
            newSolution.addAll(genome.solution)

            newSolution[lowerIndex] = genome.solution[upperIndex]
            newSolution[upperIndex] = genome.solution[lowerIndex]

            var newGenome = genome.createEmptyGenome()
            newGenome.solution = newSolution
            mutatedChildPopulation.add(newGenome)
        }
        return mutatedChildPopulation
    }
}