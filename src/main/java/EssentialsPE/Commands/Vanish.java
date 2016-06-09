package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Vanish extends BaseCommand{

    private Object s;

	public Vanish(BaseAPI api){
        super(api, "vanish", "Hide from other players!", "[player]");
        this.setPermission("essentials.vanish.use");
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
        if(args.length == 1){
  			if((args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
  	          sender.sendMessage(TextFormat.RED + "[Chyba] Hrac neni online");
  	          return false;
  			} else if(args.length == 1) {
  				player = sender.getServer().getPlayer(args[0]);
  			}
        }
        this.getAPI().switchVanish(player);
        player.sendMessage(TextFormat.GRAY + "You're now " + (s = this.getAPI().isVanished(player) ? "vanished" : "visible"));
        if(player != sender){
            sender.sendMessage(TextFormat.GRAY + player.getDisplayName() + " is now" + s);
        }
        return true;
    }
}
