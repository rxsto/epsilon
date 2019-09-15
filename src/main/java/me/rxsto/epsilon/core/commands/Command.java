package me.rxsto.epsilon.core.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public abstract class Command {

    private String name;

    private CommandCategory category;

    private String action;

    public abstract Mono<Void> execute(MessageCreateEvent event);
}
