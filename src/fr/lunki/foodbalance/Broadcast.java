package lunki.modify.foodbalance;

import lunki.modify.foodbalance.SBA.SMeth;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.ChatColor;

public class Broadcast {
    public static FoodBalance plugin;
    public static final String TAG = ChatColor.GOLD + "[" + ChatColor.GREEN + "FoodBalance" + ChatColor.GOLD + "] ";

    public Broadcast(FoodBalance instance) {
        plugin = instance;
    }

    public static String getMsg(String node) {
        return SMeth.setColours(plugin.getConfig().getString("messages." + node));
    }

    public static String getMsg(Message msg) {
        try {
            return SMeth.setColours(plugin.getConfig().getString(msg.getNode()));
        } catch (Exception e) {
            System.out.println(msg.getNode());
        }
        return null;
    }

    public static String getColour(FoodType type) {
        switch (type) {
            case CARBOHYDRATE:
                return getMsg(Message.CARBOHYDRATES_COLOUR);
            case VITAMIN:
                return getMsg(Message.PROTEINS_COLOUR);
            case PROTEIN:
                return getMsg(Message.VITAMINS_COLOUR);
            case WATER:
                return getMsg(Message.WATER_COLOUR);
            case COLD:
                return getMsg(Message.COLD_COLOR);
        }
        return "";
    }
}
