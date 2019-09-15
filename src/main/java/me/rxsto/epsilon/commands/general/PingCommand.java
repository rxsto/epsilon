package me.rxsto.epsilon.commands.general;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import me.rxsto.epsilon.core.commands.Command;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@SuppressWarnings("unused")
@CommandRegistry(name = "ping")
public class PingCommand extends Command {

    @Override
    public Mono<Void> execute(MessageCreateEvent rawEvent) {
        return Mono.just(rawEvent)
                .map(MessageCreateEvent::getMessage)
                .flatMap(Message::getChannel)
                .flatMap(channel -> {
                    var start = Instant.now();
                    return channel.createMessage("Calculating ...")
                            .flatMap(message -> {
                                var end = Instant.now();
                                var difference = Duration.between(start, end).toMillis();
                                var content = String.format("%sms", difference);
                                return message.edit(spec -> spec.setContent(content));
                            });
                })
                .then();
    }
}
