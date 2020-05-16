package lunki.modify.foodbalance.IO;

import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ValueManager {
    public static FoodBalance plugin;

    public ValueManager(FoodBalance instance) {
        plugin = instance;
    }

    public void resetValues(Player player) {
        System.out.println("zzz");
        plugin.getValueManager().setValue(FoodType.COLD, player, 1000);
        plugin.getValueManager().setValue(FoodType.WATER, player, 700);
    }

    public void setValue(FoodType type, Player player, int value) {
        String playerUuid = player.getUniqueId().toString();
        String foodType = type.toString();


        plugin.getData().set("values." + foodType + "." + playerUuid, Integer.valueOf(value));
    }

    public int getValue(FoodType type, Player player) {
        String playerUuid = player.getUniqueId().toString();
        String foodType = type.toString();


        if (plugin.getData().isSet("values." + foodType + "." + playerUuid)) {
            return plugin.getData().getInt("values." + foodType + "." + playerUuid);
        }
        if (type == FoodType.WATER) {
            plugin.getData().set("values." + foodType + "." + playerUuid, Integer.valueOf(1800));
            return 1800;
        }
        if (type == FoodType.COLD) {
            plugin.getData().set("values." + foodType + "." + playerUuid, Integer.valueOf(1000));
            return 1000;
        }
        plugin.getData().set("values." + foodType + "." + playerUuid, Integer.valueOf(0));
        return 0;
    }


    public boolean addtoWaterBPList(Player player) {
        List<String> playerlist = plugin.getData().getStringList("waterBP");
        String a = player.getUniqueId().toString();
        if (!playerlist.contains(player.getUniqueId().toString())) {
            playerlist.add(player.getUniqueId().toString());
            plugin.getData().set("waterBP", playerlist);
            plugin.saveData();
            return true;
        }
        return false;

    }

    public boolean removefromWaterBPList(Player player) {
        List<String> playerlist = plugin.getData().getStringList("waterBP");
        if (playerlist == null) playerlist = new ArrayList<>();
        if (playerlist.contains(player.getUniqueId().toString())) {
            playerlist.remove(player.getUniqueId().toString());
            plugin.getData().set("waterBP", playerlist);
            plugin.saveData();
            return true;
        }
        return false;
    }

    public boolean addtoNutrientBPList(Player player) {
        List<String> playerlist = plugin.getData().getStringList("nutrientBP");
        if (playerlist == null) playerlist = new ArrayList<>();
        if (!playerlist.contains(player.getUniqueId().toString())) {
            playerlist.add(player.getUniqueId().toString());
            plugin.getData().set("nutrientBP", playerlist);
            plugin.saveData();
            return true;
        }
        return false;

    }

    public boolean removefromNutrientBPList(Player player) {
        List<String> playerlist = plugin.getData().getStringList("nutrientBP");
        if (playerlist == null) playerlist = new ArrayList<>();
        if (playerlist.contains(player.getUniqueId().toString())) {
            playerlist.remove(player.getUniqueId().toString());
            plugin.getData().set("nutrientBP", playerlist);
            plugin.saveData();
            return true;
        }
        return false;
    }
}
