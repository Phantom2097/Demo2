package ru.phantom2097.demo2


import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Control
import javafx.stage.Stage

class DocumentPropertyApplication: Application() {
    override fun start(stage: Stage) {

        MainController.STAGE = stage
        val fxml = FXMLLoader(DocumentPropertyApplication::class.java.getResource("scene.fxml"))
        val scene = Scene(fxml.load())
        stage.title = ("Мой блокнот")
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    launch(DocumentPropertyApplication::class.java)
}