package lunki.modify.foodbalance.listener;

import java.util.Random;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class RegenListener implements Listener
{
  public static FoodBalance plugin;
  
  public RegenListener(FoodBalance instance)
  {
    plugin = instance;
  }
  
  @EventHandler
  public void onEntityRegainHealth(EntityRegainHealthEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    
    if (event.getRegainReason() != EntityRegainHealthEvent.RegainReason.SATIATED) {
      return;
    }
    
    Player player = (Player)event.getEntity();
    int proteinValue = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
    
    Random ran = new Random();
    if (proteinValue <= -7) {
      if (ran.nextInt(2) == 0) {
        event.setCancelled(true);
      }
    } else if ((proteinValue <= -6) && 
      (ran.nextInt(4) > 0)) {
      event.setCancelled(true);
    }
  }
}
