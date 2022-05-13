package com.geneticVisualizer.enums


/**
 * This enum holds all available mutation strategies.
 * @param guiName is the string which is shown in the gui to the user

 * @param bitString defines whether this mutation strategy can be used for a bit string representation
 * @param vector defines whether this crossover strategy can be used for a vector representation
 * @author Andrej Schwanke
 */
enum class Mutation(var guiName: String, var bitString: Boolean, var vector: Boolean ) {
    /**
     * Gauss mutation can not be used for bit string representation with strength factor sigma = 0.5
     */
    GaussMutationSigma0("Gauss-Mutation (σ = 0.5)", false, true),
    /**
     * Gauss mutation can not be used for bit string representation with strength factor sigma = 1
     */
    GaussMutationSigma1("Gauss-Mutation (σ = 1)",  false, true),
    /**
     * Gauss mutation can not be used for bit string representation with strength factor sigma = 2
     */
    GaussMutationSigma2("Gauss-Mutation (σ = 2)",  false, true),
    /**
     * Gauss mutation can not be used for bit string representation with strength factor sigma = 3
     */
    GaussMutationSigma3("Gauss-Mutation (σ = 3)",  false, true),
    /**
     * Gauss mutation can not be used for bit string representation with strength factor sigma = 4
     */
    GaussMutationSigma4("Gauss-Mutation (σ = 4)", false, true),
    /**
     * Gauss mutation can not be used for bit string representation with strength factor sigma = 4
     */
    GaussMutationSigma10("Gauss-Mutation (σ = 10)",  false, true),
    /**
     * Self adaptation mutation can only be used for vector representation
     */
    SelfAdaptation("Self adaptation",  false, true),
    /**
     * Multiply by 5 can not be used for bit string representation
     */
    Multiply5("Multiply 5",  false, true),
    /**
     * Multiply by 2 can not be used for bit string representation
     */
    Multiply2("Multiply 2",  false, true),
    /**
     * Bit flit mutation can only be used for bit strings
     */
    BitFlip("BitFlip",  true, false),
    /**
     * Inversion mutation can only be used for bit strings
     */
    Inversion("Inversion",  true,false),
    /**
     * Shuffle mutation can be used for bit string and vector
     */
    Shuffle("Shuffle",  true, true),
    /**
     * Swap mutation can be used for bit string and vector
     */
    Swap("Swap",  true, true),
    /**
     * Insert mutation can only be used for bit string
     */
    Insert("Insert",  true, false),
}