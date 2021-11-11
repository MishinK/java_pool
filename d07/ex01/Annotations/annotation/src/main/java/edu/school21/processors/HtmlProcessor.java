package edu.school21.processors;

import edu.school21.annotations.HtmlForm;
import edu.school21.annotations.HtmlInput;
import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.SourceVersion;
import javax.naming.spi.DirectoryManager;
import javax.tools.Diagnostic;
import java.io.*;
import java.util.Set;
import java.util.StringJoiner;
import com.google.auto.service.AutoService;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("edu.school21.annotations.HtmlForm")
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            if (elem.getKind() == ElementKind.CLASS) {
                
				HtmlForm htmlForm = elem.getAnnotation(HtmlForm.class);
                String html_string = "";
                
				html_string += "<form action = \"" + htmlForm.action() + "\" method = \"" + htmlForm.method() + "\">\n";
                for (Element e : elem.getEnclosedElements()) {
                    if (e.getKind() == ElementKind.FIELD && e.getAnnotation(HtmlInput.class) != null) {
						HtmlInput htmlInput = e.getAnnotation(HtmlInput.class);
                        html_string += "\t<input type = \"" + htmlInput.type() + "\" name = \"" + htmlInput.name() + "\" placeholder = \"" + htmlInput.placeholder() + "\">\n";
					}
                }
                html_string += "\t<input type = \"submit\" value = \"Send\">\n</form>\n";

                try {
                    File file = new File("core/target/classes/" + htmlForm.fileName());
					file.createNewFile();
					FileOutputStream fileOutputStream = new FileOutputStream(file, false);
					fileOutputStream.write(html_string.getBytes());
					fileOutputStream.close();
                } catch (IOException e) {
                    messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                }
            }
            else
                messager.printMessage(Diagnostic.Kind.ERROR, elem.getKind() + " isn't expected.");
        }
        return false;
    }
}