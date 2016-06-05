package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

abstract class Suicide extends BaseCommand{
	
    public Suicide(BaseAPI api){
        super(api, "suicide", "Sebevrazda");
        this.setPermission("essentials.suicide");
    }

    public boolean execute(CommandSender sender, String alias, String[] args){
        if(!this.testPermission(sender)){
            return false;
        }
        if(!(sender instanceof Player || (args.length) != 0)){
            this.sendUsage(sender, alias);
            return false;
        }

        Player player = (Player)sender;
        player.setHealth(0);
        sender.sendMessage("Ouch. To muselo bolet!");
        return true;
    }
} 
