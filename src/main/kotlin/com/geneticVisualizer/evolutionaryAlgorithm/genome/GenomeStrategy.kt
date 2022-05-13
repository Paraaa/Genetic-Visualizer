package com.geneticVisualizer.evolutionaryAlgorithm.genome

import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.OptimizationProblemStrategy

/**
 * This class is used to implement the strategy pattern.
 * Enable to swap the genome type in the runtime.
 * @author Andrej Schwanke
 */
open class GenomeStrategy{
    /**
     * Variable to hold the fitness of a genome
     */
    var fitness: Double = 0.0
    /**
     * Defines the step size in self adaptation mutation
     */
    var sigma: Double = 1.0
    /**
     * Defines how old the genomes is
     */
     var age: Int = 0

    /**
     * This is the representation i.e. DNA of the genome
     */
    open var solution: MutableList<Double> = mutableListOf()

    /**
     * The inheritance of this class override this function.
     * @return In the case this class is instantiated an empty [GenomeStrategy] is returned
     */
    open fun createEmptyGenome(): GenomeStrategy { return GenomeStrategy() }

    /**
     * The inheritance of this class override this function.
     * @param x x coordinate of the solution
     * @param y y coordinate of the solution
     * @return In the case this class is instantiated an empty [GenomeStrategy] is returned
     */
    open fun createGenome(x: Double, y: Double): GenomeStrategy { return GenomeStrategy() }

    /**
     * The inheritance of this class override this function. Calculated the fitnes
     * of the genome.
     */
    open fun calcFitness(goal: Int, problem: OptimizationProblemStrategy){ }

    /**
     * The inheritance of this class override this function. Returns the x coordinate of the
     * solution.
     * @return In the case this class is instantiated 0.0 is returned
     */
    open fun X(): Double { return 0.0 }

    /**
     * The inheritance of this class override this function.Returns the y coordinate of the
     * solution.
     * @return In the case this class is instantiated 0.0 is returned
     */
    open fun Y(): Double { return 0.0 }
}