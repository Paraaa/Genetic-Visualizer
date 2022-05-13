package com.geneticVisualizer.gui

import com.geneticVisualizer.enums.*
import com.geneticVisualizer.evolutionaryAlgorithm.EvolutionaryAlgorithm
import com.geneticVisualizer.evolutionaryAlgorithm.Generation
import com.geneticVisualizer.evolutionaryAlgorithm.crossover.CrossoverStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.genome.GenomeStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.mutation.MutationStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.optimizationProblem.OptimizationProblemStrategy
import com.geneticVisualizer.evolutionaryAlgorithm.selection.SelectionStrategy
import com.geneticVisualizer.factory.Factory
import javafx.application.Platform

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Side
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.util.StringConverter
import mu.KotlinLogging
import java.io.File
import java.net.URL
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.*
import kotlin.math.round


class Controller : Initializable {
    private val LOG = KotlinLogging.logger {}

    /**
     * Determines the size of the canvas
     */
    private val CANVAS_WIDTH = 730.0
    private val CANVAS_HEIGHT = 670.0

    /**
     * This section holds a reference to the necessary gui components.
     */
    @FXML var mainGrid: GridPane = GridPane()
    @FXML var contourView: ImageView = ImageView()
    @FXML lateinit var optProblemChoice: ChoiceBox<String>
    @FXML lateinit var parentSelectionChoice: ChoiceBox<String>
    @FXML lateinit var crossoverChoice: ChoiceBox<String>
    @FXML lateinit var mutationChoice: ChoiceBox<String>
    @FXML lateinit var survivorSelectionChoice: ChoiceBox<String>
    @FXML lateinit var goalChoice: ChoiceBox<String>
    @FXML lateinit var representationChoice: ChoiceBox<String>
    @FXML lateinit var selectionTypeChoice: ChoiceBox<String>
    @FXML lateinit var initialDistributionChoice: ChoiceBox<String>
    @FXML lateinit var maxIterationSpinner: Spinner<Int>
    @FXML lateinit var parentSizeSpinner: Spinner<Int>
    @FXML lateinit var parentSelectionElitismSpinner: Spinner<Int>
    @FXML lateinit var survivorSelectionElitismSpinner: Spinner<Int>
    @FXML lateinit var mutationProbabilitySpinner: Spinner<Int>
    @FXML lateinit var lambdaSpinner: Spinner<Int>
    @FXML lateinit var muSpinner: Spinner<Int>
    @FXML lateinit var crossoverEnable: CheckBox
    @FXML lateinit var mutationEnable: CheckBox
    @FXML lateinit var parentSelectionLabel: Label
    @FXML lateinit var crossoverLabel: Label
    @FXML lateinit var mutationLabel: Label
    @FXML lateinit var survivorSelectionLabel: Label
    @FXML lateinit var startButton: Button
    @FXML lateinit var stopButton: Button
    @FXML lateinit var continueButton: Button
    @FXML lateinit var generationBackwardsButton: Button
    @FXML lateinit var generationForwardButton: Button
    @FXML lateinit var operatorBackwardsButton: Button
    @FXML lateinit var operatorForwardButton: Button
    @FXML lateinit var generationLabel: Label
    @FXML lateinit var bestFitnessLabel: Label
    @FXML lateinit var worstFitnessLabel: Label
    @FXML lateinit var averageFitnessLabel: Label
    @FXML lateinit var solutionLabel: Label
    @FXML lateinit var modelingPane: AnchorPane
    @FXML lateinit var simulationPane: AnchorPane
    @FXML lateinit var graphPane: AnchorPane
    @FXML lateinit var delaySlider: Slider
    @FXML lateinit var generationSlider: Slider
    @FXML lateinit var runNameTextField: TextField
    lateinit var graph: LineChart<Number,Number>

    /**
     * Builds the necessary factory objects to generate
     * the correct objects on change.
     */
    private var factory: Factory = Factory(CANVAS_WIDTH,CANVAS_HEIGHT)

    private var evolutionaryAlgorithm: EvolutionaryAlgorithm = EvolutionaryAlgorithm()
    private var generations: MutableList<Generation> = mutableListOf()
    private var genomeCanvas: Canvas = Canvas()

    /**
     * Is a boolean which keeps track of whether the solution space was already loaded.
     * This saves some computation time while running the program. This saves
     * at least one file request.
     */
    private var wasLoaded = false

    /**
     * Keeps track of whether bit string is selected
     */
    private var bitString: Boolean = false

