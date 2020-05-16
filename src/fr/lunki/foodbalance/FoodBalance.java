package lunki.modify.foodbalance;

import lunki.modify.foodbalance.API.FoodBalanceAPI;
import lunki.modify.foodbalance.IO.ValueManager;
import lunki.modify.foodbalance.command.CmdFoodBalance;
import lunki.modify.foodbalance.command.CmdHealth;
import lunki.modify.foodbalance.command.TabCompleter;
import lunki.modify.foodbalance.food.FoodManager;
import lunki.modify.foodbalance.listener.*;
import lunki.modify.foodbalance.runnable.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.Reader;
import java.sql.Connection;
import java.util.logging.Level;

public class FoodBalance extends JavaPlugin {
    private Connection connection = null;
    private ValueManager valueManager = new ValueManager(this);
    private FoodManager foodManager = new FoodManager(this);
    private EatListener eatListener = new EatListener(this);
    private JoinListener joinListener = new JoinListener(this);
    private DeathListener deathListener = new DeathListener(this);
    private RegenListener regenListener = new RegenListener(this);
    private FightListener fightListener = new FightListener(this);
    private CakeListener cakeListener = new CakeListener(this);
    private Broadcast broadcast = new Broadcast(this);
    private DeathMessages deathMessages = new DeathMessages(this);
    public FoodBalanceAPI api = new FoodBalanceAPI(this);

    public FoodBalance() {
    }

    public void onEnable() {
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
            try {
                getConfig().options().copyDefaults(true);
                saveConfig();
                getLogger().info("Generated config.yml succesfully!");
            } catch (Exception e) {
                getLogger().info("Failed to generate config.yml!");
            }
        }

        File df = new File(getDataFolder() + File.separator + "data.yml");
        if (!df.exists()) {
            try {
                reloadData();
                saveData();
                getLogger().info("Generated data.yml succesfully!");
            } catch (Exception e) {
                getLogger().info("Failed to generate data.yml!");
            }
        }


        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(eatListener, this);
        pluginManager.registerEvents(joinListener, this);
        pluginManager.registerEvents(deathListener, this);
        pluginManager.registerEvents(regenListener, this);
        pluginManager.registerEvents(fightListener, this);
        pluginManager.registerEvents(cakeListener, this);
        this.getCommand("foodbalance").setTabCompleter(new TabCompleter());
        this.getCommand("foodbalance").setExecutor(new CmdFoodBalance(this));
        this.getCommand("health").setExecutor(new CmdHealth(this));


        getServer().getScheduler().scheduleSyncRepeatingTask(this, new WaterRunnable(this), 20L, 20L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new CarbohydrateRunnable(this), 20L, 20L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new ProteinRunnable(this), 20L, 20L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new VitaminRunnable(this), 20L, 20L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new ColdRunnable(this), 20L, 20L);


        foodManager.registerFoods();
        getLogger().info("FoodBalance v" + getDescription().getVersion() + " has been enabled!");
    }

    public void onDisable() {
        saveData();

        getLogger().info("FoodBalance v" + getDescription().getVersion() + " has been disabled!");
    }


    private FileConfiguration data = null;
    private File dataFile = null;


    public void reloadData() {
        //foodManager.reloadFoods();
        if (dataFile == null) {
            dataFile = new File(getDataFolder(), "data.yml");
        }
        data = YamlConfiguration.loadConfiguration(dataFile);

        //InputStream defStream = getResource("data.yml");
        Reader defStream = getTextResource("data.yml");

        if (defStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defStream);
            data.setDefaults(defConfig);
        }
    }


    public FileConfiguration getData() {
        if (data == null) {
            reloadData();
        }
        return data;
    }


    public void saveData() {
        if ((data == null) || (dataFile == null)) {
            return;
        }
        try {
            getData().save(dataFile);
        } catch (Exception ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + dataFile, ex);
        }
    }

    public DeathMessages getDeathMessages() {
        return deathMessages;
    }

    public Broadcast getBroadcast() {
        return broadcast;
    }

    public FoodManager getFoodManager() {
        return foodManager;
    }

    public ValueManager getValueManager() {
        return valueManager;
    }

    public Connection getConnection() {
        return connection;
    }
}
