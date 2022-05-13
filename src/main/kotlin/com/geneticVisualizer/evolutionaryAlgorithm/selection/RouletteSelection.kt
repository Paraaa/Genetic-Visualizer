package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random

/**
 * Implements the roulette selection logic
 * @author Andrej Schwanke
 */
class  RouletteSelection: SelectionStrategy() {
    /**
     * Function to execute the one roulette selection operation. The implementation is based on the following
     * implementation of Konstantin Lukanschenko. It has been altered to fit the structure of this project
     * to return a list of genomes instead of just on genome.
     * @see [https://github.com/KonstantinLukaschenko/genetic-algorithm-kotlin/blob/master/src/Selection.kt]
     * @param population List of genomes which have to be selected for the next population
     * @param amount Determines the amount of genomes which have to be selected
     * @return Return a list of genomes
     */
    override fun selection(population: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        var parents: MutableList<GenomeStrategy> = mutableListOf()
        population.sortBy { genome: GenomeStrategy -> genome.fitness }
        for(index in 0 until amount){
            var sumFitness = population.sumOf { genome -> genome.fitness } * Random.nextDouble()
            for(genome in population){
                sumFitness -= genome.fitness
                if(sumFitness <= 0){
                    parents.add(genome)
                    population.remove(genome)
                    break
                }
            }
        }
        return parents
    }
}