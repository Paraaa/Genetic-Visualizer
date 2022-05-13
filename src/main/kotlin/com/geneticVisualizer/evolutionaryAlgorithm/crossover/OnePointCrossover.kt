package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy


/**
 * Implements the arithmetic crossover logic
 * @author Andrej Schwanke
 */
class OnePointCrossover: CrossoverStrategy() {
    /**
     * Function to execute the arithmetic crossover operation
     * @param parentPopulation List of parents which have to be mated to produce a child population
     * @param amount Determines the amount of children which have to be produced
     * @return Return a list of genomes
     */
    override fun crossover(parentPopulation: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        if(parentPopulation.size == 0){ return mutableListOf() }
        var childPopulation: MutableList<GenomeStrategy> = mutableListOf()
        parentPopulation.shuffle()

        if(amount == 1){
            var parent1: GenomeStrategy = parentPopulation.random()
            var parent2: GenomeStrategy = parentPopulation.random()
            var child = parent1.createGenome(parent1.X(),parent2.Y())
            childPopulation.add(child)
            return childPopulation
        }

        for(i in 0 until amount/2){ //Divide amount by factor 2 -> One point crossover creates two children
            var parent1: GenomeStrategy = parentPopulation.random()
            var parent2: GenomeStrategy = parentPopulation.random()

            var childOne = parent1.createGenome(parent1.X(), parent2.Y())
            var childTwo = parent1.createGenome(parent2.X(), parent1.Y())

            childPopulation.add(childOne)
            childPopulation.add(childTwo)
        }

        return childPopulation
    }
}