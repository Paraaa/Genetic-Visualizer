package com.geneticVisualizer.enums


/**
 * This enum holds all available crossover strategies.
 * @param guiName is the string which is shown in the gui to the user
 * @param minimize defines whether this crossover strategy can be used for minimizing
 * @param bitString defines whether this crossover strategy can be used for a bit string representation
 * @param vector defines whether this crossover strategy can be used for a vector representation
 * @author Andrej Schwanke
 */
enum class Crossover(var guiName: String,  var bitString: Boolean, var vector: Boolean) {
    /**
     * One point crossover can be used for minimization and in a bit string representation
     */
    OnePointCrossover("1-Point-Crossover",  true, true),
    /**
     * Two point crossover can only be used for bit strings
     */
    TwoPointCrossover("2-Point-Crossover",true, false),
    /**
     * Three point crossover can only be used for bit strings
     */
    ThreePointCrossover("3-Point-Crossover", true, false),
    /**
     * Four point crossover can only be used for bit strings
     */
    FourTwoPointCrossover("4-Point-Crossover", true, false),
    /**
     * Five point crossover can only be used for bit strings
     */
    FiveTwoPointCrossover("5-Point-Crossover", true, false),
    /**
     * Twenty point crossover can only be used for bit strings
     */
    TwentyPointCrossover("20-Point-Crossover",  true, false),

    /**
     * Arithmetic crossover can only be used for a vector representation and for minimizing a problem
     */
    Arithmetic("Arithmetic",  false, true),
    /**
     * One Point arithmetic crossover
     */
    OnePointArithmeticCrossover("1-Point-Arithmetic-Crossover",  false, true),
    /**
     * Uniform crossover can only be used for a binary representation (in this case) and for minimizing problems
     */
    UniformCrossover("Uniform",  true, false)
}