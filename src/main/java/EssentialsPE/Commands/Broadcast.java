package EssentialsPE.Commands;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

abstract class Broadcast extends BaseCommand{
	
    public Broadcast(BaseAPI api){
        super(api, "broadcast", "Broadcast a message.", "<message>");
        this.setPermission("essentials.broadcast");
    }

    public boolean execute(CommandSender sender, String alias, String args) {
        if(!this.testPermission(sender)){
            return false;
        }
        if(args.length() < 1){
            this.sendUsage(sender, alias);
            return false;
        }
        sender.getServer().broadcastMessage(TextFormat.LIGHT_PURPLE + "[Broadcast] " + TextFormat.RESET + " " + args);
        return true;
    }
}