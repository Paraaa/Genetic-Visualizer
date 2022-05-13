package com.geneticVisualizer.evolutionaryAlgorithm.initial

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.math.sqrt


/**
 * Implements the equidistant distribution
 * @author Andrej Schwanke
 */
class EquidistantInitial(var width: Double, var height: Double): InitialStrategy() {

    /**
     * Executes the equidistant distribution. Genomes generated will be equally spaced in the solution space.
     * @param genomeStrategy Defines what type of genomes have to be generated
     * @param mu Defines the amount of genomes to generate
     * @return Returns a list of genomes of type [genomeStrategy] equally spaced in the solution space
     */
    override fun initialize(genomeStrategy: GenomeStrategy, mu: Int): MutableList<GenomeStrategy> {
        var initialPopulation: MutableList<GenomeStrategy> = mutableListOf()


        var columns: Int = sqrt(mu.toDouble()).toInt() + 1
        var rows: Int = columns

        var deltaX: Double = width / columns
        var deltaY: Double = height / columns
        for(x in 0 until columns){
            for(y in 0 until rows){
                var genome = genomeStrategy.createGenome(-(width/2) + x * deltaX,-(height/2) + y * deltaY)
                initialPopulation.add(genome)
            }
        }
        return initialPopulation//.subList(0,mu)
    }
}