package monotheistic.mongoose.core.components.commands;

import monotheistic.mongoose.core.strings.PluginStrings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collection;

public class CommandManager extends AbstractCommandManager {

    public CommandManager(final JavaPlugin plugin, final Collection<SubCommand> commands) {
        super(plugin, commands);

    }

    public CommandManager(final JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (strings.length < 1) {
            commandSender.sendMessage(PluginStrings.tag() + " Please specify a subcommand");
            return false;
        }
        for (SubCommand subCommand : super.getCommands()) {
            if (subCommand.name().equalsIgnoreCase(strings[0])) {
                if (commandSender.hasPermission(subCommand.requiredPermissions()) || commandSender.isOp())
                    return subCommand.onCommand(commandSender, Arrays.copyOfRange(strings, 1, strings.length));
                else {
                    commandSender.sendMessage(PluginStrings.noPerms());
                    return false;
                }
            } else {
                if (subCommand.aliases().isPresent())
                    for (String alias : subCommand.aliases().get()) {
                        if (alias.equalsIgnoreCase(strings[0])) {
                            return subCommand.onCommand(commandSender, strings);
                        }
                    }
            }
        }
        commandSender.sendMessage(PluginStrings.tag() + " Unknown Command!");
        return false;
    }
}
