package al.logger.client.utils.network;

import lombok.Getter;
import lombok.Setter;

public class Connect {

    @Getter
    @Setter
    public String header;

    @Getter
    @Setter
    public String body;

    public Connect(String header, String body) {
        this.body = body;
        this.header = header;
    }

}
