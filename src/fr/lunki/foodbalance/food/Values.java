package lunki.modify.foodbalance.food;

import java.util.HashMap;
import java.util.List;
import lunki.modify.foodbalance.FoodBalance;


public class Values
{
  private HashMap<FoodType, Integer> types;
  public static FoodBalance plugin;
  
  public Values(HashMap<FoodType, Integer> foodTypes, FoodBalance instance)
  {
    types = foodTypes;
    plugin = instance;
  }
  
  public Values(List<FoodType> foodTypes, List<Integer> values, FoodBalance instance) {
    for (int i = 0; i < types.size(); i++) {
      types.put((FoodType)foodTypes.get(i), (Integer)values.get(i));
    }
    
    plugin = instance;
  }
  
  public Values(FoodBalance instance) {
    plugin = instance;
  }
  
  public HashMap<FoodType, Integer> getTypes() {
    return types;
  }
}
