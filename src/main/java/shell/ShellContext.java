package shell;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellContext {

    private Path currentDirectory;

    public ShellContext() {
        this.currentDirectory = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public boolean changeDirectory(Path targetPath) {
        if (!Files.isDirectory(targetPath)) {
            return false;
        }

        currentDirectory = targetPath.toAbsolutePath().normalize();
        System.setProperty("user.dir", currentDirectory.toString());
        return true;
    }

    public Path resolvePath(String rawPath) {
        if (rawPath == null || rawPath.isBlank()) {
            return currentDirectory;
        }

        if (rawPath.equals("~") || rawPath.startsWith("~/")) {
            String homeDirectory = System.getenv("HOME");
            if (homeDirectory != null && !homeDirectory.isBlank()) {
                if (rawPath.equals("~")) {
                    return Paths.get(homeDirectory).toAbsolutePath().normalize();
                }
                return Paths.get(homeDirectory, rawPath.substring(2)).toAbsolutePath().normalize();
            }
        }

        Path path = Paths.get(rawPath);
        if (!path.isAbsolute()) {
            path = currentDirectory.resolve(path);
        }

        return path.normalize();
    }
}
