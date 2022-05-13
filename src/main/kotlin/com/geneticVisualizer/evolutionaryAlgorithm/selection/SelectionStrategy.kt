package com.geneticVisualizer.evolutionaryAlgorithm.selection

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
/**
 * This class is used to implement the strategy pattern.
 * Enable to swap selection operator in the runtime.
 * @author Andrej Schwanke
 */
open class SelectionStrategy {
    /**
     * The inheritance of this class override this function.
     * @return In the case this class is instantiated an empty list is returned.
     */
    open fun selection(population: MutableList<GenomeStrategy>, amount: Int = 0): MutableList<GenomeStrategy> {
        return mutableListOf()
    }
}