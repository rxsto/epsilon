package me.rxsto.epsilon.commands.general;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import me.rxsto.epsilon.Epsilon;
import me.rxsto.epsilon.core.commands.Command;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@CommandRegistry(name = "help")
public class HelpCommand extends Command {

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return Mono.just(event)
                .map(MessageCreateEvent::getMessage)
                .flatMap(Message::getChannel)
                .flatMap(channel -> {
                    Map<String, StringBuilder> mappedCommands = new HashMap<>();
                    for (CommandCategory commandCategory : CommandCategory.values()) {
                        StringBuilder commands = new StringBuilder();
                        Epsilon.getInstance().getCommandHandler().getCommands().forEach((name, command) -> {
                            if (command.getCategory().equals(commandCategory)) {
                                commands.append(String.format("`%s` ", name));
                            }
                        });
                        mappedCommands.put(commandCategory.name(), commands);
                    }
                    return channel.createEmbed(spec -> {
                        mappedCommands.forEach((category, commands) -> {
                            spec.addField(category.toLowerCase(), commands.toString(), false);
                        });
                        spec.setFooter("A complete list of all available commands", null);
                    });
                })
                .then();
    }
}
