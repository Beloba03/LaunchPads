package ca.unitedsb.Launchpads;

import org.bukkit.plugin.java.JavaPlugin;

public class LaunchpadMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("LaunchPadPlugin has been enabled.");
        getServer().getPluginManager().registerEvents(new LaunchpadListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("LaunchPadPlugin has been disabled.");
    }
}
