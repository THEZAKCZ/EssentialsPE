package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Nuke extends BaseCommand{

    public Nuke(BaseAPI api){
        super(api, "nuke", "Tenka padajici vrstva TNT", "[player]");
        this.setPermission("essentials.nuke.use");
    }

    public boolean execute(CommandSender sender, String alias, String[] args){
        if(!this.testPermission(sender)){
            return false;
        }
        if((!(args.length == 1) && !(sender instanceof Player)) || (args.length > 1)){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        if(args.length == 1){
            if(!sender.hasPermission("essentials.nuke.other")){
                sender.sendMessage(TextFormat.RED + this.getPermissionMessage());
                return false;
            }else if((args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
  	          sender.sendMessage(TextFormat.RED + "[Chyba] Hrac neni online");
	          return false;
			} else if(args.length == 1) {
				player = sender.getServer().getPlayer(args[0]);
			}
        }
        this.getAPI().nuke(player);
        return true;
    }
} 
