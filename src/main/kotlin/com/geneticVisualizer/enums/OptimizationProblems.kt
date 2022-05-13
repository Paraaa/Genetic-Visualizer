package com.geneticVisualizer.enums

/**
 * Hold all available optimizations problems which the evolutionary algorithm can solve
 * @param guiName is the string which is shown in the gui to the user
 * @author Andrej Schwanke
 */
enum class OptimizationProblems(var guiName: String) {
    /**
     * Sphere function @see[Sphere.kt]
     */
    Sphere("Sphere"),
    /**
     * SphereAbs function @see[SphereAbs.kt]
     */
    SphereAbs("SphereAbs"),
    /**
     * Hyperbolic Paraboloid function  @see[HyperbolicParaboloid.kt]
     */
    HyperbolicParaboloid("Hyperbolic Paraboloid"),
    /**
     * Abs Hyperbolic Paraboloid function @see[AbsHyperbolicParaboloid.kt]
     */
    AbsHyperbolicParaboloid("Abs Hyperbolic Paraboloid"),
    /**
     * Bumps function @see[Bumps.kt]
     */
    Bumps("Bumps"),
    /**
     * Sombero function @see[Sombero.kt]
     */
    Sombrero("Sombrero"),
    /**
     * Negnevitsky function @see[Negnevitsky.kt]
     */
    Negnevitsky("Negnevitsky"),
    /**
     * Rosenbrock function @see[Rosenbrock.kt]
     */
    Rosenbrock("Rosenbrock"),
    /**
     * Step function @see[Step.kt]
     */
    Step("Step"),
    /**
     * Beale function @see[Beale.kt]
     */
    Beale("Beale"),
    /**
     * Booth function @see[Booth.kt]
     */
    Booth("Booth"),
    /**
     * BoothMAX function @see[BoothMAX.kt]
     */
    BoothMAX("BoothMAX"),
    /**
     * Akley function @see[Akley.kt]
     */
    Akley("Akley"),
    /**
     * FletcherPowell function @see[FletcherPowell.kt]
     */
    FletcherPowell("FletcherPowell"),
    /**
     * PlainWithSpike function @see[PlainWithSpike.kt]
     */
    PlainWithSpike("PlainWithSpike"),
    /**
     * Rastrigin function @see[Rastrigin.kt]
     */
    Rastrigin("Rastrigin"),
    /**
     * Schwefel function @see[Schwefel.kt]
     */
    Schwefel("Schwefel"),
}