package com.geneticVisualizer.enums


/**
 * This enum holds all available selection strategies.
 * @param guiName is the string which is shown in the gui to the user
 * @param bitString defines whether this selection strategy can be used for a bit string representation
 * @param vector defines whether this crossover strategy can be used for a vector representation
 * @author Andrej Schwanke
 */
enum class Selection(var guiName:String, var bitString: Boolean, var vector: Boolean) {
    /**
     * Roulette selection can be used for a minimization problem
     */
    Roulette("Roulette",  true, true),
    /**
     * Tournament selection with tournament size = 2 can be used for all combinations
     */
    TournamentK2("Tournament (K = 2)",  true, true),
    /**
     * Tournament selection with tournament size = 3 can be used for all combinations
     */
    TournamentK3("Tournament (K = 3)",  true, true),
    /**
     * Tournament selection with tournament size = 5 can be used for all combinations
     */
    TournamentK5("Tournament (K = 5)",  true, true),
    /**
     * Tournament selection with tournament size = 10 can be used for all combinations
     */
    TournamentK10("Tournament (K = 10)",  true, true),
    /**
     * Age based selection with threshold T = 1
     */
    AgeT1("Age (T=1)",  true, true),
    /**
     * Age based selection with threshold T = 1
     */
    AgeT3("Age (T=3)",  true, true),
    /**
     * Age based selection with threshold T = 5
     */
    AgeT5("Age (T=5)",  true, true),
    /**
     * Age based selection with threshold T = 10
     */
    AgeT10("Age (T=10)",  true, true),
    /**
     * Age based selection with threshold T = 20
     */
    AgeT20("Age (T=20)",  true, true),
    /**
     * Linear ranking selection with η+ = 1.1
     */
    LinearRankingETA11("Linear Ranking (η+ = 1.1)",  true, true),
    /**
     * Linear ranking selection with η+ = 1.1
     */
    LinearRankingETA15("Linear Ranking (η+ = 1.5)",  true, true),
    /**
     * Linear ranking selection with η+ = 1.1
     */
    LinearRankingETA18("Linear Ranking (η+ = 1.8)",  true, true),
    /**
     * Linear ranking selection with η+ = 1.1
     */
    LinearRankingETA20("Linear Ranking (η+ = 2.0)",  true, true),
    /**
     * Stochastic universal sampling
     */
    StochasticUniversalSampling("Stochastic universal sampling",  true, true),

    /**
     * Random selection can be used for all combinations
     */
    Random("Random",  true, true)

}