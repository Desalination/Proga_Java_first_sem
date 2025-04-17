package com.Lab_5.ServerProject.Server;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Модуль приема подключений
 */

public class ReceptionConnections {
    public static void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        System.out.println("Connected to: " + remoteAddr);
        /*
         * Register channel with selector for further IO (record it for read/write
         * operations, here we have used read operation)
         */
//        channel.register(this.selector, SelectionKey.OP_READ);
    }
}
