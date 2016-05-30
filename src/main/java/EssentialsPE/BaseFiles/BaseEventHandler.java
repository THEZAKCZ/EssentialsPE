package EssentialsPE.BaseFiles;

import EssentialsPE.Loader;
import cn.nukkit.event.Listener;

abstract class BaseEventHandler implements Listener{
	
	private BaseAPI api;

    public BaseEventHandler (BaseAPI api){
        this.api = api;
    }

    public final Loader getPlugin(){
        return getAPI().getEssentialsPEPlugin();
    }

    public BaseAPI getAPI() {
        return this.api;
    }
}
