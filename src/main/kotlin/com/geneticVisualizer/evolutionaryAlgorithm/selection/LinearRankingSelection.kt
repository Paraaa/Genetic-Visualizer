package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random

/**
 * Implements the linear ranking selection logic
 * @param nPlus: Factor to determine the selection probability
 * @author Andrej Schwanke
 */
class LinearRankingSelection(var nPlus: Double): SelectionStrategy() {
    private val nMinus = 2 - nPlus
    /**
     * Function to execute the one linear ranking selection operation
     * @param population List of genomes which have to be selected for the next population
     * @param amount Determines the amount of genomes which have to be selected
     * @return Return a list of genomes
     */
    override fun selection(population: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        var parents: MutableList<GenomeStrategy> = mutableListOf()

        population.sortByDescending { genome -> genome.fitness  } //BestFitness ... worstFitness

        while(parents.size < amount) {
            for(item in population.withIndex()){
                var probability = (nPlus - ((nPlus - nMinus) * (item.index - 1)) / population.size - 1) / population.size
                if (probability <= Random.nextDouble()){
                    if(parents.size >= amount) break
                    parents.add(item.value)
                }
            }
        }

        return parents
    }
}