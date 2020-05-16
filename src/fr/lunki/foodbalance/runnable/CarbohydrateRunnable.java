package lunki.modify.foodbalance.runnable;

import java.util.List;
import java.util.Random;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.Message;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CarbohydrateRunnable implements Runnable
{
  public static FoodBalance plugin;
  
  public CarbohydrateRunnable(FoodBalance instance)
  {
    plugin = instance;
  }
  
  public void run()
  {
    List<String> playerbypass = plugin.getData().getStringList("nutrientBP");
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getGameMode() != org.bukkit.GameMode.CREATIVE || !playerbypass.contains(player.getUniqueId().toString())) {
        int currentCarbo = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
        currentCarbo = currentCarbo > 7 ? 7 : currentCarbo;
        currentCarbo = currentCarbo < -7 ? -7 : currentCarbo;

        Random ran = new Random();
        if (currentCarbo <= -6) player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, ran.nextInt(20) * 20, 0, true, false, false));
      }
    }
  }
}
