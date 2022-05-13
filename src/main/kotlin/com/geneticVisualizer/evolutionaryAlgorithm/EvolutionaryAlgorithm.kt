package com.geneticVisualizer.evolutionaryAlgorithm

import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.crossover.CrossoverStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.crossover.OnePointCrossover
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.InitialStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import com.geneticVisualizer.evolutionaryAlgorithm.mutation.GaussMutation
import com.geneticVisualizer.evolutionaryAlgorithm.mutation.MutationStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.OptimizationProblemStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.Sphere
import com.geneticVisualizer.evolutionaryAlgorithm.selection.RouletteSelection
import com.geneticVisualizer.evolutionaryAlgorithm.selection.SelectionStrategy
import mu.KotlinLogging
import kotlin.math.abs


/**
 * This is class is executing the evolutionary algorithm. It also is the model in the MVP-Pattern.
 * @author Andrej Schwanke
 */
class EvolutionaryAlgorithm {
    private val LOG = KotlinLogging.logger {}
    /**
     * Specifies the amount of iterations the EA can try to solve the optimization problem
     * Default: 20
     */
    var maxIterations: Int = 20
    /**
     * determines whether it is a minimization (goal = -1) or maximization (goal = 1) problem
     */
    var goal: Int = 1
    /**
     * Size of the population
     * Default: 100
     */
    var mu: Int = 100
    /**
     * Amount of children after recombination and mutation
     * Default: 200
     */
    var lambda: Int = 200
    /**
     * Amount of parents selected for mating
     * Default: 100
     */
    var parentSize: Int = 100
    /**
     * Holds information about which optimization problem is currently selected
     */
    var optimizationProblem: OptimizationProblemStrategy = Sphere()
    /**
     * Holds information about which selection operator is currently selected
     */
    var parentSelection : SelectionStrategy = RouletteSelection()
    /**
     * Holds information about which crossover operator is currently selected
     */
    var crossover : CrossoverStrategy = OnePointCrossover()
    /**
     * Holds information about which mutation operator is currently selected
     */
    var mutation : MutationStrategy = GaussMutation(0.5)
    /**
     * Holds information about which survivor selection operator is currently selected
     */
    var survivorSelection: SelectionStrategy = RouletteSelection()
    /**
     * Holds information about which genome strategy is currently selected
     */
    var genomeStrategy: GenomeStrategy = VectorGenome()
    /**
     * Holds information about which initial strategy is currently selected
     */
    var initialStrategy: InitialStrategy = QuadrantInitial(-465.0,465.5,-335.0,335.0)

    /**
     * Determines whether crossover should be enabled or disabled
     */
    var crossoverEnabled: Boolean = true
    /**
     * Determines whether mutation should be enabled or disabled
     */
    var mutationEnabled: Boolean = true

    /**
     * Determines whether is it a plus selection of a comma selection
     * true -> plus selection
     * false -> comma selection
     */
    var plusSelection: Boolean = true

    /**
     * Determines the amount of parent elitism. If it is zero there is
     * no elitism
     * Default: 1
     */
    var parentElitism: Int = 1
    /**
     * Determines the amount of survivor elitism. If it is zero there is
     * no elitism
     * Default: 1
     */
    var survivorElitism: Int = 1
    /**
     * Determines the mutation probability.
     * Default: 0.2
     */
    var mutationProbability: Double = 0.2

    /**
     * This is the list of all calculated generations by the evolutionary algorithm.
     * Each item is of type [Generation]
     */
    private var generations: MutableList<Generation> = mutableListOf()

