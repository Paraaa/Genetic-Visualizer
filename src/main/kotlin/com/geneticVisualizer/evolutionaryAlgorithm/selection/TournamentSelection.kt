package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy

/**
 * Implements the tournament selection logic
 * @author Andrej Schwanke
 */
class TournamentSelection(var tournamentSize: Int): SelectionStrategy() {
    /**
     * Function to execute the one tournament selection operation
     * @param population List of genomes which have to be selected for the next population
     * @param amount Determines the amount of genomes which have to be selected
     * @return Return a list of genomes
     */
    override fun selection(population: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        population.shuffle()
        var parents: MutableList<GenomeStrategy> = mutableListOf()

        if(population.size < tournamentSize){
            parents.addAll(population)
            return parents
        }

        for(it in 0 until amount){
            if(population.isEmpty()) break;
            var tournament: MutableList<GenomeStrategy> = mutableListOf()
            for(tS in 0 until tournamentSize){
                tournament.add(population.random())
            }
            tournament.sortByDescending { genome -> genome.fitness }
            parents.add(tournament[0])
            population.remove(tournament[0])
        }
        return parents
    }
}