package com.geneticVisualizer.enums

/**
 * This enum holds all available initial distribution strategies.
 * @param guiName is the string which is shown in the gui to the user
 * @author Andrej Schwanke
 */
enum class Initial(var guiName: String) {
    Full("Full"),
    TopLeft("Top Left"),
    TopRight("Top Right"),
    BottomLeft("Bottom Left"),
    BottomRight("Bottom Right"),
    Middle("Middle"),
    Zero("Zero"),
    Equidistant("Equidistant")
}