    /**
     * Keeps track whether the slider was changed by the user or the program
     */
    private var sliderWasChangedLocally: Boolean = false

    /**
     * This variable is handling the animation of the genomes in the canvas. It has to be a class
     * member to prevent unpredictable behaviour if a new animation is started while the previous one
     * is still running. To prevent this issue on has to call the stop function if a new run is started.
     */
    private var animationGenomes: AnimationGenomes = object : AnimationGenomes(){}

    /**
     * Keeps track at what operator the animation currently is
     */
    private var operatorIndex = 0
    /**
     * Keeps track at what generation the animation currently is
     */
    private var generationIndex = 0
    /**
     * Determines the delay between frames of the animation.
     * Default is: 0.5
     */
    private var delay = 0.5

    /**
     * Checks whether the optimization function was changed
     */
    private var functionChanged  = false


    /**
     * Initializes the view and build the necessary elements and behaviours.
     */
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LOG.debug { "Initializing Interface..." }
        buildInteractiveElements()
        drawOptimizationProblem()
        buildSpinner()
        defineOnActionBehaviour()
        LOG.debug { "Interface successfully initialized" }
    }

    /**
     * Starts the current evolutionary process in a animation
     */
    @FXML fun start() {
        if(functionChanged){
            clearGraph()
            functionChanged = false
        }
        modelingPane.isDisable = true
        reset()
        Platform.runLater {
            startButton.isDisable = true
            stopButton.isDisable = false
            continueButton.isDisable = true
            generationSlider.isDisable = true
            sliderWasChangedLocally = true
            deactivateStepButtons()
            LOG.debug { "Calculating optimization..." }
            drawOptimizationProblem()
            generations = evolutionaryAlgorithm.solve()
            buildGenerationSlider(generations.size)
            simulationPane.children.remove(genomeCanvas)
            genomeCanvas = drawAnimation()
            LOG.debug { "Starting new animation" }
            simulationPane.children.add(genomeCanvas)
            LOG.debug { "Calculation finished" }
        }
    }

    private fun buildGenerationSlider(max: Int){
        generationSlider.min = 1.0
        generationSlider.max = max.toDouble()
        generationSlider.blockIncrement = 1.0
        generationSlider.majorTickUnit = 5.0
        generationSlider.minorTickCount = 4
        generationSlider.isShowTickLabels = true
        generationSlider.isSnapToTicks = true
    }
    private fun setSliderIndex(){
        generationSlider.valueProperty().value = generationIndex.toDouble() + 1

    }

    /**
     * Stops the current animation and enables buttons to go to the next/previous generation/operator
     */
    @FXML fun stop() {
        if(animationGenomes.animationStopped) return
        modelingPane.isDisable = false
        animationGenomes.stop()
        animationGenomes.animationStopped = true
        startButton.isDisable = false
        stopButton.isDisable = true
        continueButton.isDisable = false
        generationSlider.isDisable = false
        sliderWasChangedLocally = false
        activateStepButtons()
        LOG.debug { "Stopped current animation" }
    }
    /**
     * Continues the animation at the last known [operatorIndex] and [generationIndex]
     */
    @FXML fun continueRun() {
        if(!animationGenomes.animationStopped) return
        modelingPane.isDisable = true
        animationGenomes.animationStopped = false
        Platform.runLater {
            simulationPane.children.remove(genomeCanvas)
            genomeCanvas = drawAnimation(true)
            simulationPane.children.add(genomeCanvas)
        }
        startButton.isDisable = true
        stopButton.isDisable = false
        continueButton.isDisable = true
        generationSlider.isDisable = true
        sliderWasChangedLocally = true
        deactivateStepButtons()
        LOG.debug { "Continue current animation" }
    }
    /**
     * Shows the previous generation
     */
    @FXML fun generationBackwards() {
        if(generationIndex <= 0) return
        generationIndex--
        addGenerationToPane()
        setSliderIndex()
        LOG.debug { "Changed: Generations index is now at: $generationIndex "}
    }
    /**
     * Shows the next generation
     */
    @FXML fun generationForward() {
        if(generationIndex >= generations.size - 1) return
        generationIndex++
        addGenerationToPane()
        setSliderIndex()
        LOG.debug { "Changed: Generations index is now at: $generationIndex "}
    }
    /**
     * Shows the previous operator. If the first operator is reached it goes to the previous generations one the
     * next click and continuous with the last operator of the previous generation
     */
    @FXML fun operatorBackwards() {
        if(generationIndex <= 0 && operatorIndex <= 0) return
        operatorIndex = (operatorIndex - 1)
        if(operatorIndex < 0){
            operatorIndex = 4
            if(generationIndex - 1 >= 0){
                generationIndex--
            }
        }
        addGenerationToPane()
        setSliderIndex()
        LOG.debug { "Changed: Operator index is now at: $operatorIndex" }
        LOG.debug { "Changed: Generation index is now at: $generationIndex" }
    }

    /**
     * Shows the next operator. If the last operator is reached if goes to the next generations one the next
     * click and continuous with the first operator of the next generation.
     */
    @FXML fun operatorForward() {
        if(generationIndex >= generations.size - 1 && operatorIndex >= 4) return
        operatorIndex = (operatorIndex + 1)

        if(operatorIndex >= 5){
            if(generationIndex + 1 <= generations.size - 1){
                operatorIndex = 0
                generationIndex++
            }
        }
        addGenerationToPane()
        setSliderIndex()
        LOG.debug { "Changed: Operator index is now at: $operatorIndex" }
        LOG.debug { "Changed: Generation index is now at: $generationIndex" }
    }

    /**
     * This method is used to add a specific generation to the canvas
     */
    private fun addGenerationToPane(){
        Platform.runLater {
            simulationPane.children.remove(genomeCanvas)
            genomeCanvas = drawGeneration()
            simulationPane.children.add(genomeCanvas)
        }
    }
    /**
     * Clears the graph of the runs
     */
    @FXML fun clearGraph(){
        graph.data.clear()
        run = 0
    }

    /**
     * Builds the interactive Elements in the view and selects the initial state
     */
    private fun buildInteractiveElements(){
        fillChoiceBoxes()
        LOG.debug { "Selecting initial assignment of UI elements" }
        optProblemChoice.selectionModel.selectFirst()
        parentSelectionChoice.selectionModel.selectFirst()
        crossoverChoice.selectionModel.selectFirst()
        mutationChoice.selectionModel.selectFirst()
        survivorSelectionChoice.selectionModel.selectFirst()
        goalChoice.selectionModel.selectFirst()
        representationChoice.selectionModel.selectFirst()
        crossoverEnable.isSelected = true
        mutationEnable.isSelected = true
        stopButton.isDisable = true
        continueButton.isDisable = true
        generationSlider.isDisable = true
        deactivateStepButtons()
        buildGraph()
    }

    /**
     * Function to build the graph and set the information on the axis
     */
    private fun buildGraph(){
        LOG.debug { "Building line chart" }
        var x = NumberAxis()
        x.label = "Generation"
        x.isMinorTickVisible = false
        var y = NumberAxis()
        y.label = "Average Fitness"
        var yFormat: NumberFormat = DecimalFormat("#.#E0")
        y.tickLabelFormatter = object : StringConverter<Number>() {
            override fun toString(number: Number): String {
                return yFormat.format(number.toDouble())
            }
            override fun fromString(string: String): Number {
                return try {
                    yFormat.parse(string)
                } catch (e: ParseException) {
                    e.printStackTrace()
                    0
                }
            }
        }

        graph = LineChart(x,y)
        graph.isLegendVisible = true
        graph.legendSide = Side.BOTTOM
        graph.maxWidth = 300.0
        graph.prefWidth = 300.0
        graph.minWidth = 300.0
        graphPane.children.add(graph)
    }
    /**
     * This function is drawing the average fitness of the population on a line graph
     */
    private var run = 0
    private fun drawGraph(){
        var datapoints: XYChart.Series<Number,Number> = XYChart.Series<Number, Number>()
        datapoints.name = if(runNameTextField.text.isEmpty()){
            run++
            "Run $run"
        } else if(runNameTextField.text.length > 32){
            runNameTextField.text.substring(0,32)
        } else {
            runNameTextField.text
        }
        generations.forEach { generation ->
            var datapoint: XYChart.Data<Number, Number> =
                XYChart.Data(generation.number+1, generation.averageFitness)
            datapoints.data.add(datapoint)
        }
        graph.data.add(datapoints)
    }
    /**
     * Defines what the buttons should do if they are clicked
     */
    private fun defineOnActionBehaviour(){
        LOG.debug { "Defining behaviour of interaction elements..." }
        optProblemChoice.setOnAction {
            if(optProblemChoice.selectionModel.selectedItem != null) {
                var selectedOptProblem: String = optProblemChoice.selectionModel.selectedItem
                var newOptimizationProblem: OptimizationProblemStrategy =
                    factory.changeOptimizationProblem(selectedOptProblem)
                evolutionaryAlgorithm.optimizationProblem = newOptimizationProblem
                wasLoaded = false
                functionChanged = true
                LOG.debug { "Changed: Optimization Problem is now: $selectedOptProblem" }
            }
        }
        parentSelectionChoice.setOnAction {
            if(parentSelectionChoice.selectionModel.selectedItem != null) {
                var selectedSelection: String = parentSelectionChoice.selectionModel.selectedItem
                var parentSelection: SelectionStrategy = factory.changeSelection(selectedSelection)
                evolutionaryAlgorithm.parentSelection = parentSelection
                LOG.debug { "Changed: Parent selection strategy is now: $selectedSelection" }
            }
        }
        crossoverChoice.setOnAction {
            if(crossoverChoice.selectionModel.selectedItem != null) {
                var selectedCrossover: String = crossoverChoice.selectionModel.selectedItem
                var crossover: CrossoverStrategy = factory.changeCrossover(selectedCrossover)
                evolutionaryAlgorithm.crossover = crossover
                LOG.debug { "Changed: Crossover strategy is now: $selectedCrossover" }
            }
        }
        mutationChoice.setOnAction {
            if(mutationChoice.selectionModel.selectedItem != null) {
                var selectedMutation: String = mutationChoice.selectionModel.selectedItem
                var mutation: MutationStrategy = factory.changeMutation(selectedMutation)
                evolutionaryAlgorithm.mutation = mutation
                LOG.debug { "Changed: Mutation strategy is now: $selectedMutation" }
            }
        }
        survivorSelectionChoice.setOnAction {
            if(survivorSelectionChoice.selectionModel.selectedItem != null) {
                var selectedSelection: String = survivorSelectionChoice.selectionModel.selectedItem
                var survivorSelection: SelectionStrategy = factory.changeSelection(selectedSelection)
                evolutionaryAlgorithm.survivorSelection = survivorSelection
                LOG.debug { "Changed: Survivor selection strategy is now: $selectedSelection" }
            }
        }
        goalChoice.setOnAction {
            if(goalChoice.selectionModel.selectedItem != null) {
                var selectedGoal: String = goalChoice.selectionModel.selectedItem
                if (selectedGoal == "Maximize") {
                    evolutionaryAlgorithm.goal = 1
                } else {
                    evolutionaryAlgorithm.goal = -1
                }
                LOG.debug { "Changed: Goal is now $selectedGoal with value: ${evolutionaryAlgorithm.goal}" }
            }
        }
        representationChoice.setOnAction {
            if(representationChoice.selectionModel.selectedItem != null) {
                var selectedRepresentation: String = representationChoice.selectionModel.selectedItem
                evolutionaryAlgorithm.genomeStrategy = factory.changeGenome(selectedRepresentation)
                LOG.debug { "Changed: Genome representation is now: $selectedRepresentation" }
                bitString = selectedRepresentation == "Binary"
                clearChoiceBoxes(representationChanged = true)
                fillChoiceBoxes(representationChanged = true)
            }
        }
        selectionTypeChoice.setOnAction {
            if(selectionTypeChoice.selectionModel.selectedItem != null){
                var selectedType: String = selectionTypeChoice.selectionModel.selectedItem
                evolutionaryAlgorithm.plusSelection = selectedType == "Plus"
                LOG.debug { "Changed: Selection type is now: $selectedType and plus selection is:" +
                        "${evolutionaryAlgorithm.plusSelection}" }
            }
        }
        initialDistributionChoice.setOnAction {
            if(initialDistributionChoice.selectionModel.selectedItem != null){
                var selectedDistribution: String = initialDistributionChoice.selectionModel.selectedItem
                evolutionaryAlgorithm.initialStrategy = factory.changeInitialDistribution(selectedDistribution)
                LOG.debug { "Changed: Initial strategy is now: $selectedDistribution" }
            }
        }
        crossoverEnable.setOnAction {
            evolutionaryAlgorithm.crossoverEnabled = crossoverEnable.isSelected
            crossoverChoice.isDisable = !crossoverEnable.isSelected
            LOG.debug { "Changed: Crossover is now: ${crossoverEnable.isSelected}" }
        }
        mutationEnable.setOnAction {
            evolutionaryAlgorithm.mutationEnabled = mutationEnable.isSelected
            mutationChoice.isDisable = !mutationEnable.isSelected
            mutationProbabilitySpinner.isDisable = !mutationEnable.isSelected
            LOG.debug { "Changed: Mutation is now: ${mutationEnable.isSelected}" }
        }

        Platform.runLater {
            parentSelectionElitismSpinner.valueFactory.valueProperty().addListener { _, _, _ ->
                if(parentSelectionElitismSpinner.valueFactory.value >= muSpinner.valueFactory.value){
                    parentSelectionElitismSpinner.valueFactory.value = muSpinner.valueFactory.value
                }
                if(checkInput(parentSelectionElitismSpinner.valueFactory.value.toString())) {
                    evolutionaryAlgorithm.parentElitism = parentSelectionElitismSpinner.valueFactory.value
                    LOG.debug { "Changed: Parent elitism is now: ${evolutionaryAlgorithm.parentElitism}" }
                }
            }
            survivorSelectionElitismSpinner.valueFactory.valueProperty().addListener { _,_,_ ->
                if(survivorSelectionElitismSpinner.valueFactory.value >= muSpinner.valueFactory.value){
                    survivorSelectionElitismSpinner.valueFactory.value = muSpinner.valueFactory.value
                }
                if(checkInput(survivorSelectionElitismSpinner.valueFactory.value.toString())) {
                    evolutionaryAlgorithm.survivorElitism = survivorSelectionElitismSpinner.valueFactory.value
                    LOG.debug { "Changed: Survivor elitism is now: ${evolutionaryAlgorithm.survivorElitism}" }
                }
            }
            maxIterationSpinner.valueFactory.valueProperty().addListener{ _,_,_ ->
                if(checkInput(maxIterationSpinner.valueFactory.value.toString())) {
                    evolutionaryAlgorithm.maxIterations = maxIterationSpinner.valueFactory.value
                    LOG.debug { "Changed: Max iteration is now: ${evolutionaryAlgorithm.maxIterations}" }
                }
            }
            parentSizeSpinner.valueFactory.valueProperty().addListener { _, _, _ ->
                if(checkInput(parentSizeSpinner.valueFactory.value.toString())) {
                    evolutionaryAlgorithm.parentSize = parentSizeSpinner.valueFactory.value
                    LOG.debug { "Changed: Parent size is now: ${evolutionaryAlgorithm.mu}" }
                }
            }
            mutationProbabilitySpinner.valueFactory.valueProperty().addListener {_,_,_ ->
                if(checkInput(mutationProbabilitySpinner.valueFactory.value.toString())) {
                    evolutionaryAlgorithm.mutationProbability =
                        (mutationProbabilitySpinner.valueFactory.value.toDouble() / 100.0)
                    LOG.debug { "Changed: Mutation probability is now: ${evolutionaryAlgorithm.mutationProbability}" }
                }
            }
            lambdaSpinner.valueFactory.valueProperty().addListener{ _, _, _ ->
                if(checkInput(lambdaSpinner.valueFactory.value.toString())) {
                    evolutionaryAlgorithm.lambda = lambdaSpinner.valueFactory.value
                    LOG.debug { "Changed: Lambda is now: ${evolutionaryAlgorithm.lambda}" }
                }
            }
            muSpinner.valueFactory.valueProperty().addListener{_,_,_ ->
                if(checkInput(muSpinner.valueFactory.value.toString())){
                    val mu = muSpinner.valueFactory.value
                    if(mu < parentSelectionElitismSpinner.valueFactory.value){
                        parentSelectionElitismSpinner.valueFactory.value = mu
                    }
                    if(mu < survivorSelectionElitismSpinner.valueFactory.value){
                        survivorSelectionElitismSpinner.valueFactory.value = mu
                    }

                    evolutionaryAlgorithm.mu = muSpinner.valueFactory.value
                    LOG.debug { "Changed: Mu (Population size) is now: ${evolutionaryAlgorithm.parentSize}" }
                }
            }
            delaySlider.valueProperty().addListener {_,_,_ ->
                delay = delaySlider.value
            }
            generationSlider.valueProperty().addListener {_,_,_ ->
                generationIndex = generationSlider.value.toInt() - 1
                if(!sliderWasChangedLocally) {
                    addGenerationToPane()
                }
            }
        }
        LOG.debug { "Behaviour defined successfully" }
    }

    /**
     * This method checks if a given input only contains numbers
     * @param input Input string to check
     * @return Returns a bool whether the given string only contains numbers
     */
    private fun checkInput(input: String): Boolean{
        return input.matches(Regex("[0-9]+"))
    }


    /**
     * Draws the contour plot on the view. If it was already loaded this step is skipped.
     */
    private fun drawOptimizationProblem(){
        if(wasLoaded) return
        LOG.debug { "Drawing solution space..." }
        simulationPane.children.remove(contourView)
        contourView = drawSolutionSpace(evolutionaryAlgorithm.optimizationProblem)
        simulationPane.children.add(contourView)
        wasLoaded = true
        LOG.debug { "Solution space drawn successfully" }
    }

    /**
     * Fills the choice boxed from witch the user can choose certain operators for the evolutionary algorithm
     * @param representationChanged Keeps track of whether the representation was changed
     */
    private fun fillChoiceBoxes( representationChanged: Boolean = false){
        LOG.debug { "Filling choice boxes..." }
        if(!representationChanged) {
            OptimizationProblems.values().forEach { problem ->
                optProblemChoice.items.add(problem.guiName)
            }
            Goals.values().forEach { goal ->
                goalChoice.items.add(goal.guiName)
            }
            Genome.values().forEach { representation ->
                representationChoice.items.add(representation.guiName)
            }
            SelectionType.values().forEach { selectionType ->
                selectionTypeChoice.items.add(selectionType.guiName)
            }
            Selection.values().forEach { selection ->
                parentSelectionChoice.items.add(selection.guiName)
                survivorSelectionChoice.items.add(selection.guiName)
            }
            Initial.values().forEach { initial ->
                initialDistributionChoice.items.add(initial.guiName)
            }
            initialDistributionChoice.selectionModel.selectFirst()
            selectionTypeChoice.selectionModel.selectFirst()
            parentSelectionChoice.selectionModel.selectFirst()
            survivorSelectionChoice.selectionModel.selectFirst()
            optProblemChoice.selectionModel.selectFirst()
            goalChoice.selectionModel.selectFirst()
            representationChoice.selectionModel.selectFirst()
        }

        Crossover.values().forEach { crossover ->
            if(bitString && !crossover.bitString) return@forEach
            if(!bitString && !crossover.vector) return@forEach
            crossoverChoice.items.add(crossover.guiName)
        }
        Mutation.values().forEach { mutation ->
            if(bitString && !mutation.bitString) return@forEach
            if(!bitString && !mutation.vector) return@forEach
            mutationChoice.items.add(mutation.guiName)
        }
        crossoverChoice.selectionModel.selectFirst()
        mutationChoice.selectionModel.selectFirst()
        LOG.debug { "Choice boxes filled successfully" }
    }
    /**
     * Clears the choice boxes after certain events to disable some choice if some other choices are selected which
     * would lead to some unintended behaviour.
     * @param representationChanged Keeps track of whether the representation was changed
     */
    private fun clearChoiceBoxes(representationChanged: Boolean = false){
        LOG.debug { "Clearing choice boxes..." }
        crossoverChoice.items.clear()
        mutationChoice.items.clear()
        if(!representationChanged) {
            optProblemChoice.items.clear()
            goalChoice.items.clear()
            representationChoice.items.clear()
            parentSelectionChoice.items.clear()
            survivorSelectionChoice.items.clear()
        }
        LOG.debug { "Choice boxes successfully cleared" }
    }

    /**
     * Builds the spinner and sets the limits of those
     */
    private fun buildSpinner() {
        LOG.debug { "Building spinner..." }
        maxIterationSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1,250)
        parentSizeSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1,2000)
        parentSelectionElitismSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0,10)
        survivorSelectionElitismSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0,10)
        mutationProbabilitySpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0,100)
        lambdaSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1,3000)
        muSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1,2000)

        maxIterationSpinner.valueFactory.value = evolutionaryAlgorithm.maxIterations
        parentSizeSpinner.valueFactory.value = evolutionaryAlgorithm.parentSize
        parentSelectionElitismSpinner.valueFactory.value = evolutionaryAlgorithm.parentElitism
        survivorSelectionElitismSpinner.valueFactory.value = evolutionaryAlgorithm.survivorElitism
        mutationProbabilitySpinner.valueFactory.value = (evolutionaryAlgorithm.mutationProbability * 100).toInt()
        lambdaSpinner.valueFactory.value = evolutionaryAlgorithm.lambda
        muSpinner.valueFactory.value = evolutionaryAlgorithm.mu


        LOG.debug { "Spinner built successfully" }
    }

    /**
     * Enabled the usage of the step buttons
     */
    private fun activateStepButtons(){
        generationBackwardsButton.isDisable= false
        generationForwardButton.isDisable= false
        operatorBackwardsButton.isDisable= false
        operatorForwardButton.isDisable = false
    }
    /**
     * Disables the usage of the step buttons
     */
    private fun deactivateStepButtons(){
        generationBackwardsButton.isDisable= true
        generationForwardButton.isDisable= true
        operatorBackwardsButton.isDisable= true
        operatorForwardButton.isDisable = true
    }

    /**
     * Starts the animation of the generations
     * @param continueRun If a run is continued some behaviour has to be different e.g. not drawing the graph again
     * @return Returns a canvas in which the animation is visualised
     */
    private fun drawAnimation(continueRun: Boolean = false): Canvas {
        var canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)
        var gc: GraphicsContext = canvas.graphicsContext2D
        gc.translate(CANVAS_WIDTH/2,CANVAS_HEIGHT/2)
        if(!continueRun) { drawGraph() }
        animationGenomes = object : AnimationGenomes() {
            var lastUpdate : Long = System.nanoTime()
            override fun handle(now: Long) {
                try {
                    //LOG.debug { "Drawing generation: $generationIndex with operator: $operatorIndex" }
                    var passedTimesInNano : Long = now - lastUpdate
                    var passedTimeInSeconds : Double = passedTimesInNano / 1_000_000_000.0
                    if(passedTimeInSeconds >= delay){
                        if(generationIndex >= generations.size - 1){
                            stop()
                        }
                        gc.clearRect(-(CANVAS_WIDTH/2), -(CANVAS_HEIGHT/2), CANVAS_WIDTH, CANVAS_HEIGHT)

                        var generation = generations[generationIndex]
                        drawOnCanvas(gc, generation)

                        updateStatistics()
                        lastUpdate = now
                        operatorIndex++
                        if(operatorIndex > 4){
                            generationIndex++
                            operatorIndex = 0
                        }
                        setSliderIndex()
                    }
                } catch (e: Exception) {
                    stop()
                    LOG.error { "Error ${e.stackTrace} occurred. \n ${e.message}" }
                }
            }
        }
        animationGenomes.start()
        return canvas
    }


    /**
     * Draws a specific generation on the canvas
     * @return Returns a canvas in which the selected generation is shown
     */
    private fun drawGeneration(): Canvas {
        var canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)
        var gc: GraphicsContext = canvas.graphicsContext2D
        gc.translate(CANVAS_WIDTH/2,CANVAS_HEIGHT/2)
        var generation = generations[generationIndex]
        drawOnCanvas(gc,generation)
        updateStatistics()
        LOG.debug { "Drawing one generation to the canvas with generation index: $generationIndex and operator index: $operatorIndex" }
        return canvas
    }
    /**
     * Draws the population in different color on the canvas.
     */
    private fun drawOnCanvas(gc: GraphicsContext, generation: Generation){
        parentSelectionLabel.textFill = Color.BLACK
        crossoverLabel.textFill = Color.BLACK
        mutationLabel.textFill = Color.BLACK
        survivorSelectionLabel.textFill = Color.BLACK
        when(operatorIndex){
            0 -> {
                parentSelectionLabel.textFill = Color.RED
                for(genome in generation.previousPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc,genome)
                }
                for(parent in generation.parentPopulation){
                    gc.fill = Color.YELLOW
                    drawGenome(gc,parent)
                }
                for(parentElite in generation.parentElite){
                    gc.fill = Color.RED
                    drawGenome(gc, parentElite)
                }

            }
            1 -> {
                crossoverLabel.textFill = if (!generation.crossoverPopulation.isEmpty()) {
                    Color.RED
                } else {
                    Color.BLACK
                }

                parentSelectionLabel.textFill = Color.BLACK
                for(parent in generation.parentPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc,parent)
                }
                for(crossoverChild in generation.crossoverPopulation){
                    gc.fill = Color.GREEN
                    drawGenome(gc,crossoverChild)
                }
            }
            2 -> {
                mutationLabel.textFill = if (!generation.mutatedPopulation.isEmpty()){
                    Color.RED
                } else {
                    Color.BLACK
                }


                crossoverLabel.textFill = Color.BLACK
                for(parent in generation.parentPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc, parent)
                }
                for(crossoverChild in generation.populationWithoutMutatedChildren){
                    gc.fill = Color.WHITE
                    drawGenome(gc,crossoverChild)
                }
                for(mutatedChild in generation.mutatedPopulation){
                    gc.fill = Color.DARKVIOLET
                    drawGenome(gc,mutatedChild)
                }
            }
            3 -> {
                survivorSelectionLabel.textFill = Color.RED
                mutationLabel.textFill = Color.BLACK
                for(parent in generation.parentPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc, parent)
                }
                for(crossoverChild in generation.crossoverPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc,crossoverChild)
                }
                for(mutatedChild in generation.mutatedPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc,mutatedChild)
                }
                for(survivor in generation.survivorPopulation){
                    gc.fill = Color.ROYALBLUE
                    drawGenome(gc, survivor)
                }
                for(survivorElite in generation.survivorElite){
                    gc.fill = Color.RED
                    drawGenome(gc, survivorElite)
                }
            }
            4 -> {
                survivorSelectionLabel.textFill = Color.BLACK
                for(survivor in generation.survivorPopulation){
                    gc.fill = Color.WHITE
                    drawGenome(gc,survivor)
                }
                drawBestGenomeMark(gc, generation)
            }
        }
    }

    /**
     * Draws a specific genome on the screen
     */
    private fun drawGenome(gc: GraphicsContext, genome: GenomeStrategy){
        val size = 10.0
        val xCord = genome.X()
        val yCord = -1 * genome.Y()
        gc.fillOval(xCord,yCord, size, size)
        gc.lineWidth = 1.0
        gc.stroke = Color.BLACK
        gc.strokeOval(xCord,yCord, size, size)
    }

    /**
     * Draws an X at the location of the best genome in the current generation
     */
    private fun drawBestGenomeMark(gc: GraphicsContext, generation: Generation){
        gc.stroke = Color.RED
        gc.lineWidth = 2.0
        val offset = 5
        val xCord = (generation.bestGenomeCoordinate.first * evolutionaryAlgorithm.optimizationProblem.scale) + offset
        val yCord = -1 * ((generation.bestGenomeCoordinate.second * evolutionaryAlgorithm.optimizationProblem.scale) - offset)
        gc.strokeLine(xCord-10,yCord+10,xCord+10,yCord-10)
        gc.strokeLine(xCord-10,yCord-10,xCord+10,yCord+10)
    }

    /**
     * This method creates a solution space based on a given function using
     * the path to an image given in the optimization problem.
     * @return Returns a ImageView for the given optimization problem.
     */
    private fun drawSolutionSpace(optimizationProblem: OptimizationProblemStrategy): ImageView {
        var inputStream = this.javaClass.classLoader.getResourceAsStream(optimizationProblem.contourPathJAR)
        var image : Image =
            if (inputStream != null) {
                Image(inputStream)
            } else {
                var file = File(optimizationProblem.contourPathIDE)
                Image(file.inputStream())
            }

        var imgView = ImageView(image)
        imgView.fitWidth = CANVAS_WIDTH
        imgView.fitHeight = CANVAS_HEIGHT
        imgView.isPreserveRatio = false
        return imgView
    }


    /**
     * Updates the statistics based on the information stored in the current [Generation] object
     */
    private fun updateStatistics(){
        Platform.runLater {
            var generation = generations[generationIndex]
            generationLabel.text = (generation.number + 1).toString()
            bestFitnessLabel.text = round((generation.bestFitness * 1000)/1000).toString()
            worstFitnessLabel.text = round((generation.worstFitness * 1000)/1000).toString()
            averageFitnessLabel.text = round((generation.averageFitness * 1000)/1000).toString()
            solutionLabel.text = "f(${round(generation.bestGenomeCoordinate.first* 1000/1000)},${round(generation.bestGenomeCoordinate.second* 100/100)}) = ${round(generation.currentSolution * 100) / 100}"
            //LOG.debug {"f(${generation.bestGenomeCoordinate.first},${generation.bestGenomeCoordinate.second}) = ${generation.currentSolution}"}
        }
    }

    /**
     * Resets some elements on the view e.g. stopping the animation and resetting some variable colors.
     */
    private fun reset(){
        LOG.debug { "Resetting..." }
        LOG.debug { "Stopping previous animation..." }
        animationGenomes.stop()
        LOG.debug { "Resetting label colors..." }
        parentSelectionLabel.textFill = Color.BLACK
        mutationLabel.textFill = Color.BLACK
        crossoverLabel.textFill = Color.BLACK
        survivorSelectionLabel.textFill = Color.BLACK
        LOG.debug { "Resetting index for drawing canvas" }
        operatorIndex = 0
        generationIndex = 0
        LOG.debug { "Consistent state is restored" }
    }
}