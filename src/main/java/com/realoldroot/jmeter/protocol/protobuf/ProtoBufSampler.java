package com.realoldroot.jmeter.protocol.protobuf;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author zhengenshen
 * @date 2018-12-04 13:56
 */
@Slf4j
public class ProtoBufSampler extends AbstractSampler {

    private static final String SERVER_HOST = "serverHost";
    private static final String SERVER_PORT = "serverPort";
    private static final String RESULT = "result";

    private static final String LOCAL_MESSAGE_PATH = "localMessagePath";

    private static final String REQUEST_PATH = "requestPath";
    private static final String USER_TOKEN = "userToken";
    private static final String USER_DEVICE_ID = "userDeviceId";


    public SampleResult sample(Entry entry) {

        SampleResult result = new SampleResult();
        result.setSampleLabel(getName());
        try {
            result.sampleStart();
            val data = JoyGameHttpUtil.post(getUrl(), getLocalMessagePath(), getUserToken(), getUserDeviceId());
            log.debug("result data >>>>>>>>>> {}", data);

            result.setSuccessful(data == 1);
            result.setResponseCodeOK();

        } catch (Exception e) {
            result.setSuccessful(false);

        } finally {
            result.sampleEnd();
        }
        return result;
    }

    private String getUrl() {
        val url = "http://" + getServerHost() + ":" + getServerPort() + getRequestPath();
        log.debug("url : {}", url);
        return url;
    }

    public String getResult() {
        return getPropertyAsString(RESULT);
    }

    public String getServerHost() {
        return getPropertyAsString(SERVER_HOST);
    }

    public String getServerPort() {
        return getPropertyAsString(SERVER_PORT);
    }

    public String getLocalMessagePath() {
        return getPropertyAsString(LOCAL_MESSAGE_PATH);
    }

    public String getRequestPath() {
        return getPropertyAsString(REQUEST_PATH);
    }

    public String getUserToken() {
        return getPropertyAsString(USER_TOKEN);
    }

    public String getUserDeviceId() {
        return getPropertyAsString(USER_DEVICE_ID);
    }


    public void setServerHost(String content) {
        super.setProperty(SERVER_HOST, content);
    }

    public void setServerPort(String content) {
        super.setProperty(SERVER_PORT, content);
    }

    public void setLocalMessagePath(String content) {
        super.setProperty(LOCAL_MESSAGE_PATH, content);
    }

    public void setRequestPath(String content) {
        super.setProperty(REQUEST_PATH, content);
    }

    public void setUserToken(String content) {
        super.setProperty(USER_TOKEN, content);
    }

    public void setUserDeviceId(String content) {
        super.setProperty(USER_DEVICE_ID, content);
    }

    public void setResult(String content) {
        super.setProperty(RESULT, content);
    }
}
