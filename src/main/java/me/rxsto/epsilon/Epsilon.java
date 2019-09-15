package me.rxsto.epsilon;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import me.rxsto.epsilon.core.commands.Command;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;
import me.rxsto.epsilon.core.handlers.CommandHandler;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import reactor.netty.http.client.HttpClient;

@Log4j2
public class Epsilon {

    private static final String PACKAGE = "me.rxsto.epsilon.commands";

    private static Epsilon instance;

    @Getter
    private final DiscordClient discordClient;
    @Getter
    private final CommandHandler commandHandler;
    @Getter
    private final HttpClient httpClient;

    private Epsilon(String[] args) {
        instance = this;

        discordClient = new DiscordClientBuilder(System.getenv("DISCORD_TOKEN")).build();
        commandHandler = new CommandHandler();
        httpClient = HttpClient.create();

        final Reflections reflections = new Reflections(PACKAGE);
        ClasspathHelper.forPackage(PACKAGE, Epsilon.class.getClassLoader()).forEach(reflections::scan);
        reflections.getTypesAnnotatedWith(CommandRegistry.class).forEach(clazz -> {
            try {
                final CommandRegistry commandRegistry = clazz.getAnnotation(CommandRegistry.class);
                if (Command.class.isAssignableFrom(clazz)) {
                    final Command command = (Command) clazz.getDeclaredConstructor().newInstance();
                    command.setName(commandRegistry.name());
                    command.setCategory(commandRegistry.category());
                    command.setAction(commandRegistry.actionText());
                    commandHandler.register(command);
                    log.info("Registered command \"{}\"", command.getName());
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        });

        discordClient.getEventDispatcher().on(MessageCreateEvent.class)
                .flatMap(commandHandler::handle)
                .subscribe();

        discordClient.login().block();
    }

    public static void main(String[] args) {
        new Epsilon(args);
    }

    public static Epsilon getInstance() {
        return instance;
    }
}
