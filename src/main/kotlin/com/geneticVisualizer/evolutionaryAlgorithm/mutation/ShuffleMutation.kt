package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random

/**
 * Implements the shuffle mutation logic
 * @author Andrej Schwanke
 */
class ShuffleMutation: MutationStrategy() {

    /**
     * This function to execute the shuffle mutation logic. Randomly shuffles the dna of the
     * genome.
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
            var newSolution = genome.solution.shuffled()
            var newGenome = genome.createEmptyGenome()
            newGenome.solution = newSolution.toMutableList()
            mutatedChildPopulation.add(newGenome)
        }
        return mutatedChildPopulation
    }
}