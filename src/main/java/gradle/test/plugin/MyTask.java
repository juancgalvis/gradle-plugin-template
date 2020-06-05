package gradle.test.plugin;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class MyTask extends DefaultTask {
    @TaskAction
    public void generateStructureTask() throws IOException {
        FileModel file = FileModel.builder()
                .path("build.gradle.generated")
                .content(getContentFromResource())
                .build();

        writeString(getProject(), file.getPath(), file.getContent());

    }
//
//    public String getContentFromResource() {
//        getClass().getResourceAsStream("build.gradle.mustache");
//    }

    public String getContentFromResource() {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("build.gradle.mustache");
        Map<String, Object> context = new HashMap<>();
        context.put("springBootVersion", "2.3");
        context.put("sonarVersion", "2.7");
        context.put("jacocoVersion", "0.8.5");

        StringWriter stringWriter = new StringWriter();
        mustache.execute(stringWriter, context);
        return stringWriter.toString();
    }

    public static void writeString(Project project, String filePath, String data) throws IOException {
        project.getLogger().debug(project.file(filePath).getAbsolutePath());
        File file = project
                .file(filePath)
                .getAbsoluteFile();

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data);
        }
    }
}
