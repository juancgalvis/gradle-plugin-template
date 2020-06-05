
package gradle.test.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskContainer;

public class PluginTest implements Plugin<Project> {
    private static final String TASK_GROUP = "Gradle Plugin Test";

    public void apply(Project project) {
        TaskContainer taskContainer = project.getTasks();
        Task temp = taskContainer.create("createFromResource", MyTask.class);
        temp.setGroup(TASK_GROUP);
        temp.setDescription("create build.gradle from resource in jar");
    }

}
