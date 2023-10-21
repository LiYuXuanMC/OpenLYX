package al.nya.reflect.config.configs;


import al.nya.reflect.config.Configurable;
import al.nya.reflect.features.modules.World.Waypoints;
import al.nya.reflect.features.modules.World.waypoint.WaypointManager;
import al.nya.reflect.utils.FileUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.File;

public final class WaypointsConfig extends Configurable {

    public WaypointsConfig(File dir) {
        super(FileUtil.createJsonFile(dir, "Waypoints"));
    }

    @Override
    public void onLoad() {
        super.onLoad();

        final JsonArray waypointsJsonArray = this.getJsonObject().get("Waypoints").getAsJsonArray();

        for (JsonElement jsonElement : waypointsJsonArray) {
            if (jsonElement instanceof JsonArray) {
                final JsonArray waypointDataJson = (JsonArray) jsonElement;

                final String host = waypointDataJson.get(0).getAsString();
                final String name = waypointDataJson.get(1).getAsString();
                final int x = waypointDataJson.get(2).getAsInt();
                final int y = waypointDataJson.get(3).getAsInt();
                final int z = waypointDataJson.get(4).getAsInt();
                final int dimension = waypointDataJson.get(5).getAsInt();
                final String color = waypointDataJson.get(6).getAsString();

                final Waypoints.WaypointData waypointData = new Waypoints.WaypointData(host, name, dimension, x, y, z);
                waypointData.setColor((int) Long.parseLong(color, 16));

                WaypointManager.INSTANCE.getWaypointDataList().add(waypointData);
            }
        }
    }

    @Override
    public void onSave() {
        JsonObject save = new JsonObject();
        JsonArray waypointsJsonArray = new JsonArray();

        WaypointManager.INSTANCE.getWaypointDataList().forEach(waypoint -> {
            JsonArray waypointDataJson = new JsonArray();
            waypointDataJson.add(new JsonPrimitive(waypoint.getHost()));
            waypointDataJson.add(new JsonPrimitive(waypoint.getName()));
            waypointDataJson.add(new JsonPrimitive((int) waypoint.getX()));
            waypointDataJson.add(new JsonPrimitive((int) waypoint.getY()));
            waypointDataJson.add(new JsonPrimitive((int) waypoint.getZ()));
            waypointDataJson.add(new JsonPrimitive(waypoint.getDimension()));
            waypointDataJson.add(new JsonPrimitive(Integer.toHexString(waypoint.getColor()).toUpperCase()));
            waypointsJsonArray.add(waypointDataJson);
        });

        save.add("Waypoints", waypointsJsonArray);
        this.saveJsonObjectToFile(save);
    }
}