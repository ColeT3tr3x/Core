package monotheistic.mongoose.core.gui;

import com.gitlab.avelyn.architecture.base.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.function.Consumer;

import static com.gitlab.avelyn.core.base.Events.listen;

public class GUIListener extends Component {
    public GUIListener() {
        addChild(listen((InventoryClickEvent event) -> {
            if (event.getClickedInventory() == null)
                return;
            final InventoryHolder holder = event.getClickedInventory().getHolder();
            if (holder instanceof MyGUI.InventoryHolderImpl) {
                event.setCancelled(true);

                final MyGUI.InventoryHolderImpl inventoryHolder = (MyGUI.InventoryHolderImpl) holder;
                inventoryHolder.allTimeListeners.forEach(listener -> listener.accept(event));
                Consumer<InventoryClickEvent> action = inventoryHolder.listeners.get(event.getCurrentItem());
                if (action != null) {
                    action.accept(event);
                }
            }
        }));
    }
}
