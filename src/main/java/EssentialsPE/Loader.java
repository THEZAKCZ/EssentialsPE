package EssentialsPE;

import EssentialsPE.BaseFiles.BaseAPI;
import EssentialsPE.BaseFiles.BaseCommand;
import EssentialsPE.BaseFiles.BaseSession;
import EssentialsPE.Commands.AFK;
import EssentialsPE.Commands.Antioch;
import EssentialsPE.Commands.Back;
import EssentialsPE.Commands.BreakCommand;
import EssentialsPE.Commands.Broadcast;
import EssentialsPE.Commands.Burn;
import EssentialsPE.Commands.ClearInventory;
import EssentialsPE.Commands.Compass;
import EssentialsPE.Commands.Condense;
import EssentialsPE.Commands.Depth;
import EssentialsPE.Commands.Economy.Balance;
import EssentialsPE.Commands.Economy.Eco;
import EssentialsPE.Commands.Economy.Pay;
import EssentialsPE.Commands.Economy.Sell;
import EssentialsPE.Commands.Economy.SetWorth;
import EssentialsPE.Commands.Economy.Worth;
import EssentialsPE.Commands.EssentialsPE;
import EssentialsPE.Commands.Extinguish;
import EssentialsPE.Commands.Fly;
import EssentialsPE.Commands.GetPos;
import EssentialsPE.Commands.God;
import EssentialsPE.Commands.Heal;
import EssentialsPE.Commands.Home.DelHome;
import EssentialsPE.Commands.Home.Home;
import EssentialsPE.Commands.Home.SetHome;
import EssentialsPE.Commands.ItemCommand;
import EssentialsPE.Commands.ItemDB;
import EssentialsPE.Commands.Jump;
import EssentialsPE.Commands.KickAll;
import EssentialsPE.Commands.Kit;
import EssentialsPE.Commands.Lightning;
import EssentialsPE.Commands.More;
import EssentialsPE.Commands.Mute;
import EssentialsPE.Commands.Near;
import EssentialsPE.Commands.Nick;
import EssentialsPE.Commands.Nuke;
import EssentialsPE.Commands.Override.Gamemode;
import EssentialsPE.Commands.Override.Kill;
import EssentialsPE.Commands.Override.Msg;
import EssentialsPE.Commands.Ping;
import EssentialsPE.Commands.PowerTool.PowerTool;
import EssentialsPE.Commands.PowerTool.PowerToolToggle;
import EssentialsPE.Commands.PTime;
import EssentialsPE.Commands.PvP;
import EssentialsPE.Commands.RealName;
import EssentialsPE.Commands.Repair;
import EssentialsPE.Commands.Reply;
import EssentialsPE.Commands.Seen;
import EssentialsPE.Commands.SetSpawn;
import EssentialsPE.Commands.Spawn;
import EssentialsPE.Commands.Sudo;
import EssentialsPE.Commands.Suicide;
import EssentialsPE.Commands.Teleport.TPA;
import EssentialsPE.Commands.Teleport.TPAccept;
import EssentialsPE.Commands.Teleport.TPAHere;
import EssentialsPE.Commands.Teleport.TPAll;
import EssentialsPE.Commands.Teleport.TPDeny;
import EssentialsPE.Commands.Teleport.TPHere;
import EssentialsPE.Commands.TempBan;
import EssentialsPE.Commands.Top;
import EssentialsPE.Commands.Unlimited;
import EssentialsPE.Commands.Vanish;
import EssentialsPE.Commands.Warp.DelWarp;
import EssentialsPE.Commands.Warp.Setwarp;
import EssentialsPE.Commands.Warp.Warp;
import EssentialsPE.Commands.World;
import EssentialsPE.EventHandlers.OtherEvents;
import EssentialsPE.EventHandlers.PlayerEvents;
import EssentialsPE.EventHandlers.SignEvents;
import EssentialsPE.Events.CreateAPIEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.HashMap;

public class Loader extends PluginBase{
    /** @var BaseAPI */
    private BaseAPI api;

