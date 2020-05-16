package lunki.modify.foodbalance.food;

public enum FoodType
{
  CARBOHYDRATE("carbohydrate"), 
  VITAMIN("vitamin"), 
  PROTEIN("protein"), 
  WATER("water"),
  COLD("cold");
  
  private String storageName;
  
  private FoodType(String storageName) {
    this.storageName = storageName;
  }
  
  public String toString() {
    return storageName;
  }
}
