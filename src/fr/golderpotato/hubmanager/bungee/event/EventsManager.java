package fr.golderpotato.hubmanager.bungee.event;

import fr.golderpotato.hubmanager.bungee.event.events.PostLogin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Eliaz on 19/06/2017.
 */
public class EventsManager {

    private Plugin plugin;


    public void registerEvents(Plugin plugin){
        register(new PostLogin());
    }

    private void register(Listener listener){
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, listener);
    }

}
