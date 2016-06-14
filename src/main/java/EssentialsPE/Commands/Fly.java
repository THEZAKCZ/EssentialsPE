package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Fly extends BaseCommand{

    public Fly(BaseAPI api){
        super(api, "fly", "Umozni letat jako BATMAN!!", "[player]");
        this.setPermission("essentials.fly.use");
    }

	  public boolean execute(CommandSender sender, String alias, String[] args) {
	      if(!this.testPermission(sender)){
	          return false;
	      }
	      if((!(args.length == 0)) && !(sender instanceof Player) || (args.length) > 1) {
	    	  this.sendUsage(sender, alias);
	          return false;
	      }
	      Player player = (Player)sender;
			if((args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
	          sender.sendMessage(TextFormat.RED + "[Chyba] Hrac neni online");
	          return false;
			} else if(args.length == 1) {
				player = sender.getServer().getPlayer(args[0]);
			}
			
        this.getAPI().switchCanFly(player);
        player.sendMessage(TextFormat.YELLOW + "Mod letani " + (this.getAPI().canFly(player) ? "byl zapnut" : "byl vypnut") + "!");
        if(args.length == 1){
            player.sendMessage(TextFormat.YELLOW + "Mod letani " + (this.getAPI().canFly(player) ? "byl zapnut" : "byl vypnut") + "!");
        }
        return true;
    }
}
