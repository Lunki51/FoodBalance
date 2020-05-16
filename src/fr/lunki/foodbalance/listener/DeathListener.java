package lunki.modify.foodbalance.listener;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.Message;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements org.bukkit.event.Listener {
    public static FoodBalance plugin;

    public DeathListener(FoodBalance instance) {
        plugin = instance;
    }

    @org.bukkit.event.EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if ((event.getEntity().getLastDamageCause() != null) &&
                (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.WITHER) &&
                (plugin.getValueManager().getValue(FoodType.PROTEIN, event.getEntity()) <= -6)) {
            event.setDeathMessage(Broadcast.getMsg(Message.DEATH_PROTEINS_LACK));
            plugin.getValueManager().resetValues(event.getEntity());
            return;
        }

        Player player = event.getEntity();
        String uuid = player.getUniqueId().toString();

        if (plugin.getDeathMessages().getDeathMessageBans().contains(uuid)) {
            event.setDeathMessage(null);
            plugin.getDeathMessages().getDeathMessageBans().remove(uuid);
        }

        plugin.getValueManager().resetValues(player);
    }
}
