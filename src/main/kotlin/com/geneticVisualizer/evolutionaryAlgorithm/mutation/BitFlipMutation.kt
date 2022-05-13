package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random
/**
 * Implements the bit flip logic
 * @author Andrej Schwanke
 */
class BitFlipMutation: MutationStrategy() {

    /**
     * This function to execute the bit flip logic
     * @param childPopulation List of children which have a probability to mutate
     * @param probability The probability in which the mutation can occur
     * @return returns a list of mutated genomes. Genomes which a not mutated will be put in this list
     * too. A new instance of the [GenomeStrategy] is created if a mutation occurs.
     */
    override fun mutation(childPopulation: MutableList<GenomeStrategy>, probability: Double ): MutableList<GenomeStrategy> {
        var mutatedChildPopulation: MutableList<GenomeStrategy> = mutableListOf()
        childPopulation.forEach {genome ->
            var mutatedSolution: MutableList<Double> = mutableListOf()
            var wasMutated = false
            genome.solution.forEach { bit ->
                if (Random.nextDouble(0.0, 1.0) < probability){
                    if(bit == 1.0){
                        mutatedSolution.add(0.0)
                    } else {
                        mutatedSolution.add(1.0)
                    }
                    wasMutated = true
                } else {
                    mutatedSolution.add(bit)
                }
            }
            if(wasMutated){
                var newGenome = BinaryGenome()
                newGenome.solution = mutatedSolution
                mutatedChildPopulation.add(newGenome)
            } else {
                mutatedChildPopulation.add(genome)
            }
        }
        return mutatedChildPopulation
    }
}