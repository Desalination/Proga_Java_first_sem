package com.Lab_5.ServerProject.Server;

import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ServerProject.Commands.*;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Exceptions.InvalidCommandException;

import java.io.IOException;
import java.util.*;

/**
 * Модуль обработки команд
 */

public class Call_commands /*implements CommandManager*/{

//    HashMap<String, Command> namesAndCommands = new HashMap<>();
    static boolean globalState = true;

    public static String call(MyCommand myCommand, LinkedList<String> commands, Data data) throws IOException {
//args:commandName, id or scripts
//        LinkedList<String> commands = myCommand.getArgs();
        //String response = "";
        String response = "";
        while (true) {
            if((commands.size()==0))//выход из скрипта, если закончились команды
                break;
            switch (commands.getFirst().split(" ")[0]) {//ввод команды
                case ("add")://args has name and nothing more or name and scripts
                    response += Add.execute(data, myCommand, commands);
                    break;
                case ("average_of_minutes_of_waiting"):
                    response +=Average_of_minutes_of_waiting.execute(data, commands);
                    break;
                case ("clear"):
                    response +=Clear.execute(data, commands);
                    break;
                case ("execute_script"):
                    //yCommand.getArgs().removeFirst();//удалили execute_script
                    response +=Execute_script.execute(data, myCommand);
                    if(commands.size()>0)
                        commands.removeFirst();
                    break;
                case("group_counting_by_has_toothpick"):
                    response += Group_counting_by_has_toothpick.execute(data, myCommand, commands);
                    break;
                case ("help"):
                    response += Help.execute(data, commands);
                    break;
                case ("info"):
                    response += Info.execute(data, commands);
                    break;
                case ("print_descending"):
                    response += Print.PrintDescending.execute(data, commands);
                    break;
                case ("remove_by_id"):
                    response += Remove.RemoveById.execute(data, commands);
                    break;
                case ("remove_first"):
                    response += Remove.RemoveFirst.execute(data, commands);
                    break;
                case ("remove_greater"):
                    response += Remove.RemoveGreater.execute(data, commands);
                    break;
                case ("remove_head"):
                    response += Remove.RemoveHead.execute(data, commands);
                    break;
                case ("show"):
                    response += Print.Show.execute(data, commands);
                    break;
                case ("update"):
                    response += Update.execute(data,myCommand, commands);
                    break;
                default:
//                    state = false;
//                    System.out.println("Unidentified command: {" + commands.get(0).split(" ")[0] + "}\nCheck correctness of the entered data.");
//                    commands.removeFirst();//не нашли команду -> удаляем ее
                    throw new InvalidCommandException("\nUnidentified command: {" + commands.get(0) + "}\nCheck correctness of the entered data.");
//                    return "Undentified command: {" + commands.get(0) + "}\nCheck correctness of the entered data.";
            }
        }
        return response;
    }
    public static boolean isCommand (String command){
        switch (command) {
            case ("add"):
                return true;
            case ("average_of_minutes_of_waiting"):
                return true;

            case ("clear"):
                return true;
            case ("execute_script"):
                return true;
            case ("exit"):
                return true;
            case ("help"):
                return true;
            case ("info"):
                return true;
            case ("print_descending"):
                return true;
            case ("remove_by_id"):
                return true;
            case ("remove_first"):
                return true;
            case ("remove_greater"):
                return true;
            case ("remove_head"):
                return true;
            case ("save"):
                return true;
            case ("show"):
                return true;
            case ("update"):
                return true;
            default:
//                System.out.println("Not valid format of entered commands.");
                return false;
        }
    }
}
