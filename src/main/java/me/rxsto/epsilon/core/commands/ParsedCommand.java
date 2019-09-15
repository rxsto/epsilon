package me.rxsto.epsilon.core.commands;

public class ParsedCommand {

    private String command;
    private String content;

    public ParsedCommand(String raw) {
        var prefix = System.getenv("PREFIX");

        if (!raw.startsWith(prefix)) {
            return;
        }

        this.command = raw.substring(prefix.length()).split(" ")[0];
        this.content = raw.substring(prefix.length() + command.length()).trim();
    }

    public String getCommand() {
        return command;
    }

    public String getContent() {
        return content;
    }
}
