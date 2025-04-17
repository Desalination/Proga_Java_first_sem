package com.Lab_5.ClientProject.Client;

import com.Lab_5.ClientProject.Client.ClientCommands.Help;
import com.Lab_5.ClientProject.Support.InputConsole;
import com.Lab_5.ClientProject.Support.Serialize;
import com.Lab_5.ServerProject.Exceptions.ServerTempNotResponse;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

//установим связь с сервером
public class Client2 {

    //    private static Charset charset = Charset.forName("UTF8");
    private static ByteBuffer readBuffer;
    private static String response;
    private static Socket clientSocket; //сокет для общения

//    private static ObjectOutputStream out;

    private static DataOutputStream out;

//    DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

    public static DataInputStream in;
    private static byte[] readBytes = new byte[65535];


    public static void main(String[] args) throws IOException {
        try {

            System.out.println("Connecting to server...");
            // адрес - локальный хост, порт - 9093, такой же как у сервера
            try {
                clientSocket = new Socket("localhost", 9093); // этой строкой мы запрашиваем подключение к серверу
            } catch (Exception ex) {
                throw new ServerTempNotResponse();
            }
            System.out.println("Connecting to server " + clientSocket.getRemoteSocketAddress().toString() + " is succeed.");

//                out = new ObjectOutputStream(clientSocket.getOutputStream());

            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());

            MyCommand myCommand = new MyCommand();
//                myCommand.setCommandName("help");
//                out.write(Serialize.ObjectToByteArray(myCommand));//byte[]
//                out.flush();
//                in.read(readBytes);//Получим сообщение с командой help
            Help.execute();//
            while (true) {
                try {

                    myCommand = InputConsole.inputCommand();
                    if (myCommand.getCommandName().equals("exit")) {
                        System.out.println("The end of the client Program. Goodbye!");
                        break;
                    }

                    out.write(Serialize.ObjectToByteArray(myCommand));//byte[]
//                        out.writeObject(myCommand);Object
                    out.flush();
                    in.read(readBytes);

                    response = new String(readBytes);
                    Arrays.fill(readBytes, (byte) 0);
                    response = response.replaceAll("[^A-Za-zА-Яа-я0-9!@#$%^&*()_+=\"-`~{} :'/.,|\n\r\s\t]", "");
                    System.out.println(response);
                }
                catch (Exception ex) {
                    System.err.println(ex);
                    System.err.println("Error was got while getting the command.");
                }
            }
            System.out.println("Client was closed...");
            clientSocket.close();
            in.close();
            out.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
//            finally { // в любом случае необходимо закрыть сокет и потоки
//                System.out.println("Client was closed...");
//                clientSocket.close();
//                in.close();
//                out.close();
//            }
    }
}


