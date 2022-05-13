package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.math.exp
import kotlin.math.sqrt
import kotlin.random.Random


/**
 * Implements the self adaptation mutation logic
 * @author Andrej Schwanke
 */
class SelfAdaptationMutation: MutationStrategy() {
    private val jRandom = java.util.Random()
    /**
     * This function to execute the self adaptation mutation logic.
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
            var tau: Double = 1.0/ sqrt(childPopulation[0].solution.size.toDouble())
            var zeta: Double = tau * jRandom.nextGaussian()
            var sigmaK: Double = genome.sigma * exp(zeta)

            var newX = genome.X() + sigmaK * jRandom.nextGaussian()
            var newY = genome.Y() + sigmaK * jRandom.nextGaussian()
            var newGenome = genome.createGenome(newX,newY)
            newGenome.sigma = sigmaK

            mutatedChildPopulation.add(newGenome)
        }
        return mutatedChildPopulation
    }
}