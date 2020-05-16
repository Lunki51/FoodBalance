package lunki.modify.foodbalance.command;

import lunki.modify.foodbalance.Broadcast;
import lunki.modify.foodbalance.FoodBalance;
import lunki.modify.foodbalance.Message;
import lunki.modify.foodbalance.food.FoodType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHealth implements CommandExecutor {
    public static FoodBalance plugin;

    public CmdHealth(FoodBalance instance) {
        plugin = instance;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;

            int carbohydrates = plugin.getValueManager().getValue(FoodType.CARBOHYDRATE, player);
            int proteins = plugin.getValueManager().getValue(FoodType.PROTEIN, player);
            int vitamins = plugin.getValueManager().getValue(FoodType.VITAMIN, player);
            int water = plugin.getValueManager().getValue(FoodType.WATER, player);
            int cold = plugin.getValueManager().getValue(FoodType.COLD, player);


            carbohydrates = carbohydrates > 7 ? 7 : carbohydrates;
            carbohydrates = carbohydrates < -7 ? -7 : carbohydrates;
            proteins = proteins > 7 ? 7 : proteins;
            proteins = proteins < -7 ? -7 : proteins;
            vitamins = vitamins > 7 ? 7 : vitamins;
            vitamins = vitamins < -7 ? -7 : vitamins;
            water = water < 0 ? 0 : water;
            cold = cold < 0 ? 0 : cold;


            String msg = Broadcast.getColour(FoodType.CARBOHYDRATE) + "[";
            for (int c = -7; c <= 7; c++) {
                if (carbohydrates >= c) {
                    msg = msg + "#";
                } else {
                    msg = msg + "-";
                }
            }
            msg = msg + "] " + Broadcast.getMsg(Message.CARBOHYDRATE);
            player.sendMessage(msg);


            msg = Broadcast.getColour(FoodType.PROTEIN) + "[";
            for (int p = -7; p <= 7; p++) {
                if (proteins >= p) {
                    msg = msg + "#";
                } else {
                    msg = msg + "-";
                }
            }
            msg = msg + "] " + Broadcast.getMsg(Message.PROTEIN);
            player.sendMessage(msg);


            msg = Broadcast.getColour(FoodType.VITAMIN) + "[";
            for (int v = -7; v <= 7; v++) {
                if (vitamins >= v) {
                    msg = msg + "#";
                } else {
                    msg = msg + "-";
                }
            }
            msg = msg + "] " + Broadcast.getMsg(Message.VITAMIN);
            player.sendMessage(msg);


            int times = (int) Math.ceil(water / 120.0D);
            msg = Broadcast.getColour(FoodType.WATER) + "[";
            for (int w = 0; w < times; w++) {
                msg = msg + "#";
            }
            for (int w = times; w < 15; w++) {
                msg = msg + "-";
            }
            msg = msg + "] " + Broadcast.getMsg(Message.WATER);
            player.sendMessage(msg);
        } else {
            sender.sendMessage(Broadcast.getMsg(Message.ONLY_IN_GAME));
        }

        return false;
    }
}
