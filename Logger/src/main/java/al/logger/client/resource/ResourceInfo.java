package al.logger.client.resource;

import al.logger.client.Logger;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResourceInfo {
    public String name;
    public ResourceLocation resourceLocation;
    public byte[] bytes;

    public ResourceInfo(String name) {
        this.name = name;
        this.bytes = null;
        this.resourceLocation = null;
    }

    public ResourceLocation getResourceMC() {
        if (resourceLocation == null) {
            this.resourceLocation = new ByteLocation(this.bytes, this.name).getResourceLocation();
            return this.resourceLocation;
        }
        return this.resourceLocation;
    }

    public void download() {
        String httpURL = Logger.getInstance().resourceManager.downloadURL + "/ack_rex/hycraft"
                + "?userid=" + Logger.getInstance().getLoggerUser().getUserid()
                + "&jtoken=" + Logger.getInstance().getLoggerUser().getJToken()
                + "&resource=" + name;
        try {
            this.bytes = downloadBytes(httpURL);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to download");
            throw new RuntimeException(e);
        }
        if (Logger.getInstance().isMySelfObf()) {
            System.out.println("Downloaded! " + this.bytes.length);
        }
    }

    public static byte[] downloadBytes(String url) throws IOException {
        URL imgURL = new URL(url.trim());//转换URL
        HttpURLConnection urlConn = (HttpURLConnection) imgURL.openConnection();//构造连接
        urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
        urlConn.connect();
        urlConn.setConnectTimeout(5000);
        urlConn.setReadTimeout(5000);
        InputStream ins = urlConn.getInputStream(); //获取输入流,从网站读取数据到 内存中
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (urlConn.getResponseCode() == 200) {//返回的状态码是200 表示成功
            int len = 0;
            byte[] buff = new byte[1024 * 1024];//1MB缓冲流 视你内存大小而定咯
            while (-1 != (len = (new BufferedInputStream(ins)).read(buff))) {//长度保存到len,内容放入到 buff
                out.write(buff, 0, len);//将图片数组内容写入到图片文件
            }
            urlConn.disconnect();
            ins.close();
            out.close();
        }
        return out.toByteArray();
    }

    public final static byte[] doGetRequestForFile(String urlStr) {
        InputStream is = null;
        ByteArrayOutputStream os = null;
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("charsert", "utf-8");
            conn.connect();
            is = conn.getInputStream();
            os = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            conn.disconnect();
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
