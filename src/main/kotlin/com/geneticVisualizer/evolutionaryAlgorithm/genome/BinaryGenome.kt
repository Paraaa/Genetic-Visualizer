package com.geneticVisualizer.evolutionaryAlgorithm.genome

import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.OptimizationProblemStrategy


/**
 * Implements a binary representation of the genome
 * @author Andrej Schwanke
 */
class BinaryGenome: GenomeStrategy() {

	/**
	 * Creates a new empty binary genome
	 * @return Returns an empty binary genome
	 */
	override fun createEmptyGenome(): GenomeStrategy {
		return BinaryGenome()
	}
	/**
	 * Creates a new binary genome
	 * @param x x coordinate of the solution
	 * @param y y coordinate of the solution
	 * @return Returns a binary genome with a bit string representation of the parameters [x] [y]
	 */
	override fun createGenome(x: Double, y: Double): GenomeStrategy {
		var genome = BinaryGenome()
		genome.solution.addAll(toBin(x.toInt()))
		genome.solution.addAll(toBin(y.toInt()))
		return genome
	}
	/**
	 * Calculates the fitness of the genomes based on the result of the current optimization problem.
	 * Before the calculation the representation is converted back to a numerical representation.
	 * @param goal determines whether it is a minimization or maximization problem @see[Goals]
	 * @param problem defines which optimization problem is currently used. The result of the calculation is
	 * corresponding to the fitness of the genome.
	 */
	override fun calcFitness(goal: Int, problem: OptimizationProblemStrategy) {
		var x = toNum(solution.subList(0,32)).toDouble()
		var y = toNum(solution.subList(32,64)).toDouble()
		fitness = goal * problem.calculate(x,y)
	}
	/**
	 * Returns the x coordinate of the solution by converting the binary representation back
	 * to numerical one.
	 * @return return the x coordinate of the solution
	 */
	override fun X(): Double {
		return toNum(solution.subList(0, 32)).toDouble()
	}
	/**
	 * Returns the y coordinate of the solution by converting the binary representation back
	 * to numerical one.
	 * @return return the y coordinate of the solution
	 */
	override fun Y(): Double {
		return toNum(solution.subList(32,64)).toDouble()
	}

	/**
	 * Function to convert a [value] into a binary representation. In this case the bits
	 * are stored as double value inside a list. This is needed to keep the usage of the strategy
	 * pattern.
	 * @param value value which is converted to a binary representation
	 */
	private fun toBin(value: Int): MutableList<Double> {
		var binaryString = Integer.toBinaryString(value).padStart(32,'0')
		var binary: MutableList<Double> = mutableListOf()
		binaryString.forEach { bit ->
			if(bit == '1'){
				binary.add(1.0)
			} else {
				binary.add(0.0)
			}
		}
		return binary
	}

	/**
	 * Function to convert a binary representation back to an integer representation.
	 * @param binary The binary representation convert back to an interger
	 * @return returns a numerical representation of the list.
	 */
	private fun toNum(binary: MutableList<Double>): Int {
		var binaryString: MutableList<Char> = mutableListOf()
		binary.forEach { bit ->
			if(bit == 1.0) {
				binaryString.add('1')
			} else {
				binaryString.add('0')
			}
		}
		return Integer.parseUnsignedInt(binaryString.joinToString(separator = ""), 2)
	}
}