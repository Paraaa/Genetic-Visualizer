package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random
/**
 * Implements the random selection logic
 * @author Andrej Schwanke
 */
class RandomSelection: SelectionStrategy() {
    /**
     * Function to execute the one random selection operation
     * @param population List of genomes which have to be selected for the next population
     * @param amount Determines the amount of genomes which have to be selected
     * @return Return a list of genomes
     */
    override fun selection(population: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        population.shuffle()
        var parentSelection: MutableList<GenomeStrategy> = mutableListOf()

        for(i in 0 until amount){
            if(population.isEmpty()) break;
            var genome = population.random()
            parentSelection.add(genome)
            population.remove(genome)
        }
        return parentSelection
    }
}