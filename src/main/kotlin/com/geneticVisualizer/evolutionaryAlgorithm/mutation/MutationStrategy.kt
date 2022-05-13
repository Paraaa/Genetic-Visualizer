package com.geneticVisualizer.evolutionaryAlgorithm.mutation

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
/**
 * This class is used to implement the strategy pattern.
 * Enable to swap mutation operator in the runtime.
 * @author Andrej Schwanke
 */
open class MutationStrategy {
    /**
     * The inheritance of this class override this function.
     * @return In the case this class is instantiated an empty list is returned.
     */
    open fun mutation(childPopulation: MutableList<GenomeStrategy>, probability: Double = 1.0): MutableList<GenomeStrategy>{
        return mutableListOf()
    }
}