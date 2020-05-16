package lunki.modify.foodbalance.food;

import lunki.modify.foodbalance.FoodBalance;
import org.bukkit.Material;


public class Food
{
  private Material item;
  private Values values;
  public static FoodBalance plugin;
  
  public Food(Material item, Values values, FoodBalance instance)
  {
    this.item = item;
    this.values = values;
    plugin = instance;
  }
  
  public void setValues(Values values) {
    this.values = values;
  }
  
  public Values getValues() {
    return values;
  }
  
  public void setItem(Material item) {
    this.item = item;
  }
  
  public Material getItem() {
    return item;
  }
}
