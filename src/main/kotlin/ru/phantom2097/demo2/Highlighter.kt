package ru.phantom2097.demo2

import com.denisfesenko.converter.HtmlToOpenXMLConverter
import fr.opensagres.poi.xwpf.converter.core.FileURIResolver
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.docx4j.openpackaging.exceptions.Docx4JException
import org.docx4j.openpackaging.exceptions.InvalidFormatException
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Paths


object Highlighter {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        highlight(
            "C:\\Users\\cosmo\\IdeaProjects\\Demo2\\DocxFiles\\бипки.docx",
            "C:\\Users\\cosmo\\IdeaProjects\\Demo2\\DocxFiles\\new_бипки.docx",
            "бипки"
        )
    }

    @Throws(IOException::class)
    fun highlight(fileIn: String?, fileOut: String?, word: String) {
        var text = docxToHTML(fileIn)
        text = text.replace(word.toRegex(), "<span style=\"background-color: #ffff00;\">$word</span>")
        _HTMLToDocx(text, fileOut)
    }

    @Throws(IOException::class)
    fun docxToHTML(fileIn: String?): String {
        val `is` = Files.newInputStream(Paths.get(fileIn))
        val document = XWPFDocument(`is`)
        val options = XHTMLOptions.create().URIResolver(FileURIResolver(File("word/media")))

        val out: OutputStream = ByteArrayOutputStream()


        XHTMLConverter.getInstance().convert(document, out, options)
        return out.toString()
    }

    private fun _HTMLToDocx(_HTMLString: String?, fileOut: String?) {
        try {
            val converter = HtmlToOpenXMLConverter()
            val wordDocument = converter.convert(_HTMLString)

            val outputFile = fileOut?.let { File(it) }
            wordDocument.save(outputFile)
        } catch (e: InvalidFormatException) {
            throw RuntimeException(e)
        } catch (e: Docx4JException) {
            throw RuntimeException(e)
        }
    }
}
