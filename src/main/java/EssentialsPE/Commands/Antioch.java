package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Antioch extends BaseCommand{

    public Antioch(BaseAPI api){
        super(api, "antioch", "Holy hand grenade", null);
        this.setPermission("essentials.antioch");
    }

    public boolean execute(CommandSender sender, String alias, String args) {
        if(!this.testPermission(sender)){
            return false;
        }
        if(!(sender instanceof Player) || (args.length()) == 0){
            this.sendUsage(sender, alias);
            return false;
        }
        Player player = (Player)sender;
        if(!this.getAPI().antioch(player)){
            sender.sendMessage(TextFormat.RED + "[Error] Cannot throw the grenade, there isn't a near valid block");
            return false;
        }
        sender.sendMessage(TextFormat.GREEN + "Grenade threw!");
        return true;
    }
}