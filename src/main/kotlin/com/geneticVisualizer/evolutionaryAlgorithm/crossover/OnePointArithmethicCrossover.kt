package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy

/**
 * Implements the one point arithmetic crossover logic
 * @author Andrej Schwanke
 */
class OnePointArithmeticCrossover: CrossoverStrategy() {
    /**
     * Function to execute the one point arithmetic crossover operation
     * @param parentPopulation List of parents which have to be mated to produce a child population
     * @param amount Determines the amount of children which have to be produced
     * @return Return a list of genomes
     */
    override fun crossover(parentPopulation: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        if(parentPopulation.size == 0){ return mutableListOf() }
        var childPopulation: MutableList<GenomeStrategy> = mutableListOf()

        if(amount == 1){
            var parent1: GenomeStrategy = parentPopulation.random()
            var parent2: GenomeStrategy = parentPopulation.random()
            var child = parent1.createGenome(parent1.X(),parent2.Y())
            childPopulation.add(child)
            return childPopulation
        }
        for(i in 0 until amount/2){
            var parent1 = parentPopulation.random()
            var parent2 = parentPopulation.random()

            //Create one crossover child
            var onePointCrossoverChild = parent1.createGenome(parent1.X(),parent2.Y())
            //Create arithmetic crossover child
            var xMean = (parent1.X() + parent2.X()) / 2
            var yMean  = (parent1.Y() + parent2.Y()) / 2
            var arithmeticCrossoverChild = parent1.createGenome(xMean,yMean)
            childPopulation.add(onePointCrossoverChild)
            childPopulation.add(arithmeticCrossoverChild)
        }
        return childPopulation
    }
}