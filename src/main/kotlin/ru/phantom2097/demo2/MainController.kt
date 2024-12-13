package ru.phantom2097.demo2

import ru.phantom2097.demo2.DocumentProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.IndexRange
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import javafx.scene.web.HTMLEditor
import javafx.scene.web.WebEngine
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.FileWriter
import java.net.URL
import java.util.*

class MainController : Initializable {

    companion object {
        lateinit var STAGE: Stage
    }
    @FXML
    lateinit var htmlEditor: HTMLEditor
    private lateinit var document: DocumentProperty


    @FXML
    private lateinit var borderPane: BorderPane

    @FXML
    private fun openFile(event: ActionEvent) {
        val fileChooser = FileChooser().apply {
            title = "Открыть файл"
            extensionFilters.add(
                FileChooser.ExtensionFilter("Documents", "*.doc", "*.docx", "*.txt", "*.html"),
            )
        }

        val file: File? = fileChooser.showOpenDialog(STAGE)
        if (file != null) {
            document = DocumentProperty(file.absolutePath)
//            htmlEditor.htmlText = GetHTMLXDocReport.getHTMLXDocReport(document.getPath())
            htmlEditor.htmlText = document.getHTMLXDocReport()
            borderPane.center = htmlEditor
            println(htmlEditor.htmlText)

        }
    }

    @FXML
    private fun saveFile() {
        // Открываем диалог сохранения файла
        val fileChooser = FileChooser().apply {
            title = "Сохранить файл"
            extensionFilters.add(
                FileChooser.ExtensionFilter("HTML Files", "*.html")
            )
        }

        // Получаем выбранный файл
        val file: File? = fileChooser.showSaveDialog(MainController.STAGE)

        if (file != null) {
            // Получаем текст из HTMLEditor
            val htmlContent = htmlEditor.htmlText

            // Пишем текст в файл
            try {
                FileWriter(file).use { writer ->
                    writer.write(htmlContent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @FXML
    override fun initialize(url: URL?, rb: ResourceBundle?) {

    }
}
