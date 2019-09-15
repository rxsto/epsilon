package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "feed", actionText = "%s feeds %s :carrot:", category = CommandCategory.ACTION)
public class FeedCommand extends ActionCommand {
}
