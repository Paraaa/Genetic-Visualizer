package com.geneticVisualizer.evolutionaryAlgorithm.crossover

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy


/**
 * This class is used to implement the strategy pattern.
 * Enable to swap crossover operator in the runtime.
 * @author Andrej Schwanke
 */
open class CrossoverStrategy {

    /**
     * The inheritance of this class override this function.
     * @return In the case this class is instantiated an empty list is returned.
     */
    open fun crossover(parentPopulation: MutableList<GenomeStrategy>, amount: Int = 0): MutableList<GenomeStrategy> {
        return mutableListOf()
    }
}