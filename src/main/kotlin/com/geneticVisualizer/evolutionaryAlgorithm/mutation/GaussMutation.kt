package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import kotlin.random.Random

/**
 * Implements the gaussian mutation logic
 * @author Andrej Schwanke
 */
class GaussMutation(var sigma: Double): MutationStrategy() {
    private val jRandom = java.util.Random()
    /**
     * This function to execute the gaussian mutation logic
     * @param childPopulation List of children which have a probability to mutate
     * @param probability The probability in which the mutation can occur
     * @return returns a list of mutated genomes. Genomes which a not mutated will be put in this list
     * too. A new instance of the [GenomeStrategy] is created if a mutation occurs.
     */
    override fun mutation(childPopulation: MutableList<GenomeStrategy>, probability: Double): MutableList<GenomeStrategy> {

        var mutatedChildPopulation: MutableList<GenomeStrategy> = mutableListOf()
        childPopulation.forEach {genome ->
            if(Random.nextDouble(0.0,1.0) > probability) {
                mutatedChildPopulation.add(genome)
                return@forEach
            }
            var newX : Double = genome.X() + (jRandom.nextGaussian() * sigma)
            var newY : Double = genome.Y() + (jRandom.nextGaussian() * sigma)
            var newGenome = VectorGenome()
            newGenome.solution = mutableListOf(newX,newY)
            mutatedChildPopulation.add(newGenome)
        }
        return mutatedChildPopulation
    }
}