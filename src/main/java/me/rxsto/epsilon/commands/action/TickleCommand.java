package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "tickle", actionText = "%s tickles %s :joy:", category = CommandCategory.ACTION)
public class TickleCommand extends ActionCommand {
}
