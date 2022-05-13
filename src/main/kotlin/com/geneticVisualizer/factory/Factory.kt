package com.geneticVisualizer.factory

import com.geneticVisualizer.enums.*
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
import mu.KotlinLogging

/**
 * Handles the creation of objects dependent on the user input.
 * @author Andrej Schwanke
 */
class Factory(var width: Double, var height: Double) {
    private val LOG = KotlinLogging.logger {}
    /**
     * @param crossover string to determine which crossover operator has to be used
     * @return Returns an object of type @see[CrossoverStrategy]. An empty [CrossoverStrategy] is
     * returned if [crossover] is unknown.
     */
    fun changeCrossover(crossover: String): CrossoverStrategy {
        when(crossover){
            Crossover.OnePointCrossover.guiName -> return OnePointCrossover()
            Crossover.TwoPointCrossover.guiName -> return KPointCrossover(2)
            Crossover.ThreePointCrossover.guiName -> return KPointCrossover(3)
            Crossover.FourTwoPointCrossover.guiName -> return KPointCrossover(4)
            Crossover.FiveTwoPointCrossover.guiName -> return KPointCrossover(5)
            Crossover.TwentyPointCrossover.guiName -> return KPointCrossover(20)
            Crossover.Arithmetic.guiName -> return ArithmeticCrossover()
            Crossover.UniformCrossover.guiName -> return UniformCrossover()
            Crossover.OnePointArithmeticCrossover.guiName -> return OnePointArithmeticCrossover()
        }
        LOG.error { "Returned empty crossover strategy!" }
        return CrossoverStrategy()
    }

