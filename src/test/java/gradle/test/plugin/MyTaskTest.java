package gradle.test.plugin;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class MyTaskTest {

    @Test
    public void test() throws IOException {
        Project project = ProjectBuilder.builder().withProjectDir(new File("build/unitTest")).build();
        project.getTasks().create("test", MyTask.class);

        MyTask task = (MyTask) project.getTasks().getByName("test");

        task.generateStructureTask();
    }
}
