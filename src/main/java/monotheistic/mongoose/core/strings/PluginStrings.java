package monotheistic.mongoose.core.strings;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public abstract class PluginStrings {

    private static ChatColor MAIN_COLOR;
    private static ChatColor SECONDARY_COLOR;

    private static String TAG;
    private static String INVALID_SYNTAX;
    private static String NO_PERMS;
    private static String CMD_LABEL;
    private static String CMD_LABEL_WITH_SLASH;
    private static String MUST_BE_PLAYER;


    public static void setup(JavaPlugin plugin, ChatColor main, ChatColor secondary, String nameForTag, String commandLabel) {
        MAIN_COLOR = main;
        SECONDARY_COLOR = secondary;
        TAG = secondary + "[" + main + (nameForTag == null ? plugin.getDescription().getName() : nameForTag) + secondary + "]" + ChatColor.RESET;
        INVALID_SYNTAX = TAG + ChatColor.RED + " Invalid syntax! Correct syntax is ";
        NO_PERMS = TAG + ChatColor.RED + " You do not have permission to use this command!";
        CMD_LABEL = commandLabel;
        CMD_LABEL_WITH_SLASH = "/" + CMD_LABEL;
        MUST_BE_PLAYER = TAG + ChatColor.RED + " You must be a player in order to use this command!";

    }

    public static String tag() {
        return TAG;
    }

    public static String mainCmdLabel(boolean withSlash) {
        return withSlash ? CMD_LABEL_WITH_SLASH : CMD_LABEL;
    }

    public static ChatColor mainColor() {
        return MAIN_COLOR;
    }

    public static ChatColor secondaryColor() {
        return SECONDARY_COLOR;
    }

    public static String invalidSyntax() {
        return INVALID_SYNTAX;
    }

    public static String noPerms() {
        return NO_PERMS;
    }

    public static String mustBeAPlayer() {
        return MUST_BE_PLAYER;
    }

    public static void nullifyEverything() {
        MAIN_COLOR = null;
        SECONDARY_COLOR = null;
        TAG = null;
        INVALID_SYNTAX = null;
        NO_PERMS = null;
        CMD_LABEL_WITH_SLASH = null;
        CMD_LABEL = null;
        MUST_BE_PLAYER = null;
    }

}
