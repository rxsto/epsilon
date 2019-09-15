package me.rxsto.epsilon.core.handlers;

import discord4j.core.event.domain.message.MessageCreateEvent;
import lombok.Getter;
import me.rxsto.epsilon.core.commands.Command;
import me.rxsto.epsilon.core.commands.ParsedCommand;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    @Getter
    private Map<String, Command> commands;

    public CommandHandler() {
        this.commands = new HashMap<>();
    }

    public void register(Command command) {
        this.commands.put(command.getName(), command);
    }

    public Mono<Void> handle(MessageCreateEvent event) {
        return Mono.justOrEmpty(event.getMessage().getContent())
                .map(ParsedCommand::new)
                .filter(parsedCommand -> commands.containsKey(parsedCommand.getCommand()))
                .map(parsedCommand -> commands.get(parsedCommand.getCommand()))
                .flatMap(command -> command.execute(event));
    }
}
