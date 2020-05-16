package lunki.modify.foodbalance.listener;

import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    public static FoodBalance plugin;

    public JoinListener(FoodBalance instance) {
        plugin = instance;
    }

    @org.bukkit.event.EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (plugin.getValueManager().getValue(FoodType.WATER, player) == 0) {
            plugin.getValueManager().resetValues(player);
        }

        if (!player.hasPermission("foodbalance")) {
            return;
        }

        if (!plugin.getConfig().getBoolean("updates.notify-admin")) {
            return;
        }
    }
}
