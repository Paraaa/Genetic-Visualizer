package com.geneticVisualizer.enums

/**
 * Enum to hold all available genome representations
 * @param guiName is the string which is shown in the gui to the user
 * @author Andrej Schwanke
 */
enum class Genome(var guiName: String) {
	/**
	 * Vector representation is used for real values.
	 */
	Vector("Vector"),

	/**
	 * Binary is used for a bit string representation
	 */
	Binary("Binary")
}