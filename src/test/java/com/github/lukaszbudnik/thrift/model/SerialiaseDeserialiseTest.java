package com.github.lukaszbudnik.thrift.model;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class SerialiaseDeserialiseTest {

    @Test
    public void shouldSerialiseDeserialise() throws TException {
        TestRunV1 testRunV1 = new TestRunV1("test run v1", 2121212121, new ArrayList<TestCaseV1>());

        byte[] bytes = new byte[1024];
        new Random().nextBytes(bytes);
        TestCaseV1 testCaseV1 = new TestCaseV1("test case v1");
        testCaseV1.setContent(bytes);
        testRunV1.addToTestCases(testCaseV1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TTransport otrans = new TIOStreamTransport(baos);
        TProtocol oprot = new TBinaryProtocol(otrans);
        testRunV1.write(oprot);

        System.out.println(baos.size());

        TestRunV2 testRunV2 = new TestRunV2();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        TTransport itrans = new TIOStreamTransport(bais);
        TProtocol iprot = new TBinaryProtocol(itrans);
        testRunV2.read(iprot);

        Assert.assertEquals(testRunV1.getName(), testRunV2.getName());
        Assert.assertEquals(testRunV1.getTestCases().get(0), testRunV2.getTestCases().get(0));
    }

}
