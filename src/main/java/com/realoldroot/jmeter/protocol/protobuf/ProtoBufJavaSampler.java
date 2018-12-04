package com.realoldroot.jmeter.protocol.protobuf;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author zhengenshen
 * @date 2018-12-04 11:24
 */
public class ProtoBufJavaSampler extends AbstractJavaSamplerClient {

    // 性能测试时的线程运行体，即测试执行的循环体，根据线程数和循环次数的不同可执行多次
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.setSampleLabel("");
        return null;
    }

    //用于设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter参数列表中
    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("serverUrl", "");
        arguments.addArgument("messagePath", "");
        arguments.addArgument("token", "");
        arguments.addArgument("deviceId", "");
        return arguments;
    }



    //初始化方法，用于初始化性能测试时的每个线程，实际运行时每个线程仅执行一次
    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
    }


    // 测试结束方法，用于结束性能测试中的每个线程，实际运行时，每个线程仅执行一次，在测试方法运行结束后执行
    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }

}
