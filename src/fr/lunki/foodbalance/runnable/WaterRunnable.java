package lunki.modify.foodbalance.runnable;

import java.util.List;
import java.util.Random;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.Message;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterRunnable implements Runnable
{
  public static FoodBalance plugin;
  
  public WaterRunnable(FoodBalance instance)
  {
    plugin = instance;
  }
  public void run()
  {
    List<String> playerbypass = plugin.getData().getStringList("waterBP");
    for (Player player : Bukkit.getOnlinePlayers()) {
      if ((player.getGameMode() != GameMode.CREATIVE) && (player.getGameMode() != GameMode.SPECTATOR) && (!playerbypass.contains(player.getUniqueId().toString()))) {
        int currentWater = plugin.getValueManager().getValue(FoodType.WATER, player) - 1;
        int oldWater = plugin.getValueManager().getValue(FoodType.WATER, player);
        currentWater = currentWater < 0 ? 0 : currentWater;
        currentWater = currentWater > 1800 ? 1800 : currentWater;


        Biome biome = player.getLocation().getBlock().getBiome();
        if ((biome == Biome.DESERT) || (biome == Biome.DESERT_HILLS) || (biome == Biome.DESERT_LAKES) ||  (biome == Biome.NETHER)) {
          currentWater -= 2;
        } else if ((biome == Biome.SAVANNA) || (biome == Biome.SHATTERED_SAVANNA) || (biome == Biome.SAVANNA_PLATEAU) ||
          (biome == Biome.SHATTERED_SAVANNA_PLATEAU) || (biome == Biome.JUNGLE) || (biome == Biome.JUNGLE_EDGE) ||
          (biome == Biome.MODIFIED_JUNGLE) ||(biome == Biome.MODIFIED_JUNGLE_EDGE) || (biome == Biome.JUNGLE_HILLS) ||
          (biome == Biome.BADLANDS) || (biome == Biome.BADLANDS_PLATEAU) || (biome == Biome.ERODED_BADLANDS) ||
          (biome == Biome.MODIFIED_BADLANDS_PLATEAU) || (biome == Biome.MODIFIED_WOODED_BADLANDS_PLATEAU) ||
          (biome == Biome.WOODED_BADLANDS_PLATEAU)) {
          currentWater--;
        }
        

        if (player.isSprinting()) {
          currentWater--;
        }


        Random ran = new Random();
        if (currentWater <= 90.0D) {
          if (ran.nextInt(10) == 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(10) + 10) * 20, 1,true,false,false));
          }
          if (ran.nextInt(10) == 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(10) + 10) * 20, 1,true,false,false));
          }
          if (ran.nextInt(10) == 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(10) + 10) * 20, 1,true,false,false));
          }
          if (ran.nextInt(3) == 0) {
            if (player.getHealth() <= 1.0D) {
              if(!player.isDead()){
                plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
                Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_HYDRATION).replace("%player%", player.getName()));
                plugin.getValueManager().resetValues(player);
                player.damage(9999999.0D);
              }
            } else {
              player.damage(1.0D);

            }
            

          }
        }


        else if (currentWater <= 180.0D) {
          if (ran.nextInt(15) == 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, ran.nextInt(20) * 20, 0));
          }
          if (ran.nextInt(15) == 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, ran.nextInt(20) * 20, 0));
          }
          if (ran.nextInt(15) == 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, ran.nextInt(20) * 20, 0));
          }
        }

        if (currentWater == 0) {
          plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
          Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_HYDRATION).replace("%player%", player.getName()));
          plugin.getValueManager().resetValues(player);
          player.damage(9.9999999E7D);
        } else {
          plugin.getValueManager().setValue(FoodType.WATER, player, currentWater);
        }


      }
    }
  }
}
