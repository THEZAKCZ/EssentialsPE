package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Extinguish extends BaseCommand{

    public Extinguish(BaseAPI api){
        super(api, "extinguish", "Uhasi hrace", "[player]");
        this.setPermission("essentials.extinguish.use");
    }

    public boolean execute(CommandSender sender, String alias, String[] args){
        if(!this.testPermission(sender)){
            return false;
        }
        if((!(args.length == 0)) && !(sender instanceof Player) || (args.length > 1)){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        if(args.length == 0){
            if(!sender.hasPermission("essentials.extinguish.other")){
                sender.sendMessage(TextFormat.RED + this.getPermissionMessage());
                return false;
            } if((args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
    		          sender.sendMessage(TextFormat.RED + "[Chyba] Hrac neni online");
    		          return false;
    				} else if(args.length == 1) {
    					player = sender.getServer().getPlayer(args[0]);
    				}
    			
    			player.extinguish();
    			sender.sendMessage(TextFormat.AQUA + "Byl jsi uhasen!");
    		      if(args.length == 1){
    		          sender.sendMessage(TextFormat.GREEN + player.getDisplayName() + " byl uhasen!");
    	    		  player.sendMessage(TextFormat.AQUA + "Byl jsi uhasen!");
    		}
        }
		return true;
    }
}
