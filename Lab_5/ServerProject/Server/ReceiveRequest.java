package com.Lab_5.ServerProject.Server;

import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ServerProject.Support.Deserializer;
import com.Lab_5.ServerProject.Support.EnDeCodeServer;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ReceiveRequest {
    /**
     * Событие готовности процесса чтения
     * Поместите полученные данные в буфер
     *
     * @param key
     * @throws IOException
     */
    public static void read(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(65535);

        //через инпуты и оутпуты читать нельзя, неблокирующий режим
        int numRead = -1;
        numRead = socketChannel.read(buffer);
        if (numRead == -1) {
            Socket socket = socketChannel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteAddr);
            socketChannel.close();
            key.cancel();
        }
        if(numRead == 4) {//если не прочитал ничего, но непонятно почему сюда зашел
            return;
        }
        MyCommand myCommand = (MyCommand) Deserializer.byteBufferToObject(buffer, numRead);
        System.out.println(myCommand.toString());
//        socketChannel.register(this.selector, SelectionKey.OP_WRITE);

//        byte[] data = new byte[numRead];
//        System.arraycopy(buffer.array(), 0, data, 0, numRead);
//        System.out.println("Got: " + new String(data));
    }
}
