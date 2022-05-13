package com.geneticVisualizer.evolutionaryAlgorithm.genome

import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.OptimizationProblemStrategy


/**
 * Implements a vector representation of the genome
 * @author Andrej Schwanke
 */
class VectorGenome: GenomeStrategy() {
	/**
	 * Creates a new empty vector genome
	 * @return Returns an empty vector genome
	 */
	override fun createEmptyGenome(): GenomeStrategy {
		return VectorGenome()
	}
	/**
	 * Creates a new vector genome
	 * @param x x coordinate of the solution
	 * @param y y coordinate of the solution
	 * @return Returns a vector genome with the given parameters [x] [y] as its DNS
	 */
	override fun createGenome(x: Double, y: Double): GenomeStrategy {
		var genome = VectorGenome()
		genome.solution = mutableListOf(x,y)
		return genome
	}

	/**
	 * Calculates the fitness of the genomes based on the result of the current optimization problem
	 * @param goal determines whether it is a minimization or maximization problem @see[Goals]
	 * @param problem defines which optimization problem is currently used. The result of the calculation is
	 * corresponding to the fitness of the genome.
	 */
	override fun calcFitness(goal: Int, problem: OptimizationProblemStrategy) {
		this.fitness = goal * problem.calculate(solution[0], solution[1])
	}
	/**
	 * Returns the x coordinate of the solution.
	 * @return returns the first position of the [solution] i.e. DNS of the genome
	 */
	override fun X(): Double {
		return solution[0]
	}
	/**
	 * Returns the y coordinate of the solution.
	 * @return returns the second position of the [solution] i.e. DNS of the genome
	 */
	override fun Y(): Double {
		return solution[1]
	}
}
