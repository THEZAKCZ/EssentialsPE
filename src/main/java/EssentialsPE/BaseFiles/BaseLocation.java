package EssentialsPE.BaseFiles;

import cn.nukkit.level.Level;
import cn.nukkit.level.Location;

class BaseLocation extends Location{

    protected String name;

    public BaseLocation(String name, double x, double y, double z, Level level, double yaw, double pitch){
        super(x, y, z, yaw, pitch, level);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static BaseLocation fromPosition(String name, Location pos){
        return new BaseLocation(name, pos.getX(), pos.getY(), pos.getZ(), pos.getLevel(), pos.getYaw(), pos.getPitch());
    }
}