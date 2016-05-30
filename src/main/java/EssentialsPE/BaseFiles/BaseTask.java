package EssentialsPE.BaseFiles;

import EssentialsPE.Loader;

abstract class BaseTask {

				private BaseAPI api;

			    public BaseTask (BaseAPI api) {
			    	getAPI().getEssentialsPEPlugin();
			        this.api = api;
			    }

			    public final Loader getPlugin() {
			        return getAPI().getEssentialsPEPlugin();
			    }

			    public final BaseAPI getAPI() {
			        return this.api;
			    }
			}
