package com.Lab_5.ServerProject.Server;


import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ClientProject.Exceptions.InputFileNotFoundException;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ServerProject.Commands.Save;
import com.Lab_5.ServerProject.Support.*;
import com.Lab_5.ServerProject.Support.FileReader;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;


import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class NIOServer {
    private Selector selector;
    private Data data = new Data();
    private final static String fileStorageName = "D:\\Denis\\Programs\\Java_codes\\Lab5\\src\\Resources\\collection.json";
    private InetSocketAddress listenAddress;
    private final static int PORT = 9093;

    public static void main(String[] args) throws Exception {
        try {
//            if(args.length == 0)//для рабочего режима работы
//                throw new InputFileNotFoundException("Not found file name of storage.");
            new NIOServer("localhost", PORT, fileStorageName).startServer();
        } catch (IOException e) {
            e.printStackTrace();
//            System.err.println();
        }
    }

    public NIOServer(String address, int port, String fileStorageName) throws IOException {
        listenAddress = new InetSocketAddress(address, PORT);
        data = new Data(fileStorageName, new JSONparser(), new FileReader());
    }

    /**
     * Start the server
     *
     * @throws IOException
     */
    private void startServer() throws Exception {
        int counter = 0;
        boolean afterAccept = false;
        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

// выполняю над ними команды
        //внедрить execute scr
// если с командами все норм, сохраняю,
// иначе не сохраняю, и говорю, что конкретно не так
//ответы от сервера получать адекватные о выполнении команд
//реализовать команду, которую забыл

        //проверить на лупинг в клиенте лучше
        // bind server socket channel to port

        serverChannel.socket().bind(listenAddress);
        serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        String response = "";


        System.out.println("Server started on port >> " + PORT);
        while (true) {
            // wait for events

            int readyCount = selector.select();
            if (readyCount == 0) {
                continue;
            }

            // process selected keys...
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator iterator = readyKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();

                // Remove key from set so we don't process it twice
                iterator.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) { // Accept client connections
                    this.accept(key);
                } else if (key.isReadable()) { // Read from client
                    //System.out.println("Readable");
                    response = this.read(key);
                } else if (key.isWritable()) {
                    //System.out.println("Writable");
                    this.send(key, response);
                }
            }
        }
    }

    // accept client connection
    private void accept(SelectionKey key) throws IOException {
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
        channel.register(this.selector, SelectionKey.OP_READ);

    }

    // read from the socket channel
    private String read(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(65535);

        //через инпуты и оутпуты читать нельзя, неблокирующий режим
        int numRead = -1;
        numRead = socketChannel.read(buffer);
        if (numRead == -1) {
            Socket socket = socketChannel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
//            System.out.println("Connection closed by client: " + remoteAddr);
            socketChannel.close();
            key.cancel();
            return "Connection closed by client: " + remoteAddr;
        }
//        if(numRead == 4) {//если не прочитал ничего, но непонятно почему сюда зашел, а такое бывало
//            socketChannel.register(this.selector, SelectionKey.OP_WRITE);
//            return;
//        }
        System.out.println("Command is read.");
        MyCommand myCommand = (MyCommand) Deserializer.byteBufferToObject(buffer, numRead);
        //предлагаю id или scripts сделать вторыми переменными, а не переменной одного элемента
        if(myCommand.getId()==-1)
            myCommand.getArgs().addFirst(myCommand.getCommandName());//привели к виду, для заданного изначальной архитектеры выполнения команд
        else
            myCommand.getArgs().addFirst(myCommand.getCommandName() + " " + myCommand.getId().toString());//привели к виду, для заданного изначальной архитектеры выполнения команд
        try {
//            String response = "";
            String response = Call_commands.call(myCommand, myCommand.getArgs(), data);//выполнили команду
            Save.execute(data);//сохранили выполнение, если все норм
            System.out.println("Command: " + myCommand.toString() + " perfomed.");
            socketChannel.register(this.selector, SelectionKey.OP_WRITE);
            return response;
        }
        catch(Exception ex){
            socketChannel.register(this.selector, SelectionKey.OP_WRITE);
            return ex.toString();
        }//args:commandName, id(if have) o&r scripts(if have)

    }

    private void send(SelectionKey key, String response) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        Charset charset = Charset.forName("UTF-8");
        //String strByteBuffer = "Message from Server!!!";
        socketChannel.write(charset.encode(response));//byteBuffer
        //System.out.println(response);
        System.out.println("Response was send!");
        socketChannel.register(this.selector, SelectionKey.OP_READ);

        // Имеется более одной строки данных, перехватить одну строку данных
//        String outputData = data.substring(0, data.indexOf("\n") + 1);
//        System.out.println(outputData);
//        // Преобразуем строку вывода в байты в соответствии с кодировкой gbk и все равно помещаем в outputBuffer
//        ByteBuffer outputBuffer = EnDeCodeServer.encode("echo" + outputData);
//
//        //outputBuffer.hasRemaining () Определить, есть ли необработанные байты
//        // В неблокирующем режиме не гарантируется, что метод записи отправит все байты outputBuffer за один раз, но соблюдается принцип отправки столько, сколько может быть отправлено, поэтому мы должны принять цикл
//        while (outputBuffer.hasRemaining()) {
//            socketChannel.write(outputBuffer);
//        }
//
//        // Я думаю, это эквивалентно перемещению указателя стека и последующему удалению сегмента данных, на который не указывает
//        ByteBuffer temp = EnDeCodeServer.encode(outputData);
//        // Устанавливаем положение буфера на предел temp
//        buffer.position(temp.limit());
//        // Удаляем обработанные данные в буфере
//        buffer.compact();
//        // Если строка "bye \ r \ n" была выведена, отключите selectionKet и закройте SocketChannel
//        if (outputData.equals("bye\r\n")) {
//            key.cancel();
//            socketChannel.close();
//            System.out.println("Закройте соединение с клиентом !");
//        }
    }
}