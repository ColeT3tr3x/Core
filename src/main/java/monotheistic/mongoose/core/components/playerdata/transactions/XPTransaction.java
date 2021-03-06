package monotheistic.mongoose.core.components.playerdata.transactions;

import monotheistic.mongoose.core.components.playerdata.PlayerData;
import monotheistic.mongoose.core.components.playerdata.database.Database;
import monotheistic.mongoose.core.components.playerdata.modules.xp.XPPlayer;
import monotheistic.mongoose.core.strings.PluginStrings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XPTransaction extends Transaction {


    public XPTransaction(Player giver, Player taker, int amt, Database database) {
        super(giver, taker, amt, database);
    }

    @Override
    public boolean execute() {
        final PlayerData giver = super.getGiver() == null ? null : getDatabase().fromCache(super.getGiver()).orElseThrow(() -> new RuntimeException("Player data not found in cache!"));
        final PlayerData taker = super.getTaker() == null ? null : getDatabase().fromCache(super.getTaker()).orElseThrow(() -> new RuntimeException("Player data not found in cache!"));
        XPPlayer ecoGiver;
        XPPlayer ecoTaker;
        if (!((giver == null || giver instanceof XPPlayer) && (taker == null || taker instanceof XPPlayer))) {
            throw new IllegalArgumentException("Non-XPlayers cannot be used in a XP Transaction!");
        } else {
            ecoGiver = (XPPlayer) giver;
            ecoTaker = (XPPlayer) taker;
        }
        if (allAreNull()) {
            throw new IllegalArgumentException("Both objects implementing EcoPlayer cannot be null!");
        }
        if (ecoGiver != null) {
            ecoGiver.takeXp((int) super.getAmt());
            super.getGiver().sendMessage(PluginStrings.tag() + ChatColor.GREEN + " Transaction complete.");
        }
        if (taker != null) {
            ecoTaker.giveXp((int) super.getAmt());
            super.getTaker().sendMessage(PluginStrings.tag() + ChatColor.GREEN + " Transaction complete.");
        }
        return true;
    }


}
