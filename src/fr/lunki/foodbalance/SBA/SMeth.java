package lunki.modify.foodbalance.SBA;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class SMeth {
    public SMeth() {
    }

    public static Location toLocation(String locationString) {
        String[] s = locationString.split("%");

        Location loc = new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]),
                Double.parseDouble(s[3]));
        if (s.length > 4) {
            loc.setPitch(Float.parseFloat(s[4]));
            loc.setYaw(Float.parseFloat(s[5]));
        }

        return loc;
    }


    public static String toLocationStringSign(Location loc) {
        return loc.getWorld().getName() + "%" + loc.getX() + "%" + loc.getY() + "%" + loc.getZ();
    }


    public static String toLocationString(Location loc) {
        return
                loc.getWorld().getName() + "%" + loc.getX() + "%" + loc.getY() + "%" + loc.getZ() + "%" + loc.getPitch() + "%" + loc.getYaw();
    }

    public static String toTime(int seconds) {
        int hours = 0;
        int minutes = 0;

        if (seconds / 3600 > 0) {
            hours = seconds / 3600;
            seconds -= hours * 3600;
        }

        if (seconds / 60 > 0) {
            minutes = seconds / 60;
            seconds -= minutes * 60;
        }

        if (hours > 0) {
            return hours + ":" + (minutes < 10 ? "0" + minutes : Integer.valueOf(minutes)) + ":" + (seconds < 10 ? "0" + seconds : Integer.valueOf(seconds));
        }
        return minutes + ":" + (seconds < 10 ? "0" + seconds : Integer.valueOf(seconds));
    }

    public static String setColours(String s) {
        return s.replace("&0", ChatColor.BLACK.toString()).replace("&1", ChatColor.DARK_BLUE.toString()).replace("&2", ChatColor.DARK_GREEN.toString()).replace("&3", ChatColor.DARK_AQUA.toString()).replace("&4", ChatColor.DARK_RED.toString()).replace("&5", ChatColor.DARK_PURPLE.toString()).replace("&6", ChatColor.GOLD.toString()).replace("&7", ChatColor.GRAY.toString()).replace("&8", ChatColor.DARK_GRAY.toString()).replace("&9", ChatColor.BLUE.toString()).replace("&a", ChatColor.GREEN.toString()).replace("&b", ChatColor.AQUA.toString()).replace("&c", ChatColor.RED.toString()).replace("&d", ChatColor.LIGHT_PURPLE.toString()).replace("&e", ChatColor.YELLOW.toString()).replace("&f", ChatColor.WHITE.toString()).replace("&k", ChatColor.MAGIC.toString()).replace("&l", ChatColor.BOLD.toString()).replace("&m", ChatColor.STRIKETHROUGH.toString()).replace("&n", ChatColor.UNDERLINE.toString()).replace("&o", ChatColor.ITALIC.toString()).replace("&r", ChatColor.RESET.toString());
    }
}
