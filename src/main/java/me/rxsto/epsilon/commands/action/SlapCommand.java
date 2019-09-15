package me.rxsto.epsilon.commands.action;

import me.rxsto.epsilon.core.commands.ActionCommand;
import me.rxsto.epsilon.core.commands.CommandCategory;
import me.rxsto.epsilon.core.commands.registry.CommandRegistry;

@SuppressWarnings("unused")
@CommandRegistry(name = "slap", actionText = "%s slaps %s :dizzy_face:", category = CommandCategory.ACTION)
public class SlapCommand extends ActionCommand {
}
