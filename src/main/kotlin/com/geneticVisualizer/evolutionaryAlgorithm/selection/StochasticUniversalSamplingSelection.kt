package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import mu.KotlinLogging
import kotlin.random.Random

/**
 * Implements the stochastic universal sampling selection logic
 * @author Andrej Schwanke
 */
class StochasticUniversalSamplingSelection: SelectionStrategy() {
    /**
     * Function to execute the one stochastic universal sampling selection operation
     * Implementation is based on the pseudocode found in: https://en.wikipedia.org/wiki/Stochastic_universal_sampling
     * @param population List of genomes which have to be selected for the next population
     * @param amount Determines the amount of genomes which have to be selected
     * @return Return a list of genomes
     */
    override fun selection(population: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {

        var parents: MutableList<GenomeStrategy> = mutableListOf()
        if(amount == 0){ return parents }
        if(population.size == amount){
            population.forEach { genome ->
                parents.add(genome)
            }
            return parents
        }

        var sumFitness = population.sumOf { genome -> genome.fitness }
        var pointerDistance = sumFitness/amount
        var startPoint = Random.nextDouble(0.0,pointerDistance)
        var pointers: MutableList<Double> = mutableListOf()
        for(i in 0 until amount){
            pointers.add(startPoint + i * pointerDistance)
        }

        population.sortBy { genome: GenomeStrategy -> genome.fitness }

        for(pointer in pointers){
            var j = 0
            while (population.subList(0,j+1).sumOf { genome -> genome.fitness } <= pointer){
                j++
            }
            parents.add(population[j])
        }

        return parents
    }
}