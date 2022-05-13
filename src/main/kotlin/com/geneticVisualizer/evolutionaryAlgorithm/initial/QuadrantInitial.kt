package com.geneticVisualizer.evolutionaryAlgorithm.initial

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random

/**
 * This class produces the initial distribution.
 * @param xLow Defines the lower x bound of the initial distribution
 * @param xUpper Defines the upper x bound of the initial distribution
 * @param yLow Defines the lower y bound of the initial distribution
 * @param yUpper Defines the upper y bound of the initial distribution
 * @author Andrej Schwanke
 */
class QuadrantInitial(var xLow: Double,var xUpper: Double,var yLow: Double, var yUpper: Double): InitialStrategy() {
    /**
     * Executes the quadrant distribution. Genomes will be generated in a certain region of the solution space.
     * @param genomeStrategy Defines what type of genomes have to be generated
     * @param mu Defines the amount of genomes to generate
     * @return Returns a list of genomes of type [genomeStrategy] spaced in one region of the solution space
     */
    override fun initialize(genomeStrategy: GenomeStrategy, mu: Int): MutableList<GenomeStrategy> {
        var initialPopulation: MutableList<GenomeStrategy> = mutableListOf()
        for(p in 0 until mu){
            var x = Random.nextDouble(xLow,xUpper)
            var y = Random.nextDouble(yLow,yUpper)
            var genome = genomeStrategy.createGenome(x,y)
            initialPopulation.add(genome)
        }
        return initialPopulation
    }
}