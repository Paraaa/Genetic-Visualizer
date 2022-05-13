package com.geneticVisualizer

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class MainApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(MainApplication::class.java.getResource("MainView.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Genetic Visualizer"
        stage.scene = scene
        stage.icons.add(Image(MainApplication::class.java.getResourceAsStream("icon.png")))
        stage.isResizable = true
        stage.show()
    }
}

fun main() {
    Application.launch(MainApplication::class.java)
}