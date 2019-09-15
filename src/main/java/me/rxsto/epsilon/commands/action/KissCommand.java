package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "kiss", actionText = "%s kisses %s :two_hearts:", category = CommandCategory.ACTION)
public class KissCommand extends ActionCommand {
}
