package al.logger.client.utils.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WebConnection {
    public ArrayList<GetHeader> requestPropertys = new ArrayList<>();
    public boolean isUseragent;
    public String encoding = "UTF-8";
    public int timerOut = 0;
    public String url;

    public WebConnection(boolean isUseragent) {
        this.isUseragent = isUseragent;
    }

    public WebConnection(boolean isUseragent, String encoding) {
        this.isUseragent = isUseragent;
        this.encoding = encoding;
    }

    public String getUrlConnection(String url, String type, GetHeader... headers) throws IOException {
        StringBuilder baseurl = new StringBuilder();
        if (headers != null) {
            baseurl = new StringBuilder("?");
            int i = 0;
            for (GetHeader header : headers) {
                if (i == 0) {
                    baseurl.append(header.getHeader()).append("=").append(header.getContent());
                } else {
                    baseurl.append("&").append(header.getHeader()).append("=").append(header.getContent());
                }
                i++;
            }
        }
        URL urlObj = new URL(url + baseurl);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod(type);
        connection.setReadTimeout(timerOut);

        for (GetHeader header : requestPropertys) {
            connection.setRequestProperty(header.getHeader(), header.getContent());
        }
        requestPropertys.clear();
        if (isUseragent) {
            requestPropertys.add(new GetHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.52"));
        }

        connection.connect();
        int code = connection.getResponseCode();
        if (code == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
            this.url = connection.getURL().toString();

            return stringBuilder.toString();
        } else {
            return "error:" + code;
        }
    }

    public String getUrlHtml(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        for (GetHeader header : requestPropertys) {
            connection.setRequestProperty(header.getHeader(), header.getContent());
        }
        requestPropertys.clear();
        if (isUseragent) {
            requestPropertys.add(new GetHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.52"));
        }

        connection.connect();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), encoding);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line).append("\n");
        }
        return buffer.toString();
    }

    public String getI18HttpStatusString(String url) throws IOException {
        URL serverUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverUrl
                .openConnection();
        connection.setRequestMethod("GET");

        connection.setInstanceFollowRedirects(false);
        for (GetHeader header : requestPropertys) {
            connection.setRequestProperty(header.getHeader(), header.getContent());
        }

        connection.connect();
        String location = connection.getHeaderField("Location");
        serverUrl = new URL(location);
        connection = (HttpURLConnection) serverUrl.openConnection();
        connection.setRequestMethod("GET");

        connection.setInstanceFollowRedirects(false);
        for (GetHeader header : requestPropertys) {
            connection.setRequestProperty(header.getHeader(), header.getContent());
        }

        requestPropertys.clear();
        if (isUseragent) {
            requestPropertys.add(new GetHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.52"));
        }

        connection.connect();
        connection.disconnect();

        return location;
    }

    public void addRequestProperty(String header, String content) {
        requestPropertys.add(new GetHeader(header, content));
    }

    public void removeRequestProperty(String header) {
        requestPropertys.removeIf(getHeader -> getHeader.getHeader().equals(header));
    }

    public String getReal(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode >= 300 && responseCode < 400) {
                return connection.getHeaderField("Location");
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return url;
        }
    }


    public void clearRequestProperty() {
        requestPropertys.clear();
    }

    public boolean isUseragent() {
        return isUseragent;
    }

    public void setUseragent(boolean useragent) {
        isUseragent = useragent;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getTimerOut() {
        return timerOut;
    }

    public void setTimerOut(int timerOut) {
        this.timerOut = timerOut;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
