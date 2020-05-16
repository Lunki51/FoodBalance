package lunki.modify.foodbalance.listener;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.food.Food;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EatListener implements org.bukkit.event.Listener {
    public static FoodBalance plugin;

    public EatListener(FoodBalance instance) {
        plugin = instance;
    }

    @org.bukkit.event.EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        List<String> playerbypass = plugin.getData().getStringList("nutrientBP");
        if (event.getPlayer().getGameMode() != org.bukkit.GameMode.CREATIVE || !playerbypass.contains(event.getPlayer().getUniqueId().toString())) {
            ItemStack itemEaten = event.getItem();
            org.bukkit.entity.Player player = event.getPlayer();
            List<Food> foods = plugin.getFoodManager().getFoods();

            Food food = null;
            for (int i = 0; i < foods.size(); i++) {
                food = (Food) foods.get(i);
                if (itemEaten.getType() == food.getItem()) {
                    break;
                }
            }

            if (food.getItem() != Material.GOLDEN_APPLE) {
                int carbohydratesCurrent = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
                int proteinsCurrent = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
                int vitaminsCurrent = plugin.getValueManager().getValue(FoodType.VITAMIN, player);
                int waterCurrent = plugin.getValueManager().getValue(FoodType.WATER, player);


                int carbohydrates = 0;
                int proteins = 0;
                int vitamins = 0;
                int water = 0;


                if (food.getValues().getTypes().containsKey(FoodType.CARBOHYDRATE)) {
                    carbohydrates = ((Integer) food.getValues().getTypes().get(FoodType.CARBOHYDRATE)).intValue();
                }
                if (food.getValues().getTypes().containsKey(FoodType.PROTEIN)) {
                    proteins = ((Integer) food.getValues().getTypes().get(FoodType.PROTEIN)).intValue();
                }
                if (food.getValues().getTypes().containsKey(FoodType.VITAMIN)) {
                    vitamins = ((Integer) food.getValues().getTypes().get(FoodType.VITAMIN)).intValue();
                }
                if (food.getValues().getTypes().containsKey(FoodType.WATER) && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    water = ((Integer) food.getValues().getTypes().get(FoodType.WATER)).intValue();
                }

                if (carbohydrates != 0) {
                    carbohydratesCurrent += carbohydrates;
                }

                if (proteins != 0) {
                    proteinsCurrent += proteins;
                }

                if (vitamins != 0) {
                    vitaminsCurrent += vitamins;
                }

                if (carbohydrates != 0 || proteins != 0 || vitamins != 0) {
                    if (carbohydrates == 0) {
                        carbohydratesCurrent--;
                    }
                    if (proteins == 0) {
                        proteinsCurrent--;
                    }
                    if (vitamins == 0) {
                        vitaminsCurrent--;
                    }
                }


                waterCurrent += water;

                if (vitaminsCurrent <= -6) {
                    player.sendMessage(Broadcast.getColour(FoodType.VITAMIN) + "-Vous souffrez de carence en vitamines-");
                }

                if (proteinsCurrent <= -6) {
                    player.sendMessage(Broadcast.getColour(FoodType.PROTEIN) + "-Vous souffrez de carence en protéines-");
                }

                if (carbohydratesCurrent <= -6) {
                    player.sendMessage(Broadcast.getColour(FoodType.CARBOHYDRATE) + "-Vous souffrez de carence en glucides-");
                }

                if (vitaminsCurrent >= 7) vitaminsCurrent = 7;
                if (vitaminsCurrent <= -7) vitaminsCurrent = -7;
                if (proteinsCurrent >= 7) proteinsCurrent = 7;
                if (proteinsCurrent <= -7) proteinsCurrent = -7;
                if (carbohydratesCurrent >= 7) carbohydratesCurrent = 7;
                if (carbohydratesCurrent <= -7) carbohydratesCurrent = -7;
                if (waterCurrent <= 0) waterCurrent = 0;
                if (waterCurrent >= 1800) waterCurrent = 1800;


                plugin.getValueManager().setValue(FoodType.CARBOHYDRATE, player, carbohydratesCurrent);
                plugin.getValueManager().setValue(FoodType.PROTEIN, player, proteinsCurrent);
                plugin.getValueManager().setValue(FoodType.VITAMIN, player, vitaminsCurrent);
                plugin.getValueManager().setValue(FoodType.WATER, player, waterCurrent);
            } else {
                plugin.getValueManager().resetValues(player);
            }
        }
    }
}
