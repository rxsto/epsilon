package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "cuddle", actionText = "%s cuddles %s :blush:", category = CommandCategory.ACTION)
public class CuddleCommand extends ActionCommand {
}
