package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "pat", actionText = "%s pats %s :relaxed:", category = CommandCategory.ACTION)
public class PatCommand extends ActionCommand {
}
