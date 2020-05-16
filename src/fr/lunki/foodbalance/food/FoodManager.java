package lunki.modify.foodbalance.food;

import lunki.modify.foodbalance.FoodBalance;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FoodManager {
    private List<Food> foods = new ArrayList();
    public static FoodBalance plugin;

    public FoodManager(FoodBalance instance) {
        plugin = instance;
    }

    public void registerFoods() {
        Food food = null;
        Material mat = null;
        Values val = null;
        HashMap<FoodType, Integer> fti = null;
        List<String> list = plugin.getConfig().getStringList("foods");

        for (String configfood : list) {
            String itemname = null;
            int comma = 0;
            for (int i = 0; i < configfood.length(); i++) {
                if (configfood.charAt(i) == ',') {
                    comma = i;
                    break;
                }
                if (itemname == null) {
                    itemname = String.valueOf(configfood.charAt(i));
                } else {
                    itemname += configfood.charAt(i);
                }
            }
            if (Material.getMaterial(itemname) != null) {
                mat = Material.getMaterial(itemname);
                fti = new HashMap<>();
                int vitamins = Character.getNumericValue(configfood.charAt(comma + 1));
                int proteins = Character.getNumericValue(configfood.charAt(comma + 3));
                int carbohydrates = Character.getNumericValue(configfood.charAt(comma + 5));
                int water = Integer.parseInt(configfood.subSequence(comma + 7, configfood.length()).toString());

                if (water != 0) {
                    fti.put(FoodType.WATER, Integer.valueOf(water));
                }
                if (carbohydrates != 0) {
                    fti.put(FoodType.CARBOHYDRATE, Integer.valueOf(carbohydrates));
                }
                if (proteins != 0) {
                    fti.put(FoodType.PROTEIN, Integer.valueOf(proteins));
                }
                if (vitamins != 0) {
                    fti.put(FoodType.VITAMIN, Integer.valueOf(vitamins));
                }
                food = new Food(mat, new Values(fti, plugin), plugin);
                foods.add(food);
            }
        }
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void reloadFoods() {
        foods.clear();
        this.registerFoods();
    }
}
