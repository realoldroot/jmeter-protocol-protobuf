package com.realoldroot.jmeter.protocol.protobuf;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zhengenshen
 * @date 2018-12-04 14:44
 */
@Slf4j
public class JoyGameHttpUtil {

    public static byte post(String url, String localMessagePath, String token, String deviceId) throws Exception {

        val client = HttpClients.createDefault();
        val httpPost = new HttpPost(url);
        val requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(50000)
                .setSocketTimeout(10000)
                .build();
        httpPost.setConfig(requestConfig);
        val params = new HashMap<String, byte[]>(1);

        val entity = MultipartEntityBuilder.create();
        entity.setCharset(Charset.forName("UTF-8"));
        entity.addBinaryBody("msgFile", read(localMessagePath), ContentType.APPLICATION_OCTET_STREAM, "msgFile" + ".dat");
        entity.addTextBody("token", token);
        entity.addTextBody("deviceId", deviceId);

        httpPost.setEntity(entity.build());
        // 设置User-Agent
        httpPost.addHeader("User-Agent", "JoyYou");

        byte[] resData;

        try {
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                httpPost.abort();
                log.warn("request error, url: {}, statusCode: {}", url, statusCode);
                throw new Exception();
            }

            val responseEntity = response.getEntity();
            resData = EntityUtils.toByteArray(responseEntity);
            EntityUtils.consume(responseEntity);
        } catch (IOException e) {
            httpPost.abort();
            //发生网络异常
            throw new Exception("request Error, url: " + url, e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                log.warn("HttpProtoBufUtil request Error, url: " + url, e);
            }
        }
        if (resData == null) {
            throw new Exception();
        }
        if (resData.length < 1) {
            throw new Exception();
        }
        return resData[0];
    }

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] read(String path) throws Exception {
        @Cleanup val fs = new FileInputStream(path);
        val bytes = new byte[fs.available()];
        fs.read(bytes);
        return bytes;
    }

    public static void main(String[] args) {
        // byte[] bytes = new byte[]{2, 8, 2, 18};
        // int a = byteArrayToInt(bytes);
        // System.out.println(a);

        byte[] bytes = intToByteArray(1);
        System.out.println(Arrays.toString(bytes));
    }
}
