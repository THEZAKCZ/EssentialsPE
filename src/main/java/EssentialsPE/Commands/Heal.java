package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.particle.HeartParticle;
import cn.nukkit.utils.TextFormat;

abstract class Heal extends BaseCommand{

	public Heal(BaseAPI api){
        super(api, "heal", "Heal yourself or other player", "[player]");
        this.setPermission("essentials.heal.use");
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
			
	      player.setHealth(20);
	      player.getLevel().addParticle(new HeartParticle(player.add(0, 2, 0)));
	      player.sendMessage(TextFormat.GREEN + "Byl jsi vylecen!");
	      if(args.length == 1){
	          sender.sendMessage(TextFormat.GREEN + player.getDisplayName() + " byl vylecen!");
	      }
	      return true;
			}
	  	}
