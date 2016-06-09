package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;

abstract class Compass extends BaseCommand{

    public Compass(BaseAPI api){
        super(api, "compass", "Zobrazi svetovou stranu, na kterou se koukas", null);
        this.setPermission("essentials.compass");
    }

    public boolean execute(CommandSender sender, String alias, String[] args, String direction){
        if(!this.testPermission(sender)){
            return false;
        }
        if(!(sender instanceof Player || (args.length != 0)){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
		switch(player.getDirection()){
            case 0:
                direction = "jih";
                break;
            case 1:
                direction = "zapad";
                break;
            case 2:
                direction = "sever";
                break;
            case 3:
                direction = "vychod";
                break;
            default:
                sender.sendMessage(TextFormat.RED + "Oops, nastal tady nejaky problem pri urcovani tve pozice");
                return false;
                break;
        }
        sender.sendMessage(TextFormat.AQUA + "Koukas se na " + TextFormat.YELLOW + direction);
        return true;
    }
}
