package fr.golderpotato.hubmanager.bungee;


import fr.golderpotato.hubmanager.bungee.event.EventsManager;
import fr.golderpotato.hubmanager.commands.Hub;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Eliaz on 19/06/2017.
 */
public class BungeePlugin extends Plugin{

    public static BungeePlugin INSTANCE;

    @Override
    public void onEnable(){
        INSTANCE = this;
        ProxyServer.getInstance().getPluginManager().registerCommand(INSTANCE, new Hub());
        new EventsManager().registerEvents(INSTANCE);
    }



}
