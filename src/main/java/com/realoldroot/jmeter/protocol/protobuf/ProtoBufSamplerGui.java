package com.realoldroot.jmeter.protocol.protobuf;

import lombok.val;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledTextField;

import javax.swing.*;
import java.awt.*;

/**
 * @author zhengenshen
 * @date 2018-12-04 13:46
 */
public class ProtoBufSamplerGui extends AbstractSamplerGui {

    private JPanel mainPanel;

    private JLabeledTextField serverHost = new JLabeledTextField("serverHost");
    private JLabeledTextField serverPort = new JLabeledTextField("serverPort");
    private JLabeledTextField localMessagePath = new JLabeledTextField("localMessagePath");
    private JLabeledTextField requestPath = new JLabeledTextField("requestPath");


    private JLabeledTextField userToken = new JLabeledTextField("userToken");
    private JLabeledTextField userDeviceId = new JLabeledTextField("userDeviceId");



    public ProtoBufSamplerGui() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        initMainPanel();
        add(mainPanel, BorderLayout.CENTER);
    }

    // 布局
    private void initMainPanel() {
        mainPanel = new VerticalPanel();
        mainPanel.add(serverHost);
        mainPanel.add(serverPort);
        mainPanel.add(localMessagePath);
        mainPanel.add(requestPath);
        mainPanel.add(userToken);
        mainPanel.add(userDeviceId);
    }

    /**
     * 该方法用于把Sampler中的数据加载到界面中。
     * 在实现自己的逻辑之前，先调用一下父类的方法super.configure(el)，
     * 这样可以确保框架自动为你加载一些缺省数据，比如Sampler的名字。
     *
     * @param element
     */

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (!(element instanceof ProtoBufSampler)) {
            return;
        }
        val sampler = (ProtoBufSampler) element;

        serverHost.setText(sampler.getServerHost());
        serverPort.setText(sampler.getServerPort());
        localMessagePath.setText(sampler.getLocalMessagePath());
        requestPath.setText(sampler.getRequestPath());
        userToken.setText(sampler.getUserToken());
        userDeviceId.setText(sampler.getUserDeviceId());
    }

    public String getLabelResource() {
        return "protobuf Sampler";
    }

    public TestElement createTestElement() {
        ProtoBufSampler sampler = new ProtoBufSampler();
        modifyTestElement(sampler);
        return sampler;
    }

    /**
     * 这个方法用于把界面的数据移到Sampler中，刚好与上面的方法相反。
     * 在调用自己的实现方法之前，
     * 请先先调用一下super.configureTestElement(e)，这个会帮助移掉一些缺省的数据。
     *
     * @return
     */
    public void modifyTestElement(TestElement element) {
        val sampler = (ProtoBufSampler) element;
        sampler.clear();
        super.configureTestElement(element);
        sampler.setServerHost(serverHost.getText());
        sampler.setServerPort(serverPort.getText());
        sampler.setLocalMessagePath(localMessagePath.getText());
        sampler.setRequestPath(requestPath.getText());
        sampler.setUserToken(userToken.getText());
        sampler.setUserDeviceId(userDeviceId.getText());

    }

    /**
     * 该方法会在reset新界面的时候调用，这里可以填入界面控件中需要显示的一些缺省的值。
     */
    @Override
    public void clearGui() {
        super.clearGui();
    }
}
