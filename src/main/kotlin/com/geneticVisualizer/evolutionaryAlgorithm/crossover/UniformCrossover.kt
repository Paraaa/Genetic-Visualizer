package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy

/**
 * Implements the uniform crossover logic
 * @author Andrej Schwanke
 */
class UniformCrossover: CrossoverStrategy() {
    /**
     * Function to execute the uniform crossover operation
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
            var mask: MutableList<Int> = mutableListOf()
            parent1.solution.forEach { _ -> mask.add(mutableListOf(0,1).random()) }
            var solution1 = mutableListOf<Double>()
            parent1.solution.forEachIndexed { index, bit ->
                if(mask[index] == 0){
                    solution1.add(bit)
                } else {
                    solution1.add(parent2.solution[index])
                }
            }
            var genome1 = BinaryGenome()
            genome1.solution = solution1
            childPopulation.add(genome1)
            return childPopulation
        }

        for(i in 0 until amount/2){
            var parent1: GenomeStrategy = parentPopulation.random()
            var parent2: GenomeStrategy = parentPopulation.random()

            var mask: MutableList<Int> = mutableListOf()
            parent1.solution.forEach { _ -> mask.add(mutableListOf(0,1).random()) }

            var solution1 = mutableListOf<Double>()
            var solution2 = mutableListOf<Double>()

            parent1.solution.forEachIndexed { index, bit ->
                if(mask[index] == 0){
                    solution1.add(bit)
                    solution2.add(parent2.solution[index])
                } else {
                    solution2.add(bit)
                    solution1.add(parent2.solution[index])
                }
            }
            var genome1 = BinaryGenome()
            var genome2 = BinaryGenome()
            genome1.solution = solution1
            genome2.solution = solution2
            childPopulation.add(genome1)
            childPopulation.add(genome2)
        }

        return childPopulation
    }
}