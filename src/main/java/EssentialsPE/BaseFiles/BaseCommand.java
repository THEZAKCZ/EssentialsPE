package EssentialsPE.BaseFiles;

import EssentialsPE.Loader;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginIdentifiableCommand;
import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

public abstract class BaseCommand extends Command implements PluginIdentifiableCommand {

    private BaseAPI api;

    private String consoleUsageMessage;

    public BaseCommand(BaseAPI api, String name){
        this(api, name, "", null, null, new String[0]);
    }

    public BaseCommand(BaseAPI api, String name, String description){
        this(api, name, description, null, null, new String[0]);
    }

    public BaseCommand(BaseAPI api, String name, String description, String usageMessage){
        this(api, name, description, usageMessage, null, new String[0]);
    }

    public BaseCommand(BaseAPI api, String name, String description, String usageMessage, String consoleUsageMessage){
        this(api, name, description, usageMessage, consoleUsageMessage, new String[0]);
    }

    public BaseCommand(BaseAPI api, String name, String description, String usageMessage, String consoleUsageMessage, String[] aliases){
        super(name, description, usageMessage, aliases);
        this.api = api;
        this.consoleUsageMessage = consoleUsageMessage;
    }

    public final Loader getPlugin(){
        return this.getAPI().getEssentialsPEPlugin();
    }

    public final BaseAPI getAPI(){
        return this.api;
    }

    public String getUsage(){
        return "/" + super.getName() +" " + super.getUsage();
    }

    public String getConsoleUsage(){
        return this.consoleUsageMessage;
    }

    public void sendUsage(CommandSender sender, String alias){
        String message = TextFormat.RED + "Usage: " + TextFormat.GRAY + "/"+alias+" ";
        if(!(sender instanceof Player)){
            if(consoleUsageMessage != null){
                message += consoleUsageMessage;
            }else{
                message += super.getUsage().replaceAll("[player]", "<player>");
            }
        }else{
            message += super.getUsage();
        }
        sender.sendMessage(message);
    }
}
