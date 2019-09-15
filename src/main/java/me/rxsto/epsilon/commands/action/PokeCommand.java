package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "poke", actionText = "%s pokes %s :point_left:", category = CommandCategory.ACTION)
public class PokeCommand extends ActionCommand {
}
