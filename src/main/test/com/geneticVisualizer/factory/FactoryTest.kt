package com.geneticVisualizer.factory

import com.geneticVisualizer.evolutionaryAlgorithm.crossover.*
import com.geneticVisualizer.evolutionaryAlgorithm.genome.BinaryGenome
import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.genome.VectorGenome
import com.geneticVisualizer.evolutionaryAlgorithm.initial.EquidistantInitial
import com.geneticVisualizer.evolutionaryAlgorithm.initial.InitialStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.initial.QuadrantInitial
import com.geneticVisualizer.evolutionaryAlgorithm.initial.ZeroInitial
import com.geneticVisualizer.evolutionaryAlgorithm.mutation.*
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.*
import com.geneticVisualizer.evolutionaryAlgorithm.selection.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FactoryTest {

    var testFactory: Factory = Factory(730.0,670.0)

    @Test
    fun changeCrossover() {
        var none = testFactory.changeCrossover("ThisIsNotViable")
        var opc1 = testFactory.changeCrossover("1-Point-Crossover")
        var opc2 = testFactory.changeCrossover("2-Point-Crossover")
        var opc3 = testFactory.changeCrossover("3-Point-Crossover")
        var opc4 = testFactory.changeCrossover("4-Point-Crossover")
        var opc5 = testFactory.changeCrossover("5-Point-Crossover")
        var opc20 = testFactory.changeCrossover("20-Point-Crossover")
        var arit = testFactory.changeCrossover("Arithmetic")
        var opc1arit = testFactory.changeCrossover("1-Point-Arithmetic-Crossover")
        var uniform = testFactory.changeCrossover("Uniform")
        assertTrue(none is CrossoverStrategy)
        assertTrue(opc1 is OnePointCrossover)
        assertTrue(opc2 is KPointCrossover)
        assertTrue(opc3 is KPointCrossover)
        assertTrue(opc4 is KPointCrossover)
        assertTrue(opc5 is KPointCrossover)
        assertTrue(opc20 is KPointCrossover)
        assertTrue(arit is ArithmeticCrossover)
        assertTrue(opc1arit is OnePointArithmeticCrossover)
        assertTrue(uniform is UniformCrossover)
    }

    @Test
    fun changeGenome() {
        var none = testFactory.changeGenome("ThisIsNotViable")
        var vec = testFactory.changeGenome("Vector")
        var bin = testFactory.changeGenome("Binary")
        assertTrue(none is GenomeStrategy)
        assertTrue(vec is VectorGenome)
        assertTrue(bin is BinaryGenome)
    }

    @Test
    fun changeInitialDistribution() {
        var none = testFactory.changeInitialDistribution("ThisIsNotViable")
        var full = testFactory.changeInitialDistribution("Full")
        var TopLeft = testFactory.changeInitialDistribution("Top Left")
        var TopRight = testFactory.changeInitialDistribution("Top Right")
        var BottomLeft = testFactory.changeInitialDistribution("Bottom Left")
        var BottomRight = testFactory.changeInitialDistribution("Bottom Right")
        var Middle = testFactory.changeInitialDistribution("Middle")
        var Zero = testFactory.changeInitialDistribution("Zero")
        var Equidistant = testFactory.changeInitialDistribution("Equidistant")
        assertTrue(none is InitialStrategy)
        assertTrue(full is QuadrantInitial)
        assertTrue(TopLeft is QuadrantInitial)
        assertTrue(TopRight is QuadrantInitial)
        assertTrue(BottomLeft is QuadrantInitial)
        assertTrue(BottomRight is QuadrantInitial)
        assertTrue(Middle is QuadrantInitial)
        assertTrue(Zero is ZeroInitial)
        assertTrue(Equidistant is EquidistantInitial)
    }

    @Test
    fun changeMutation() {
        var none = testFactory.changeMutation("ThisIsNotViable")
        var g0 = testFactory.changeMutation("Gauss-Mutation (σ = 0.5)")
        var g1 = testFactory.changeMutation("Gauss-Mutation (σ = 1)")
        var g2 = testFactory.changeMutation("Gauss-Mutation (σ = 2)")
        var g3 = testFactory.changeMutation("Gauss-Mutation (σ = 3)")
        var g4 = testFactory.changeMutation("Gauss-Mutation (σ = 4)")
        var g10 = testFactory.changeMutation("Gauss-Mutation (σ = 10)")
        var self = testFactory.changeMutation("Self adaptation")
        var m2 = testFactory.changeMutation("Multiply 2")
        var m5 = testFactory.changeMutation("Multiply 5")
        var bitfip = testFactory.changeMutation("BitFlip")
        var inversion = testFactory.changeMutation("Inversion")
        var shuffle = testFactory.changeMutation("Shuffle")
        var swap = testFactory.changeMutation("Swap")
        var insert = testFactory.changeMutation("Insert")
        assertTrue(none is MutationStrategy)
        assertTrue(g0 is GaussMutation)
        assertTrue(g1 is GaussMutation)
        assertTrue(g2 is GaussMutation)
        assertTrue(g3 is GaussMutation)
        assertTrue(g4 is GaussMutation)
        assertTrue(g10 is GaussMutation)
        assertTrue(self is SelfAdaptationMutation)
        assertTrue(m2 is MultiplyFactorMutation)
        assertTrue(m5 is MultiplyFactorMutation)
        assertTrue(bitfip is BitFlipMutation)
        assertTrue(inversion is InversionMutation)
        assertTrue(shuffle is ShuffleMutation)
        assertTrue(swap is SwapMutation)
        assertTrue(insert is InsertMutation)
    }

    @Test
    fun changeOptimizationProblem() {
        var none = testFactory.changeOptimizationProblem("ThisIsNotViable")
        var sphere = testFactory.changeOptimizationProblem("Sphere")
        var sphereAbs = testFactory.changeOptimizationProblem("SphereAbs")
        var hyppar = testFactory.changeOptimizationProblem("Hyperbolic Paraboloid")
        var abshyppar = testFactory.changeOptimizationProblem("Abs Hyperbolic Paraboloid")
        var bumps = testFactory.changeOptimizationProblem("Bumps")
        var sombrero = testFactory.changeOptimizationProblem("Sombrero")
        var negnevitsky = testFactory.changeOptimizationProblem("Negnevitsky")
        var rosenbrock = testFactory.changeOptimizationProblem("Rosenbrock")
        var step = testFactory.changeOptimizationProblem("Step")
        var beale = testFactory.changeOptimizationProblem("Beale")
        var booth = testFactory.changeOptimizationProblem("Booth")
        var boothMAX = testFactory.changeOptimizationProblem("BoothMAX")
        var akley = testFactory.changeOptimizationProblem("Akley")
        var fletcherPowell = testFactory.changeOptimizationProblem("FletcherPowell")
        var plainWithSpike = testFactory.changeOptimizationProblem("PlainWithSpike")
        var rastrigin = testFactory.changeOptimizationProblem("Rastrigin")
        var schwefel = testFactory.changeOptimizationProblem("Schwefel")
        assertTrue(none is OptimizationProblemStrategy)
        assertTrue(sphere is Sphere)
        assertTrue(sphereAbs is SphereAbs)
        assertTrue(hyppar is HyperbolicParaboloid)
        assertTrue(abshyppar is AbsHyperbolicParaboloid)
        assertTrue(bumps is Bumps)
        assertTrue(sombrero is Sombrero)
        assertTrue(negnevitsky is Negnevitsky)
        assertTrue(rosenbrock is Rosenbrock)
        assertTrue(step is Step)
        assertTrue(beale is Beale)
        assertTrue(booth is Booth)
        assertTrue(boothMAX is BoothMAX)
        assertTrue(akley is Akley)
        assertTrue(fletcherPowell is FletcherPowell)
        assertTrue(plainWithSpike is PlainWithSpike)
        assertTrue(rastrigin is Rastrigin)
        assertTrue(schwefel is Schwefel)
    }

    @Test
    fun changeSelection() {
        var none = testFactory.changeSelection("ThisIsNotViable")
        var roulette = testFactory.changeSelection("Roulette")
        var t2 = testFactory.changeSelection("Tournament (K = 2)")
        var t3 = testFactory.changeSelection("Tournament (K = 3)")
        var t5 = testFactory.changeSelection("Tournament (K = 5)")
        var t10 = testFactory.changeSelection("Tournament (K = 10)")
        var a1 = testFactory.changeSelection("Age (T=1)")
        var a3 = testFactory.changeSelection("Age (T=3)")
        var a5 = testFactory.changeSelection("Age (T=5)")
        var a10 = testFactory.changeSelection("Age (T=10)")
        var a20 = testFactory.changeSelection("Age (T=20)")
        var l11 = testFactory.changeSelection("Linear Ranking (η+ = 1.1)")
        var l15 = testFactory.changeSelection("Linear Ranking (η+ = 1.5)")
        var l18 = testFactory.changeSelection("Linear Ranking (η+ = 1.5)")
        var l20 = testFactory.changeSelection("Linear Ranking (η+ = 2.0)")
        var sus = testFactory.changeSelection("Stochastic universal sampling")
        var random = testFactory.changeSelection("Random")
        assertTrue(none is SelectionStrategy)
        assertTrue(roulette is RouletteSelection)
        assertTrue(t2 is TournamentSelection)
        assertTrue(t3 is TournamentSelection)
        assertTrue(t5 is TournamentSelection)
        assertTrue(t10 is TournamentSelection)
        assertTrue(a1 is AgeSelection)
        assertTrue(a3 is AgeSelection)
        assertTrue(a5 is AgeSelection)
        assertTrue(a10 is AgeSelection)
        assertTrue(a20 is AgeSelection)
        assertTrue(l11 is LinearRankingSelection)
        assertTrue(l15 is LinearRankingSelection)
        assertTrue(l18 is LinearRankingSelection)
        assertTrue(l20 is LinearRankingSelection)
        assertTrue(sus is StochasticUniversalSamplingSelection)
        assertTrue(random is RandomSelection)
    }
}