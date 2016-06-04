package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.command.CommandSender;

abstract class Ping extends BaseCommand{
	
    public Ping(BaseAPI api){
        super(api, "ping", "Pong!");
        this.setPermission("essentials.ping");
    }

    public boolean execute(CommandSender sender, String alias, String[] args){
        if(!this.testPermission(sender)){
            return false;
        }
        sender.sendMessage("Pong!");
        return true;
    }
}