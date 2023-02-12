package cn.kingen.commons.system.cmd;

import java.io.IOException;

/**
 * An executor of command lines for a given executable application which usually ends with .exe on
 * Windows.
 *
 * @author Kingen
 */
public class CommandExecutor {

    /**
     * path of the executable.
     */
    private final String executablePath;

    public CommandExecutor(String executablePath) {
        // todo whether the path is executable
        this.executablePath = executablePath;
    }

    /**
     * Create a task with given arguments.
     */
    public CommandTask createTask(String... args) {
        return new CommandTask(this, args);
    }

    /**
     * Create a task and execute the task.
     */
    public int execute(String... args) throws IOException {
        CommandTask task = new CommandTask(this, args);
        try {
            task.execute();
            return task.exitValue();
        } finally {
            task.destroy();
        }
    }

    public String getExecutablePath() {
        return executablePath;
    }
}
