package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Top extends BaseCommand{

    public Top(BaseAPI api){
        super(api, "top", "Teleportuje na nejvissi block nad tebou", null);
        this.setPermission("essentials.top");
    }

    public boolean execute(CommandSender sender, String alias, String[] args){
        if(!this.testPermission(sender)){
            return false;
        }
        if(!(sender instanceof Player || (args.length != 0))){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        sender.sendMessage(TextFormat.YELLOW + "Teleportuji...");
        player.teleport(player.add(0, player.getLevel().getHighestBlockAt(player.getFloorX(), player.getFloorZ()) + 1));
        return true;
    }
}
