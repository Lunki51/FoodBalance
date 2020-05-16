package lunki.modify.foodbalance.runnable;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.Message;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class ColdRunnable implements Runnable {

    FoodBalance plugin;
    public ColdRunnable(FoodBalance plugin) {
        this.plugin=plugin;
    }

    @Override
    public void run() {
        return;
        /*for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)){
                int cold = plugin.getValueManager().getValue(FoodType.COLD, player);


                int min = 0;
                Biome biome = player.getLocation().getBlock().getBiome();
                if(biome.equals(Biome.SNOWY_BEACH) || biome.equals(Biome.SNOWY_MOUNTAINS) || biome.equals(Biome.SNOWY_TAIGA)
                        || biome.equals(Biome.SNOWY_TAIGA_HILLS) || biome.equals(Biome.SNOWY_TAIGA_MOUNTAINS)
                        || biome.equals(Biome.SNOWY_TUNDRA) || biome.equals(Biome.FROZEN_OCEAN)
                        || biome.equals(Biome.FROZEN_RIVER) || biome.equals(Biome.DEEP_FROZEN_OCEAN)){

                    min++;

                    int buff=1;

                    ItemStack[] armor = player.getInventory().getArmorContents();

                    for(ItemStack stack : armor){
                        if(stack==null)continue;
                        if(stack.getType().equals(Material.IRON_BOOTS) ||  stack.getType().equals(Material.CHAINMAIL_BOOTS))min++;
                        if(stack.getType().equals(Material.IRON_CHESTPLATE) ||  stack.getType().equals(Material.CHAINMAIL_BOOTS))min++;
                        if(stack.getType().equals(Material.IRON_HELMET) ||  stack.getType().equals(Material.CHAINMAIL_BOOTS))min++;
                        if(stack.getType().equals(Material.IRON_LEGGINGS) ||  stack.getType().equals(Material.CHAINMAIL_BOOTS))min++;
                    }

                    for(ItemStack stack : armor){
                        if(stack==null)continue;
                        if(stack.getType().equals(Material.LEATHER_BOOTS) && stack.getItemMeta().getDisplayName().equals("Fur Boots"))buff-=0.15;
                        if(stack.getType().equals(Material.LEATHER_CHESTPLATE) && stack.getItemMeta().getDisplayName().equals("Fur Chestplate"))buff-=0.15;
                        if(stack.getType().equals(Material.LEATHER_HELMET) && stack.getItemMeta().getDisplayName().equals("Fur Helmet"))buff-=0.15;
                        if(stack.getType().equals(Material.LEATHER_LEGGINGS) && stack.getItemMeta().getDisplayName().equals("Fur Leggings"))buff-=0.15;
                    }
                    buff = buff==0.60?0:buff;
                    min*=buff;
                    if(player.getLocation().getBlock().getType().equals(Material.WATER)){
                        min*=10;
                    }
                }else{
                    min-=5;
                }

                cold-=min;

                if(cold<=500){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, true, false, false));
                }
                if(cold<=400){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 4, true, false, false));
                }
                if(cold<=250){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 6, true, false, false));
                    Random rand = new Random();
                    int chance = rand.nextInt(7);
                    if(chance==0){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,40,1,true, false, true));
                    }
                }
                if(cold<=100){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 8, true, false, false));

                }
                cold = cold < 0 ? 0 : cold > 1000 ? 1000 : cold;
                if(cold<=0){
                    plugin.getDeathMessages().getDeathMessageBans().add(player.getUniqueId().toString());
                    Bukkit.broadcastMessage(Broadcast.getMsg(Message.DEATH_COLD).replace("%player%", player.getName()));
                    plugin.getValueManager().resetValues(player);
                    player.damage(9.9999999E7D);
                }else{
                    plugin.getValueManager().setValue(FoodType.COLD,player,cold);
                }
            }
        }*/
    }

}
