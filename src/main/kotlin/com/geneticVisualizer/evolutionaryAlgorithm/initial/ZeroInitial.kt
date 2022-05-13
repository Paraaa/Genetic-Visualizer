package com.geneticVisualizer.evolutionaryAlgorithm.initial

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy

/**
 * Implements the zero "distribution"
 * @author Andrej Schwanke
 */
class ZeroInitial: InitialStrategy() {
    /**
     * Initializes the population with each genome starting at x=0 y=0.
     * @param genomeStrategy Defines what type of genomes have to be generated
     * @param mu Defines the amount of genomes to generate
     * @return Returns a list of genomes of type [genomeStrategy] equally spaced in the solution space
     */
    override fun initialize(genomeStrategy: GenomeStrategy, mu: Int): MutableList<GenomeStrategy> {
        var initialPopulation: MutableList<GenomeStrategy> = mutableListOf()
        for(p in 0 until mu){
            var genome = genomeStrategy.createGenome(0.0,0.0)
            initialPopulation.add(genome)
        }
        return initialPopulation
    }

}