    public void onEnable(){
        BaseSession.configDefaults.put("isAFK", false);
        BaseSession.configDefaults.put("isGod", false);
        BaseSession.configDefaults.put("homes", new HashMap<String, >());
        BaseSession.configDefaults.put("isMuted", false);
        BaseSession.configDefaults.put("isAFK", false);
        BaseSession.configDefaults.put("isAFK", false);
        BaseSession.configDefaults.put("isAFK", false);
        BaseSession.configDefaults.put("isAFK", false);
        BaseSession.configDefaults.put("isAFK", false);
        "isAFK" => false,
                "isGod" => false,
                "homes" => [],
        "isMuted" => false,
                "mutedUntil" => null,
                "nick" => null,
                "ptCommands" => false,
                "ptChatMacros" => false,
                "isPvPEnabled" => true,
                "isUnlimitedEnabled" => false,
                "isVanished" => false

        // Before anything else...
        $this->checkConfig();

        // Custom API Setup :3
        $this->getServer()->getPluginManager()->callEvent($ev = new CreateAPIEvent($this, BaseAPI::class));
        $class = $ev->getClass();
        $this->api = new $class($this);

        // Other startup code...
        if(!is_dir($this->getDataFolder())){
            mkdir($this->getDataFolder());
        }
	    $this->getLogger()->info(TextFormat::YELLOW . "Loading...");
        $this->registerEvents();
        $this->registerCommands();
        if(count($p = $this->getServer()->getOnlinePlayers()) > 0){
            $this->getAPI()->createSession($p);
        }
        if($this->getAPI()->isUpdaterEnabled()){
            $this->getAPI()->fetchEssentialsPEUpdate(false);
        }
        $this->getAPI()->scheduleAutoAFKSetter();
    }

    public function onDisable(){
        if(count($l = $this->getServer()->getOnlinePlayers()) > 0){
            $this->getAPI()->removeSession($l);
        }
        $this->getAPI()->__destruct();
    }

    /**
     * Function to register all the Event Handlers that EssentialsPE provide
     */
    public function registerEvents(){
        $this->getServer()->getPluginManager()->registerEvents(new OtherEvents($this->getAPI()), $this);
        $this->getServer()->getPluginManager()->registerEvents(new PlayerEvents($this->getAPI()), $this);
        $this->getServer()->getPluginManager()->registerEvents(new SignEvents($this->getAPI()), $this);
    }

    /**
     * Function to register all EssentialsPE's commands...
     * And to override some default ones
     */
    private function registerCommands(){
        $commands = [
            new AFK($this->getAPI()),
            new Antioch($this->getAPI()),
            new Back($this->getAPI()),
            //new BigTreeCommand($this->getAPI()), TODO
            new BreakCommand($this->getAPI()),
            new Broadcast($this->getAPI()),
            new Burn($this->getAPI()),
            new ClearInventory($this->getAPI()),
            new Compass($this->getAPI()),
            new Condense($this->getAPI()),
            new Depth($this->getAPI()),
            new EssentialsPE($this->getAPI()),
            new Extinguish($this->getAPI()),
            new Fly($this->getAPI()),
            new GetPos($this->getAPI()),
            new God($this->getAPI()),
            //new Hat($this->getAPI()), TODO: Implement when MCPE implements "Block-Hat rendering"
            new Heal($this->getAPI()),
            new ItemCommand($this->getAPI()),
            new ItemDB($this->getAPI()),
            new Jump($this->getAPI()),
            new KickAll($this->getAPI()),
            new Kit($this->getAPI()),
            new Lightning($this->getAPI()),
            new More($this->getAPI()),
            new Mute($this->getAPI()),
            new Near($this->getAPI()),
            new Nick($this->getAPI()),
            new Nuke($this->getAPI()),
            new Ping($this->getAPI()),
            new PTime($this->getAPI()),
            new PvP($this->getAPI()),
            new RealName($this->getAPI()),
            new Repair($this->getAPI()),
            new Seen($this->getAPI()),
            new SetSpawn($this->getAPI()),
            new Spawn($this->getAPI()),
            //new Speed($this->getAPI()), TODO
            new Sudo($this->getAPI()),
            new Suicide($this->getAPI()),
            new TempBan($this->getAPI()),
            new Top($this->getAPI()),
            //new TreeCommand($this->getAPI()), TODO
            new Unlimited($this->getAPI()),
            new Vanish($this->getAPI()),
            //new Whois($this->getAPI()), TODO
            new World($this->getAPI()),

            //Economy
            //new Balance($this->getAPI()),
            //new Eco($this->getAPI()),
            //new Pay($this->getAPI()),
            //new Sell($this->getAPI()),
            //new SetWorth($this->getAPI()),
            //new Worth($this->getAPI()),

            //Home
            new DelHome($this->getAPI()),
            new Home($this->getAPI()),
            new SetHome($this->getAPI()),

            // Messages
            new Msg($this->getAPI()),
            new Reply($this->getAPI()),

            //PowerTool
            new PowerTool($this->getAPI()),
            new PowerToolToggle($this->getAPI()),

            //Teleport
            new TPA($this->getAPI()),
            new TPAccept($this->getAPI()),
            new TPAHere($this->getAPI()),
            new TPAll($this->getAPI()),
            new TPDeny($this->getAPI()),
            new TPHere($this->getAPI()),

            //Warp
            new DelWarp($this->getAPI()),
            new Setwarp($this->getAPI()),
            new Warp($this->getAPI()),

            //Override
            new Gamemode($this->getAPI()),
            new Kill($this->getAPI())
        ];
        $aliased = [];
        foreach($commands as $cmd){
            /** @var BaseCommand $cmd */
            $commands[$cmd->getName()] = $cmd;
            $aliased[$cmd->getName()] = $cmd->getName();
            foreach($cmd->getAliases() as $alias){
                $aliased[$alias] = $cmd->getName();
            }
        }
        $cfg = $this->getConfig()->get("commands", []);
        foreach($cfg as $del){
            if(isset($alias[$del])){
                unset($commands[$alias[$del]]);
            }else{
                $this->getLogger()->debug("\"$del\" command not found inside EssentialsPE, skipping...");
            }
        }
        $this->getServer()->getCommandMap()->registerAll("EssentialsPE", $commands);
    }

