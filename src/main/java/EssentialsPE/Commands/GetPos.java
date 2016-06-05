package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class GetPos extends BaseCommand{

    public GetPos(BaseAPI api){
        super(api, "getpos", "Podivat se na pozici vasi , nebo ostatnich hracu", "[player]", "coords");
        this.setPermission("essentials.getpos.use");
    }

    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!this.testPermission(sender)){
            return false;
        }
        if((args.length == 0) && !(sender instanceof Player) || (args.length > 1)){
            return false;
        }
        Player player = (Player)sender;
        if(args.length == 1){
            if(!sender.hasPermission("essentials.getpos.other")){
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
        if(args.length == 0 ) {
        sender.sendMessage(TextFormat.GREEN + "Jsi ve svete: " + TextFormat.AQUA + player.getLevel().getName() + "\n" + TextFormat.GREEN + "Souradnice: " + TextFormat.YELLOW + "X: " + TextFormat.AQUA + player.getFloorX() + TextFormat.GREEN + ", " + TextFormat.YELLOW + "Y: " + TextFormat.AQUA + player.getFloorY() + TextFormat.GREEN + ", " + TextFormat.YELLOW + "Z: " + TextFormat.AQUA + player.getFloorZ());
        return true;
        	} else if(args.length == 1 ) {
              sender.sendMessage(TextFormat.GREEN + "Hrac " + TextFormat.AQUA + player.getName() + TextFormat.GREEN + " je ve svete: " + TextFormat.AQUA + player.getLevel().getName() + "\n" + TextFormat.GREEN + "Souradnice: " + TextFormat.YELLOW + "X: " + TextFormat.AQUA + player.getFloorX() + TextFormat.GREEN + ", " + TextFormat.YELLOW + "Y: " + TextFormat.AQUA + player.getFloorY() + TextFormat.GREEN + ", " + TextFormat.YELLOW + "Z: " + TextFormat.AQUA + player.getFloorZ());
        }
  	return true;
    }
  }
