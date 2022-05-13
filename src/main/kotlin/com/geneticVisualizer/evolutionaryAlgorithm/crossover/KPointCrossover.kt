package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random

/**
 * Implements the KPointCrossover crossover logic
 * @param K defines the amount of crossover points used to recombine the solutions
 * @author Andrej Schwanke
 */
class KPointCrossover(var K: Int): CrossoverStrategy() {

    /**
     * Function to execute the K point crossover operation.
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
            var child = parent1.createEmptyGenome()

            var crossoverPoints = defineCrossoverPoint(parentPopulation[0].solution.size)
            var solution: MutableList<Double> = mutableListOf()
            crossoverPoints.forEachIndexed { index, distance ->
                if(index % 2 == 0){
                    solution.addAll(parent1.solution.subList(distance.first,distance.second))
                } else {
                    solution.addAll(parent2.solution.subList(distance.first,distance.second))
                }
            }
            child.solution = solution
            childPopulation.add(child)
            return childPopulation
        }


        var crossoverPoints = defineCrossoverPoint(parentPopulation[0].solution.size)
        for(i in 0 until amount/2){ //Divide amount by factor 2 -> One point crossover creates two children
            var parent1: GenomeStrategy = parentPopulation.random()
            var parent2: GenomeStrategy = parentPopulation.random()

            var childOne: GenomeStrategy = parent1.createEmptyGenome()
            var childTwo: GenomeStrategy = parent1.createEmptyGenome()

            var solution1: MutableList<Double> = mutableListOf()
            var solution2: MutableList<Double> = mutableListOf()

            crossoverPoints.forEachIndexed { index, distance ->
                if(index % 2 == 0){
                    solution1.addAll(parent1.solution.subList(distance.first,distance.second))
                    solution2.addAll(parent2.solution.subList(distance.first,distance.second))
                } else {
                    solution1.addAll(parent2.solution.subList(distance.first,distance.second))
                    solution2.addAll(parent1.solution.subList(distance.first,distance.second))
                }
            }
            childOne.solution = solution1
            childTwo.solution = solution2
            childPopulation.add(childOne)
            childPopulation.add(childTwo)
        }
        return childPopulation
    }

    /**
     * Helper function to determine the position of the crossover points for a given K.
     * @param solutionSize The size of the dns of the genome
     * @return Returns a list of Pairs. The first index of the pair corresponds to the beginning of the interval
     * and the second one to the end of the interval i.e. Pair<fromIndex,toIndex>
     */
    private fun defineCrossoverPoint(solutionSize: Int): MutableList<Pair<Int,Int>>{
        var crossOverPointsLocation: MutableList<Pair<Int,Int>> = mutableListOf()

        var from = 0
        (0 .. K).forEach { index ->
            if(index != K){
                var to = Random.nextInt(from,solutionSize)
                crossOverPointsLocation.add(Pair(from,to))
                from = to
            } else {
                crossOverPointsLocation.add(Pair(from, solutionSize))
            }
        }
        return crossOverPointsLocation
    }
}