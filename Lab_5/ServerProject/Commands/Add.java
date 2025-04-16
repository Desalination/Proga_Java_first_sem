package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ClientProject.Exceptions.*;
import com.Lab_5.ClientProject.HumanBeing.Car;
import com.Lab_5.ClientProject.HumanBeing.Coordinates;
import com.Lab_5.ClientProject.HumanBeing.HumanBeing;
import com.Lab_5.ServerProject.Exceptions.InputValueNotYesOrNo;
import com.Lab_5.ServerProject.Exceptions.InvalidCarException;
import com.Lab_5.ServerProject.Server.Call_commands;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Support.InputConsole;
import com.Lab_5.ClientProject.Support.InputValidation;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedList;

import static com.Lab_5.ClientProject.Support.InputValidation.helpingMoodValidation;
import static com.Lab_5.ClientProject.Support.InputValidation.ifYESorNO;

public class Add implements Command {

    public void undo(Data data){data.getdataSet().removeLast();}

    public static String execute(Data data, MyCommand myCommand, LinkedList<String> commands) throws IOException {

        if (commands.size() == 1 /*|| Call_commands.isCommand(commands.get(1).split(" ")[0])*/) {//если следующая строка это команда, ввод интерактивный
            //args:command name and nothing more
            String response = addClientPrepared(myCommand.getTransfer(), data);
            commands.removeFirst();
            return response;
        }
        else {//args: comma
            return addServerPrepared(data, commands);
        }
//        System.out.println("New HumanBeing added.");
    }

    private static String addClientPrepared(HumanBeing newHuman, Data data){
        data.add(newHuman);
        return "New\n" + newHuman.toString() + "\nadded.\n";
    }
//    private static void add_interactive(Data data) {
//        System.out.println("Adding new HumanBeing: ");
//        data.add(
//                InputConsole.inputName(),
//                InputConsole.inputCoordinates(),
//                InputConsole.inputRealHero(),
//                InputConsole.inputHasToothPick(),
//                InputConsole.inputImpactSpeed(),
//                InputConsole.inputSoundtrackName(),
//                InputConsole.inputMinutesOfWaiting(),
//                InputConsole.inputMood(),
//                InputConsole.inputCar()
//        );
//    }

    private static String addServerPrepared(Data data, LinkedList<String> scripts) throws IOException {
        scripts.remove(0);

        HumanBeing hum = new HumanBeing();

        if(scripts.get(0).matches(InputValidation.USERNAME_PATTERN))
            hum.setName(scripts.get(0));
        else
            throw new InvalidNameException();
        scripts.removeFirst();

        hum.setCoordinates(new Coordinates(Double.parseDouble(scripts.get(0)), Integer.parseInt(scripts.get(1))));
        scripts.removeFirst();
        scripts.removeFirst();

        hum.setCreationDate(ZonedDateTime.now());

        Boolean bool = ifYESorNO(scripts);
        if(bool == null)
            throw new InvalidRealHeroException();

        hum.setRealHero(bool);
        scripts.removeFirst();

        bool = ifYESorNO(scripts);
        if(bool == null)
            throw new InvalidToothPickException();
        hum.setHasToothpick(bool);
        scripts.removeFirst();

        hum.setImpactSpeed(Double.parseDouble(scripts.get(0)));
        scripts.removeFirst();

        hum.setSoundtrackName(scripts.get(0));
        scripts.removeFirst();

        hum.setMinutesOfWaiting(Double.parseDouble(scripts.get(0)));
        scripts.removeFirst();

        hum.setMood(helpingMoodValidation(scripts.get(0)));//может пропустить мууд
        scripts.removeFirst();

        if (scripts.get(0).equals("yes")) {
            scripts.removeFirst();

            Car carObj = new Car();

            if(scripts.get(0).matches(InputValidation.CARNAME_PATTERN))
                carObj.setName(scripts.get(0));
            else
                throw new InvalidCarNameException();
            scripts.removeFirst();

            bool = ifYESorNO(scripts);
            scripts.removeFirst();
            if(bool == null)
                throw new InvalidCarCoolnessException();

            carObj.setCool(bool);
            hum.setCar(carObj);
        }
        else if (scripts.get(0).equals("no"))
            scripts.removeFirst();
        else
            throw new InvalidCarException();
        data.add(hum);
        return("New \n{"+hum.toString()+"}\nadded.\n");
    }
}
