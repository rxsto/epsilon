package me.rxsto.epsilon.core.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;
import me.rxsto.epsilon.Epsilon;
import org.json.JSONObject;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.NoSuchElementException;

public class ActionCommand extends Command {

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return Mono.just(event.getMessage())
                .zipWith(event.getMessage().getChannel())
                .flatMap(tuple -> {
                    String author = tuple.getT1()
                            .getAuthor()
                            .map(User::getMention)
                            .orElseThrow(NoSuchElementException::new);
                    return tuple.getT1()
                            .getUserMentions()
                            .take(1)
                            .last()
                            .map(User::getMention)
                            .map(target -> String.format(this.getAction(), author, target))
                            .zipWith(getImage(this.getName()))
                            .map(contentTuple -> Tuples.of(contentTuple.getT1(), contentTuple.getT2(), tuple.getT2()));
                })
                .flatMap(tuple -> tuple.getT3().createEmbed(spec -> spec.setDescription(tuple.getT1()).setImage(new JSONObject(tuple.getT2()).getString("url"))))
                .then();
    }

    private static Mono<String> getImage(String action) {
        return Epsilon.getInstance()
                .getHttpClient()
                .baseUrl("https://nekos.life/api/v2")
                .get()
                .uri(String.format("/img/%s", action))
                .responseContent()
                .aggregate()
                .asString();
    }
}
