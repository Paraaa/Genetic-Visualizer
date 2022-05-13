package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy

/**
 * Implements the age selection logic
 * @param threshold Determines how old a genome can get. If it is older it will not be selected unless the size of
 * the parent population is less than the desired amount
 * @author Andrej Schwanke
 */
class AgeSelection(var threshold: Int): SelectionStrategy() {
    /**
     * Function to execute the age selection operation
     * @param population List of genomes which have to be selected for the next population
     * @param amount Determines the amount of genomes which have to be selected
     * @return Return a list of genomes
     */
    override fun selection(population: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        var parents: MutableList<GenomeStrategy> = mutableListOf()


        population.sortByDescending { genome -> genome.age }

        var overThreshold = population.filter {genome -> genome.age >= threshold }.toMutableList()
        var underThreshold = population.filter { genome -> genome.age < threshold}.toMutableList()

        //This makes the selection of the parents random again
        overThreshold.shuffle()
        underThreshold.shuffle()

        for(genome in underThreshold){
            if(parents.size == amount) break
            parents.add(genome)
        }
        for(genome in overThreshold){
            if(parents.size == amount) break
            parents.add(genome)
        }

        return parents
    }
}