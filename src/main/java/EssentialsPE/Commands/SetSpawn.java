package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class SetSpawn extends BaseCommand{

    public SetSpawn(BaseAPI api){
        super(api, "setspawn", "Change your server main spawn point");
        this.setPermission("essentials.setspawn");
    }

    public boolean execute(CommandSender sender, String alias, String args) {
        if(!this.testPermission(sender)){
            return false;
        }
        if(!(sender instanceof Player) || (args.length()) != 0){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        player.getLevel().setSpawnLocation(player.getLocation());
        player.getServer().setDefaultLevel(player.getLevel());
        player.sendMessage(TextFormat.YELLOW + "Server's spawn point changed!");
        this.getAPI().getServer().getLogger().info(TextFormat.YELLOW + "Server's spawn point set to " + TextFormat.AQUA + player.getLevel().getName() + TextFormat.YELLOW + " by " + TextFormat.GREEN + sender.getName());
        return true;
    }
}