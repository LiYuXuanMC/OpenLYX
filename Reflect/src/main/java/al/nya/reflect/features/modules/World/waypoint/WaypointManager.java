package al.nya.reflect.features.modules.World.waypoint;

import al.nya.reflect.features.modules.World.Waypoints.WaypointData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Seth
 * 5/8/2019 @ 6:50 AM.
 */
public final class WaypointManager {
    public static WaypointManager INSTANCE;

    static {
        INSTANCE = new WaypointManager();
    }

    @Getter
    @Setter
    private List<WaypointData> waypointDataList = new ArrayList<>();

    public WaypointData find(String host, String name) {
        for (WaypointData data : this.waypointDataList) {
            if (data.getHost().equalsIgnoreCase(host) && data.getName().toLowerCase().startsWith(name.toLowerCase())) {
                return data;
            }
        }
        return null;
    }

    public void unload() {
        this.waypointDataList.clear();
    }

}