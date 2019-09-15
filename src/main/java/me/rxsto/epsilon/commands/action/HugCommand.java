package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "hug", actionText = "%s hugs %s :hugging:", category = CommandCategory.ACTION)
public class HugCommand extends ActionCommand {
}
