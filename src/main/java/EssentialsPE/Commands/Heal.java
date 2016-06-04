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
        if((!(args.length == 1)) && !(sender instanceof Player) || (args.length) > 1) {
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
		if(!(args.length == 1 && !(sender.getServer().getOfflinePlayer(args[0]).isOnline()))) {
            sender.sendMessage(TextFormat.RED + "[Error] Player not found");
            return false;
        }
        player.setHealth(20);
        player.getLevel().addParticle(new HeartParticle(player.add(0, 2, 0)));
        player.sendMessage(TextFormat.GREEN + "You have been healed!");
        if(player == sender){
            sender.sendMessage(TextFormat.GREEN + player.getDisplayName() + " has been healed!");
        }
        return true;
    }
}