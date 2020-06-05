package gradle.test.plugin;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileModel {
    private final String path;
    private final String content;
}
