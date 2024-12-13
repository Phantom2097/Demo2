package ru.phantom2097.demo2


import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.ToXMLContentHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tika.parser.AutoDetectParser;



//@Suppress("DEPRECATION")
class DocumentProperty(private val path: String) {

//    fun getHTML(): String {
//        val text = Files.newInputStream(Paths.get(path))
//        val tikaParser = AutoDetectParser()
//        val handler = ToXMLContentHandler()
//        val context = ParseContext().also {
//            it.set(javaClass, tikaParser)
//        }
//        tikaParser.parse(text, handler, Metadata(), context)
//        return handler.toString()
//    }

    //    fun getPath() = path
    @Throws(IOException::class)
    fun getHTMLXDocReport(): String {
        // Открытие документа в формате DOCX
        val inputStream: InputStream = Files.newInputStream(Paths.get(path))
        val document = XWPFDocument(inputStream)

        // Настройка опций для преобразования
        val options = XHTMLOptions.create().URIResolver(FileURIResolver(File("word/media")))

        // Поток для вывода результата
        val outputStream: OutputStream = ByteArrayOutputStream()

        // Преобразование документа в HTML
        XHTMLConverter.getInstance().convert(document, outputStream, options)

        // Возвращаем результат как строку
        return outputStream.toString()
    }


}