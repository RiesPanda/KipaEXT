package tv.twitch.riespanda.kipaext;

import org.bukkit.plugin.java.JavaPlugin;
import tv.twitch.riespanda.kipaext.events.OpenGUI;
import tv.twitch.riespanda.kipaext.events.onClick;

public final class KipaEXT extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("KipaEXT by RiesPanda has been enabled!");
        getServer().getPluginManager().registerEvents(new OpenGUI(), this);
    }
}
