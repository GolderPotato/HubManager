package fr.golderpotato.hubmanager.bungee.event.events;

import fr.golderpotato.waitingqueue.bungee.BungeePlugin;
import fr.golderpotato.waitingqueue.server.Server;
import fr.golderpotato.waitingqueue.server.ServerState;
import fr.golderpotato.waitingqueue.server.ServerStatus;
import fr.golderpotato.waitingqueue.server.ServerType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

/**
 * Created by Eliaz on 19/06/2017.
 */
public class PostLogin implements Listener{

    @EventHandler
    public void onLog(PostLoginEvent event){
        final Server base = new Server(ServerType.HUB);
        Server optimal = base;
        for(Server servers : BungeePlugin.getInstance().getServers().values()){
            if(!(servers.getServerType() == ServerType.HUB))continue;
            if(!(servers.getServerState().equals(ServerState.OPEN)))continue;
            if(!(servers.getServerStatus().equals(ServerStatus.ONLINE)))continue;

            if(servers.getOnline() == 0){
                optimal = servers;
                break;
            }

            if(servers.getOnline() > optimal.getOnline()){
                optimal = servers;
                continue;
            }else{
                break;
            }
        }
        ServerInfo optimalInfo = ProxyServer.getInstance().getServerInfo(optimal.getName());
        if(optimal != base){
            if(!optimalInfo.equals(event.getPlayer().getServer().getInfo())){
                event.getPlayer().connect(optimalInfo);
            }
        }else{
            event.getPlayer().disconnect(new TextComponent("§cAucun hub n'a été trouvé! Veuillez rééssayer plus tard"));
        }
    }

}
