module ru.phantom2097.demo2 {
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires fr.opensagres.poi.xwpf.converter.core;
    requires fr.opensagres.poi.xwpf.converter.xhtml;
    requires org.apache.poi.ooxml;
    requires org.apache.tika.core;
    requires javafx.web;
    requires html2openxml;
    requires org.docx4j.core;


    opens ru.phantom2097.demo2 to javafx.fxml;
    exports ru.phantom2097.demo2;
}