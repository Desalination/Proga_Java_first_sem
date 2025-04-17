package com.Lab_5.ClientProject.Support;

import com.Lab_5.ClientProject.Client.ClientCommands.Help;
import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ClientProject.Exceptions.InputFileNotFoundException;
import com.Lab_5.ClientProject.Exceptions.InvalidCommandException;
import com.Lab_5.ClientProject.Exceptions.InvalidLongException;
import com.Lab_5.ClientProject.Exceptions.LoopingSriptsException;
import com.Lab_5.ClientProject.HumanBeing.HumanBeing;
import com.Lab_5.ClientProject.Support.InputConsole;
import com.Lab_5.ServerProject.Commands.*;
import com.Lab_5.ServerProject.Support.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class CommandValidation {

    public MyCommand validation(MyCommand myCommand) throws IOException {//работа с аргументами
        String commandName = myCommand.getCommandName();
        if (commandName.equals("add") || (commandName.equals("update")) /*&& !myCommand.getId().equals(-1L))*/) {
            //если нужно передать новый элемент
            if(commandName.equals("update")) {
                myCommand.setId(Long.parseLong(myCommand.getArgs().getFirst()));//добавили id в id из аргс
                myCommand.getArgs().removeFirst();//убрали id из аргс, допустим
            }//отправлять в аргс id или нет?
            myCommand.setTransfer(prepareHumanBeing());
            return myCommand;
        }
        //значит у меня от клиента в args: id или скрипты для выполнения
        if (/*commandName.equals("update") ||*/
                commandName.equals("remove_by_id") ||
                commandName.equals("remove_greater")) {
            //если нужно передать айди и проверить его существование
            Long id = Long.parseLong(myCommand.getArgs().getFirst());
            if (id < 0)
                throw new InvalidLongException();
            myCommand.setId(Long.parseLong(myCommand.getArgs().getFirst()));//добавили id в переменную айди, проверю на существование в сервере, вернусь, подгтовлю, отправлю
            myCommand.getArgs().removeFirst();//убрали id из аргс
            return myCommand;
        }

        if (commandName.equals("average_of_minutes_of_waiting") ||
                commandName.equals("clear") ||
                commandName.equals("help") ||
                commandName.equals("info") ||
                commandName.equals("print_descending") ||
                commandName.equals("remove_first") ||
                commandName.equals("remove_head") ||
                commandName.equals("exit") ||
                commandName.equals("group_counting_by_has_toothpick") ||
                commandName.equals("show"))
            return myCommand;
        if (commandName.equals("execute_script")) {
            String fileName = myCommand.getArgs().get(0).split(" ")[0];
            FileReader fileReader = new FileReader();
            //нужно прочитать в строку
            //закинуть ее в аргс
            //проверить есть ли там эксекьют
            //повторять первые шаги, пока не будет потерян
            //а если несколько execute_scriptov в одном файле? лучше сделать рекурсивно
            myCommand.getArgs().removeFirst();//удаляем имя файла из аргументов
            String script = fileReader.readFromFile(fileName);
            executeStringValidation(fileReader, script, myCommand.getArgs());
            return myCommand;
        } else
            throw new InvalidCommandException("\nUnidentified command: {" + myCommand.getCommandName() + "}\nCheck correctness of the entered data.");
    }

    private static HumanBeing prepareHumanBeing() {
        System.out.println("Creating new HumanBeing: ");
        return new HumanBeing(InputConsole.inputName(),
                InputConsole.inputCoordinates(),
                InputConsole.inputRealHero(),
                InputConsole.inputHasToothPick(),
                InputConsole.inputImpactSpeed(),
                InputConsole.inputSoundtrackName(),
                InputConsole.inputMinutesOfWaiting(),
                InputConsole.inputMood(),
                InputConsole.inputCar()
        );
    }

    private static LinkedList<String> commands = new LinkedList<>();

    private static LinkedList<String> executeStringValidation(FileReader fileReader, String script, LinkedList<String> scripts) throws IOException {
//        boolean foundExecuteScript = true;
//        String script = fileReader.readFromFile(fileName);//прочитали весь скрипт в строку
//        if (scripts.contains(script)) {//в скриптах не может быть два одинаковых скрипта без зацикливания
//            throw new LoopingSriptsException();
//        }

        String fileName ="";
        scripts.add(script);//добавили скрипт, как элемент


        int prev = 0;//добавить
        char sym;
        //как-то нужно
//        LinkedList<LinkedList<String>> c;
//        c.get(0).get(0);

        //String fileName = args.getFirst();
        //кидаю в итоге каждый скрипт в свою ячейку в лист
//        StringBuilder fileNameBuilder = new StringBuilder(fileName);
        while (true) {
            prev = script.indexOf("execute_script", prev);
            if (prev!=-1) {//нашли вхождение, с определенной позиции
                //execute_script filename
                //нужно получить имя файла как-то....
                int i = prev+15;
                prev++;//для поиска следующего execute_script
                fileName = "";
                while((sym = script.charAt(i++))!='\r'){
                    fileName += Character.toString(sym);
//                    fileNameBuilder.append(Character.toString(sym));
                    if(i == script.length())
                        break;
                }
//                fileName=fileNameBuilder.toString();
                try {
                    executeStringValidation(fileReader, fileReader.readFromFile(fileName), scripts);
                }
                catch(InputFileNotFoundException ex){
                    if(ex.toString().length()>28) throw ex;
                    throw new InputFileNotFoundException("Error. Input file " + fileName + " not found");
                }
            } else
                break;
        }
        //fileName = fileNameBuilder.toString();
        return scripts;
    }
}
