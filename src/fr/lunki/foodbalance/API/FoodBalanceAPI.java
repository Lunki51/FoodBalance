package lunki.modify.foodbalance.API;

import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.IO.ValueManager;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.entity.Player;

public class FoodBalanceAPI {
    public static FoodBalance plugin;

    public FoodBalanceAPI(FoodBalance instance) {
        plugin = instance;
    }

    public static ValueManager getValueManager() {
        return plugin.getValueManager();
    }

    public static void eat(Player player, int carbohydrates, int proteins, int vitamins, int water, int cold) {
        int ch = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player) + carbohydrates;
        int pt = plugin.getValueManager().getValue(FoodType.PROTEIN, player) + proteins;
        int vt = plugin.getValueManager().getValue(FoodType.VITAMIN, player) + vitamins;
        int hy = plugin.getValueManager().getValue(FoodType.WATER, player) + water;
        int co = plugin.getValueManager().getValue(FoodType.COLD, player) + cold;


        ch = ch < -7 ? -7 : ch > 7 ? 7 : ch;
        pt = pt < -7 ? -7 : pt > 7 ? 7 : pt;
        vt = vt < -7 ? -7 : vt > 7 ? 7 : vt;
        hy = hy < 0 ? 0 : hy > 1800 ? 1800 : hy;
        co = co < 0 ? 0 : co > 1000 ? 1000 : co;

        plugin.getValueManager().setValue(FoodType.CARBOHYDRATE, player, ch);
        plugin.getValueManager().setValue(FoodType.PROTEIN, player, pt);
        plugin.getValueManager().setValue(FoodType.VITAMIN, player, vt);
        plugin.getValueManager().setValue(FoodType.WATER, player, hy);
        plugin.getValueManager().setValue(FoodType.COLD, player, co);
    }
}
