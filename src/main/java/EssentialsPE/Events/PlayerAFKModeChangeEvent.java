package EssentialsPE.Events;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCustomEvent;
import cn.nukkit.event.Cancellable;
import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;

public class PlayerAFKModeChangeEvent extends BaseCustomEvent implements Cancellable{
    private static final HandlerList handlers = new HandlerList();


    protected Player player;

    protected boolean isAFK;

    protected boolean mode;

    protected boolean broadcast;

    public PlayerAFKModeChangeEvent(BaseAPI api, Player player, boolean mode, boolean broadcast){
        super(api);
        this.player = player;
        this.isAFK = api.isAFK(player);
        this.mode = mode;
        this.broadcast = broadcast;
    }

    /**
     * Return the player to be used
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Tell if the player is already AFK or not
     */
    public boolean isAFK(){
        return isAFK;
    }

    /**
     * Tell the mode will to be set
     */
    public boolean getAFKMode(){
        return mode;
    }

    /**
     * Change the mode to be set
     * false = Player will not be AFK
     * true = Player will be AFK
     */
    public void setAFKMode(boolean mode){
        mode = mode;
    }

    /**
     * Tell if the AFK status will be broadcast
     */
    public boolean getBroadcast(){
        return broadcast;
    }

    /**
     * Specify if the AFK status will be broadcast
     */
    public void setBroadcast(boolean mode){
        broadcast = mode;
    }
} 