    /**
     * @param genome string to determine which genome type has to be used
     * @return Returns an object of type @see[GenomeStrategy]. An empty [GenomeStrategy] is
     * returned if [genome] is unknown.
     */
    fun changeGenome(genome: String): GenomeStrategy {
        when(genome){
            Genome.Binary.guiName -> return BinaryGenome()
            Genome.Vector.guiName -> return VectorGenome()
        }
        LOG.error { "Returned empty genome strategy!" }
        return object: GenomeStrategy(){}
    }
    /**
     * @param value string to determine which initial distribution has to be used
     * @return Returns an object of type @see[InitialStrategy]. An empty [InitialStrategy] is
     * returned if [value] is unknown.
     */
    fun changeInitialDistribution(value: String): InitialStrategy {
        when(value){
            Initial.Full.guiName -> return QuadrantInitial(-(width/2),(width/2),-(height/2),height/2)
            Initial.TopLeft.guiName -> return QuadrantInitial(-(width/2),0.0, 0.0,height/2)
            Initial.TopRight.guiName -> return QuadrantInitial(0.0,(width/2), 0.0,(height/2))
            Initial.BottomLeft.guiName -> return QuadrantInitial(-(width/2),0.0, -(height/2),0.0)
            Initial.BottomRight.guiName -> return QuadrantInitial(0.0,(width/2), -(height/2),0.0)
            Initial.Middle.guiName -> return  QuadrantInitial(-(width/4),(width/4),-(height/4),(height/4))
            Initial.Zero.guiName -> return ZeroInitial()
            Initial.Equidistant.guiName -> return EquidistantInitial(width,height)
        }
        LOG.error { "Returned empty initial strategy!" }
        return InitialStrategy()
    }
    /**
     * @param mutation string to determine which mutation operator has to be used
     * @return Returns an object of type @see[MutationStrategy]. An empty [MutationStrategy] is
     * returned if [mutation] is unknown.
     */
    fun changeMutation(mutation: String): MutationStrategy {
        when(mutation){
            Mutation.GaussMutationSigma0.guiName -> return GaussMutation(0.5)
            Mutation.GaussMutationSigma1.guiName -> return GaussMutation(1.0)
            Mutation.GaussMutationSigma2.guiName -> return GaussMutation(2.0)
            Mutation.GaussMutationSigma3.guiName -> return GaussMutation(3.0)
            Mutation.GaussMutationSigma4.guiName -> return GaussMutation(4.0)
            Mutation.GaussMutationSigma10.guiName -> return GaussMutation(10.0)
            Mutation.SelfAdaptation.guiName -> return SelfAdaptationMutation()
            Mutation.Multiply5.guiName -> return MultiplyFactorMutation(5)
            Mutation.Multiply2.guiName -> return MultiplyFactorMutation(2)
            Mutation.BitFlip.guiName -> return BitFlipMutation()
            Mutation.Inversion.guiName -> return InversionMutation()
            Mutation.Shuffle.guiName -> return ShuffleMutation()
            Mutation.Swap.guiName -> return SwapMutation()
            Mutation.Insert.guiName -> return InsertMutation()
        }
        LOG.error { "Returned empty mutation strategy!" }
        return MutationStrategy()
    }
    /**
     * @param problem string to determine which optimization problem has to be used
     * @return Returns an object of type @see[OptimizationProblemStrategy]. An empty [OptimizationProblemStrategy] is
     * returned if [problem] is unknown.
     */
    fun changeOptimizationProblem(problem : String): OptimizationProblemStrategy {
        when(problem) {
            OptimizationProblems.Sphere.guiName -> return Sphere()
            OptimizationProblems.HyperbolicParaboloid.guiName -> return HyperbolicParaboloid()
            OptimizationProblems.Bumps.guiName -> return Bumps()
            OptimizationProblems.AbsHyperbolicParaboloid.guiName -> return AbsHyperbolicParaboloid()
            OptimizationProblems.Sombrero.guiName -> return Sombrero()
            OptimizationProblems.Negnevitsky.guiName -> return Negnevitsky()
            OptimizationProblems.Rosenbrock.guiName -> return Rosenbrock()
            OptimizationProblems.Step.guiName -> return Step()
            OptimizationProblems.Beale.guiName -> return Beale()
            OptimizationProblems.Booth.guiName -> return Booth()
            OptimizationProblems.BoothMAX.guiName -> return BoothMAX()
            OptimizationProblems.SphereAbs.guiName -> return SphereAbs()
            OptimizationProblems.Akley.guiName -> return Akley()
            OptimizationProblems.FletcherPowell.guiName -> return FletcherPowell()
            OptimizationProblems.PlainWithSpike.guiName -> return PlainWithSpike()
            OptimizationProblems.Rastrigin.guiName -> return Rastrigin()
            OptimizationProblems.Schwefel.guiName -> return Schwefel()
        }
        LOG.error { "Returned empty optimization problem!" }
        return OptimizationProblemStrategy()
    }
    /**
     * @param selection string to determine which crossover operator has to be used
     * @return Returns an object of type @see[SelectionStrategy]. An empty [SelectionStrategy] is
     * returned if [selection] is unknown.
     */
    fun changeSelection(selection: String): SelectionStrategy {
        when(selection){
            Selection.Roulette.guiName -> return RouletteSelection()
            Selection.Random.guiName -> return RandomSelection()
            Selection.TournamentK2.guiName -> return TournamentSelection(2)
            Selection.TournamentK3.guiName -> return TournamentSelection(3)
            Selection.TournamentK5.guiName -> return TournamentSelection(5)
            Selection.TournamentK10.guiName -> return TournamentSelection(10)
            Selection.StochasticUniversalSampling.guiName -> return StochasticUniversalSamplingSelection()
            Selection.LinearRankingETA11.guiName -> return LinearRankingSelection(1.1)
            Selection.LinearRankingETA15.guiName -> return LinearRankingSelection(1.5)
            Selection.LinearRankingETA18.guiName -> return LinearRankingSelection(1.8)
            Selection.LinearRankingETA20.guiName -> return LinearRankingSelection(2.0)
            Selection.AgeT1.guiName -> return AgeSelection(1)
            Selection.AgeT3.guiName -> return AgeSelection(3)
            Selection.AgeT5.guiName -> return AgeSelection(5)
            Selection.AgeT10.guiName -> return AgeSelection(10)
            Selection.AgeT20.guiName -> return AgeSelection(20)
        }
        LOG.error { "Returned empty selection strategy!" }
        return SelectionStrategy()
    }
}