    /**
     * Function to execute the evolutionary algorithm. The function iterates until [maxIterations] is reached.
     * In each iteration it determines the parent population, parent elite, crossover population, mutated population,
     * survivor population and the survivor elite. Those sets are then stored in a new [Generation] object which is
     * stored in a list of generations.
     * @return Return a List of generation which is used in the view to represent the evolutionary process.
     */
    fun solve(): MutableList<Generation> {
        generations.clear()

        /**
         * Add initial random population to the generations.
         */
        LOG.debug { "Initializing first population" }
        var currentGeneration: MutableList<GenomeStrategy> = initialStrategy.initialize(genomeStrategy, mu)
        calcFitnessOfPopulation(currentGeneration)

        for(i in 0 until maxIterations) {
            LOG.debug { "###################### Generation $i ######################" }

            var generation = Generation()
            generation.number = i
            generation.previousPopulation.addAll(currentGeneration)


            var nextGeneration: MutableList<GenomeStrategy> = mutableListOf()
            var parentPopulation: MutableList<GenomeStrategy> = mutableListOf()
            var childPopulation: MutableList<GenomeStrategy> = mutableListOf()
            var parentsAndChildren: MutableList<GenomeStrategy> = mutableListOf()
            var selectedSurvivor: MutableList<GenomeStrategy> = mutableListOf()

            if(parentElitism > 0){
                currentGeneration.sortByDescending { genome -> genome.fitness }
                for(pE in 0 until parentElitism){
                    parentPopulation.add(currentGeneration[0])
                    generation.parentElite.add(currentGeneration[0])
                    currentGeneration.removeAt(0)
                }
                LOG.debug { "Using parent elitism with size: ${parentPopulation.size}. Expected: $parentElitism" }
            }
            parentPopulation.addAll(parentSelection.selection(currentGeneration,parentSize-parentElitism))
            generation.parentPopulation.addAll(parentPopulation)
            LOG.debug { "Created: parent population with size ${parentPopulation.size}. Expected $parentSize"}

            if(crossoverEnabled && mutationEnabled) {
                LOG.debug { "Crossover and Mutation enabled: " }
                var crossoverChildPopulation = crossover.crossover(parentPopulation, lambda)
                generation.crossoverPopulation.addAll(crossoverChildPopulation)
                LOG.debug { "Size of child population after crossover: ${crossoverChildPopulation.size}. Expected: $lambda" }
                childPopulation =  mutation.mutation(crossoverChildPopulation,mutationProbability)
                generation.populationWithoutMutatedChildren.addAll(crossoverChildPopulation.minus(crossoverChildPopulation.minus(childPopulation)))
                generation.mutatedPopulation.addAll(childPopulation.minus(crossoverChildPopulation))
                LOG.debug { "Amount of mutated children is: ${generation.mutatedPopulation.size} with probability: $mutationProbability" }
            } else if(crossoverEnabled && !mutationEnabled){
                LOG.debug { "Crossover enabled and Mutation disabled: " }
                childPopulation =  crossover.crossover(parentPopulation, lambda)
                generation.crossoverPopulation.addAll(childPopulation)
                LOG.debug { "Size of child population after crossover: ${childPopulation.size}. Expected: $lambda" }
            } else if(!crossoverEnabled && mutationEnabled){
                LOG.debug { "Crossover disabled and Mutation enabled: " }
                childPopulation = mutation.mutation(parentPopulation,mutationProbability)
                generation.populationWithoutMutatedChildren.addAll(parentPopulation.minus(parentPopulation.minus(childPopulation)))
                generation.mutatedPopulation.addAll(childPopulation.minus(parentPopulation))
                LOG.debug { "Amount of mutated children is: ${generation.mutatedPopulation.size} with probability: $mutationProbability" }
            } else{
                LOG.debug { "Crossover and Mutation disabled: " }
                childPopulation.addAll(parentPopulation)
                LOG.debug { "Size of child population: ${childPopulation.size}. Expected: $parentSize" }
            }

            if(plusSelection){ parentsAndChildren.addAll(parentPopulation) }
            parentsAndChildren.addAll(childPopulation)
            calcFitnessOfPopulation(parentsAndChildren)

            if(survivorElitism > 0){
                parentsAndChildren.sortByDescending { genome -> genome.fitness }
                for(sE in 0 until survivorElitism){
                    selectedSurvivor.add(parentsAndChildren[0])
                    generation.survivorElite.add(parentsAndChildren[0])
                    parentsAndChildren.removeAt(0)
                }
                LOG.debug { "Using survivor elitism with size: ${selectedSurvivor.size}. Expected: $survivorElitism" }
            }
            selectedSurvivor.addAll(survivorSelection.selection(parentsAndChildren,mu-survivorElitism))
            generation.survivorPopulation.addAll(selectedSurvivor)
            LOG.debug { "Size of next generation is: ${selectedSurvivor.size}. Expected: $mu" }

            nextGeneration.addAll(selectedSurvivor)

            incrementAge(nextGeneration)
            currentGeneration = nextGeneration

            generation.calcStatistics(optimizationProblem)
            generations.add(generation)
        }
        return generations
    }

    /**
     * Function to calculate the fitness of each genome in the population. The [GenomeStrategy.calcFitness] function
     * is called for each genome in the population.
     * @param genomes required a list of genomes to call the [GenomeStrategy.calcFitness] function
     */
    private fun calcFitnessOfPopulation(genomes: MutableList<GenomeStrategy>){
        LOG.debug { "Calculating fitness of population" }
        genomes.forEach { genome ->
            genome.calcFitness(goal,optimizationProblem)
        }
        shiftFitness(genomes)
    }

    /**
     * This function shifts the fitness space into the positive realm to get rid of negative fitness values.
     * The worst fitness of the population is determined and then the absolute is applied to all other
     * fitness of the genomes.
     * @param genomes required a list of genomes to shift their fitness.
     */
    private fun shiftFitness(genomes: MutableList<GenomeStrategy>){
        if(genomes.size == 1){
            genomes[0].fitness = abs(genomes[0].fitness) + 0.01
        }
        LOG.debug { "Shifts the fitness of the population" }
        var worstFitness = abs(genomes.sortedBy { genomeStrategy -> genomeStrategy.fitness }[0].fitness)
        genomes.forEach { genome ->
            genome.fitness += worstFitness + 0.01
        }
    }
    /**
     * Increments the age by one
     * @param genomes required a list of genomes to increment their age.
     */
    private fun incrementAge(genomes: MutableList<GenomeStrategy>){
        LOG.debug { "Incrementing age of genomes" }
        genomes.forEach { genome ->
            genome.age += 1
        }
    }
}