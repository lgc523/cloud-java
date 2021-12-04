package dev.spider.consumer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author lgc
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Integer i = 0x12345678;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(i);

        byte[] bytes = bos.toByteArray();
        for (byte aByte : bytes) {
            System.out.println(Integer.toHexString(aByte));
        }
    }

}
