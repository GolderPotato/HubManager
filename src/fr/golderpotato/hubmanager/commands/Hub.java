package fr.golderpotato.hubmanager.commands;

import fr.golderpotato.waitingqueue.bungee.BungeePlugin;
import fr.golderpotato.waitingqueue.server.Server;
import fr.golderpotato.waitingqueue.server.ServerState;
import fr.golderpotato.waitingqueue.server.ServerStatus;
import fr.golderpotato.waitingqueue.server.ServerType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

/**
 * Created by Eliaz on 19/06/2017.
 */
public class Hub extends Command{

    public Hub(){
        super("hub");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(!(commandSender instanceof ProxiedPlayer))return;
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        switch (args.length){
            case 1:
                int hubID;
                try{
                    hubID = Integer.valueOf(args[0]);
                } catch (Exception e){
                    player.sendMessage("Veuillez entrer un ID de hub!");
                    return;
                }
                for(Server server : getHUBs()){
                    if(Integer.valueOf(server.getName().replaceAll("hub", "")) == hubID){
                        player.connect(ProxyServer.getInstance().getServerInfo(server.getName()));
                        return;
                    }
                }
                break;
            case 0:
                if(getHUBs().size() > 0){
                    player.connect(ProxyServer.getInstance().getServerInfo(getHUBs().get(0).getName()));
                }
                break;
            default:
                player.sendMessage("Commands Invalide!");
                break;
        }
    }

    public ArrayList<Server> getHUBs(){
        ArrayList<Server> toReturn = new ArrayList<>();
        for(Server server : BungeePlugin.getInstance().getServers().values()){
            if(server.getServerType().equals(ServerType.HUB) && !server.getServerStatus().equals(ServerStatus.OFFLINE) && server.getServerState().equals(ServerState.OPEN) && server.getOnline() < server.getSlots()){
                toReturn.add(server);
            }
        }
        return toReturn;
    }
}