    public function checkConfig(){
        if(!is_dir($this->getDataFolder())){
            mkdir($this->getDataFolder());
        }
        if(!file_exists($this->getDataFolder() . "config.yml")){
            $this->saveDefaultConfig();
        }
        //$this->saveResource("Economy.yml");
        $this->saveResource("Kits.yml");
        $this->saveResource("Warps.yml");
        $cfg = $this->getConfig();

        if(!$cfg->exists("version") || $cfg->get("version") !== "0.0.2"){
            $this->getLogger()->debug(TextFormat::RED . "An invalid config file was found, generating a new one...");
            rename($this->getDataFolder() . "config.yml", $this->getDataFolder() . "config.yml.old");
            $this->saveDefaultConfig();
            $cfg = $this->getConfig();
        }

        $booleans = ["enable-custom-colors"];
        foreach($booleans as $key){
            $value = null;
            if(!$cfg->exists($key) || !is_bool($cfg->get($key))){
                switch($key){
                    // Properties to auto set true
                    case "safe-afk":
                        $value = true;
                        break;
                    // Properties to auto set false
                    case "enable-custom-colors":
                        $value = false;
                        break;
                }
            }
            if($value !== null){
                $cfg->set($key, $value);
            }
        }

        $integers = ["oversized-stacks", "near-radius-limit", "near-default-radius"];
        foreach($integers as $key){
            $value = null;
            if(!is_numeric($cfg->get($key))){
                switch($key){
                    case "auto-afk-kick":
                        $value = 300;
                        break;
                    case "oversized-stacks":
                        $value = 64;
                        break;
                    case "near-radius-limit":
                        $value = 200;
                        break;
                    case "near-default-radius":
                        $value = 100;
                        break;
                }
            }
            if($value !== null){
                $cfg->set($key, $value);
            }
        }

        $afk = ["safe", "auto-set", "auto-broadcast", "auto-kick", "broadcast"];
        foreach($afk as $key){
            $value = null;
            $k = $this->getConfig()->getNested("afk." . $key);
            switch($key){
                case "safe":
                case "auto-broadcast":
                case "broadcast":
                    if(!is_bool($k)){
                        $value = true;
                    }
                    break;
                case "auto-set":
                case "auto-kick":
                    if(!is_int($k)){
                        $value = 300;
                    }
                    break;
            }
            if($value !== null){
                $this->getConfig()->setNested("afk." . $key, $value);
            }
        }

        $updater = ["enabled", "time-interval", "warn-console", "warn-players", "channel"];
        foreach($updater as $key){
            $value = null;
            $k = $this->getConfig()->getNested("updater." . $key);
            switch($key){
                case "time-interval":
                    if(!is_int($k)){
                        $value = 1800;
                    }
                    break;
                case "enabled":
                case "warn-console":
                case "warn-players":
                    if(!is_bool($k)){
                        $value = true;
                    }
                    break;
                case "channel":
                    if(!is_string($k) || ($k !== "stable" && $k !== "beta" && $k !== "development")){
                        $value = "stable";
                    }
            }
            if($value !== null){
                $this->getConfig()->setNested("updater." . $key, $value);
            }
        }
    }

    /**
     * @return BaseAPI
     */
    public function getAPI(): BaseAPI{
        return $this->api;
    }
}
