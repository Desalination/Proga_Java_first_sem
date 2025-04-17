package com.Lab_5.ServerProject.Support;

import java.io.*;
import java.nio.ByteBuffer;

public class Deserializer {
    public static Object byteBufferToObject(ByteBuffer byteBuffer, int numRead)
            throws Exception {
        byte[] bytes = new byte[numRead];

//        if(byteBuffer.get(0)!=-84){
//            byteBuffer = addHeader(byteBuffer, numRead);
//        }

        if(byteBuffer.hasArray())
            bytes = byteBuffer.array();
        else
            byteBuffer.get(bytes);
        Object object = deSerializer(bytes);
        return object;
    }

    public static Object deSerializer(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//        new ObjectInputStream()
        Object obj = objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return obj;
    }
    public static ByteBuffer addHeader(ByteBuffer b2,  int numRead) throws IOException {
        ByteBuffer b1 = ByteBuffer.allocate(4);
        b1.put(0, (byte)-84);
        b1.put(1, (byte)-19);
        b1.put(2, (byte)0);
        b1.put(3, (byte)-5);
//        byteBuffer[0];
//        byteBuffer = ByteBuffer.allocate(numRead+4).put(header).put(byteBuffer);
//        return byteBuffer;
        int capacity = ((b1.limit() == 0 ? b1.capacity() : b1.limit()))
                + ((b2.limit() == 0 ? b2.capacity() : b2.limit()));
        ByteBuffer dst;
        if (b1.isDirect() || b2.isDirect()) {
            dst = ByteBuffer.allocateDirect(capacity);
        } else {
            dst = ByteBuffer.allocate(capacity);
        }
        b1.rewind();
        dst.put(b1);
        b2.rewind();
        dst.put(b2);
        return dst;
    }
}
