package al.logger.client.ui.bases.components;

import al.logger.client.Logger;
import al.logger.client.resource.ByteLocation;
import al.logger.client.resource.ResourceInfo;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class SongListObject {

    @Getter
    @Setter
    private String songListID;
    @Getter
    @Setter
    private String songListTitle;
    @Getter
    private ResourceLocation imgResourceLocation;

    public SongListObject(String songListID, String songListTitle, String songListImg) {
        try {
            this.songListID = songListID;
            this.songListTitle = songListTitle;
            this.imgResourceLocation = new ByteLocation(ResourceInfo.downloadBytes(songListImg), songListTitle).getResourceLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
