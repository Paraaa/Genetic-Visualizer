package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random

/**
 * Implements the inversion mutation logic
 * @author Andrej Schwanke
 */
class InversionMutation: MutationStrategy() {


    /**
     * This function to execute the inversion mutation logic. Inverts a random part of the genome.
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
            var solution = genome.solution
            var fromIndex = Random.nextInt(0,solution.size)
            var toIndex = Random.nextInt(fromIndex,solution.size)

            var newSolution = solution.toDoubleArray()
            newSolution.reverse(fromIndex = fromIndex, toIndex = toIndex)
            var newGenome = BinaryGenome()
            newGenome.solution = newSolution.toMutableList()

            mutatedChildPopulation.add(newGenome)
        }
        return mutatedChildPopulation
    }
}