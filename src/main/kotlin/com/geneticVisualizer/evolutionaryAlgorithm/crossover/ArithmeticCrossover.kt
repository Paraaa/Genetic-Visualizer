package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import kotlin.random.Random


/**
 * Implements the one point crossover logic
 * @author Andrej Schwanke
 */
class ArithmeticCrossover: CrossoverStrategy() {

    /**
     * Function to execute the one point crossover operation
     * @param parentPopulation List of parents which have to be mated to produce a child population
     * @param amount Determines the amount of children which have to be produced
     * @return Return a list of genomes
     */
    override fun crossover(parentPopulation: MutableList<GenomeStrategy>, amount: Int): MutableList<GenomeStrategy> {
        if(parentPopulation.size == 0){ return mutableListOf() }
        var childPopulation: MutableList<GenomeStrategy> = mutableListOf()
        parentPopulation.shuffle()
        for(i in 0 until amount){
            var parent1: GenomeStrategy = parentPopulation.random()
            var parent2: GenomeStrategy = parentPopulation.random()
            var x: Double = (parent1.X() + parent2.X())/2
            var y: Double = (parent1.Y() + parent2.Y())/2
            var genome = VectorGenome()
            genome.solution = mutableListOf(x,y)
            childPopulation.add(genome)
        }
        return childPopulation
    }
}