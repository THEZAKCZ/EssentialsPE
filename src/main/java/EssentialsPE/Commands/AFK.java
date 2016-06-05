package EssentialsPE.Commands;

import java.sql.Array;
import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class AFK extends BaseCommand{

    public AFK(BaseAPI $api){
        super($api, "afk", "Toggle the \"Away From the Keyboard\" status", "[player]");
        this.setPermission("essentials.afk.use");
    }

    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!this.testPermission(sender)){
            return false;
        }
        if(!(args.length == 1) && !(sender instanceof Player)){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        if(args.length == 1){
            if(!sender.hasPermission("essentials.afk.other")){
                sender.sendMessage(TextFormat.RED + this.getPermissionMessage());
                return false;
            }
    		if((args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
                sender.sendMessage(TextFormat.RED + "[Chyba] Hrac neni online");
                return false;
    		} else if(args.length == 1) {
    			player = sender.getServer().getPlayer(args[0]);
    		}
        }
        
        this.getAPI().switchAFKMode(player, true);
        return true;
        }
    } 
