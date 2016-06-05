package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Spawn extends BaseCommand{
	
    public Spawn(BaseAPI api){
        super(api, "spawn", "Teleport na hlavni spawn", "[player]");
        this.setPermission("essentials.spawn.use");
    }

    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!this.testPermission(sender)){
            return false;
        }
        if((args.length == 0) && !(sender instanceof Player) || (args.length > 1)){
        	this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        if(args.length == 1){
            if(!sender.hasPermission("essentials.spawn.other")){
                sender.sendMessage(TextFormat.RED + "[Chyba] Nemuzes teleportovat ostatni hrace na spawn");
                return false;
            } if((args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
                sender.sendMessage(TextFormat.RED + "[Chyba] Hrac neni online");
                return false;
    		} else if(args.length == 1) {
  			player = sender.getServer().getPlayer(args[0]);
  		}
        }
        player.teleport(player.getLevel().getSpawnLocation());
        player.sendMessage(TextFormat.GREEN + "Teleportuji...");
        return true;
    }
  }
