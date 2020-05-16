package lunki.modify.foodbalance.listener;

import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CakeListener implements Listener
{
  public static FoodBalance plugin;
  
  public CakeListener(FoodBalance instance)
  {
    plugin = instance;
  }


 /* @org.bukkit.event.EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    org.bukkit.entity.Player player = event.getPlayer();
    if (event.getClickedBlock().getType() == Material.CAKE) {
      int currentCarbohydrates = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
      currentCarbohydrates = currentCarbohydrates + 2 > 7 ? 7 : currentCarbohydrates + 2;
      plugin.getValueManager().setValue(FoodType.CARBOHYDRATE, player, currentCarbohydrates);
    }
  }*/
}
