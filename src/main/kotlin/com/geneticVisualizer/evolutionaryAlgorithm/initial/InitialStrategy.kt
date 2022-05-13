package com.geneticVisualizer.evolutionaryAlgorithm.initial

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import kotlin.random.Random


/**
 * This class is used to implement the strategy pattern. It is used to implement different initial states
 * of the evolutionary algorithm.
 * @author Andrej Schwanke
 */
open class InitialStrategy {
    /**
     * The inheritance of this class override this function.
     * @param genomeStrategy Defines what type of genomes have to be generated
     * @param mu Defines the amount of genomes to generate
     * @return In the case this class is instantiated an empty list of type [GenomeStrategy]
     */
    open fun initialize(genomeStrategy: GenomeStrategy, mu: Int): MutableList<GenomeStrategy>{
        return mutableListOf()
    }
}