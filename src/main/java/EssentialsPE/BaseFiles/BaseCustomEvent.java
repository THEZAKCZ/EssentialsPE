package EssentialsPE.BaseFiles;

import EssentialsPE.Loader;
import cn.nukkit.event.plugin.PluginEvent;

public abstract class BaseCustomEvent extends PluginEvent{

    private BaseAPI api;

    public BaseCustomEvent(BaseAPI api){
        super(api.getEssentialsPEPlugin());
        this.api = api;
    }

    public final Loader getPlugin(){
        return getAPI().getEssentialsPEPlugin();
    }

    public final BaseAPI getAPI(){
        return api;
    }
}