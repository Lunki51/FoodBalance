package lunki.modify.foodbalance.runnable;

import java.util.List;
import java.util.Random;

import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VitaminRunnable implements Runnable
{
  public static FoodBalance plugin;
  
  public VitaminRunnable(FoodBalance instance)
  {
    plugin = instance;
  }
  
  public void run()
  {
    List<String> playerbypass = plugin.getData().getStringList("nutrientBP");
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getGameMode() != org.bukkit.GameMode.CREATIVE || !playerbypass.contains(player.getUniqueId().toString())) {
        int currentVitamin = plugin.getValueManager().getValue(FoodType.VITAMIN, player);
        currentVitamin = currentVitamin > 7 ? 7 : currentVitamin;
        currentVitamin = currentVitamin < -7 ? -7 : currentVitamin;
        
        Random ran = new Random();
        if (currentVitamin <= -6) player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, ran.nextInt(20) * 20, 0,true, false, true));
      }
    }
  }
}
