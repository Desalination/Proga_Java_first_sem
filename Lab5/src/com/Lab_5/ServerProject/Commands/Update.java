package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ClientProject.Exceptions.*;
import com.Lab_5.ClientProject.HumanBeing.Car;
import com.Lab_5.ClientProject.HumanBeing.Coordinates;
import com.Lab_5.ClientProject.HumanBeing.HumanBeing;
import com.Lab_5.ServerProject.Exceptions.NotFoundIdException;
import com.Lab_5.ServerProject.Server.Call_commands;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ClientProject.Support.InputConsole;
import com.Lab_5.ClientProject.Support.InputValidation;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedList;

import static com.Lab_5.ClientProject.Support.InputValidation.helpingMoodValidation;
import static com.Lab_5.ClientProject.Support.InputValidation.ifYESorNO;

public class Update {

    public static String execute(Data data, MyCommand myCommand, LinkedList<String> commands) throws IOException {
        String idStr = commands.get(0).split(" ")[1];
        long id = Long.parseLong(idStr);
        if (commands.size()== 1 ) {//если следующая строка это команда, ввод интерактивный
            commands.removeFirst();
            if(update(data.getdataSet(),myCommand, id, commands, true))
                return("Person["+ id + "] updated.\n" );
            else
                throw new NotFoundIdException("Not found element with this id for updating.");
        }

        else {//следующая строка возможно переменная
            if(!update(data.getdataSet(),myCommand, id, commands, false)) {
                System.out.println("Person with id=" + id + " don't exist.");
                throw new NotFoundIdException("Not found element by id=" + id + ". Further implementation of the script canceled.\n");
//                while(!Call_commands.isCommand(commands.getFirst().split(" ")[0])) {
//                    commands.removeFirst();
//                    if(commands.size() == 0)
//                        break;
//                }
            }
            else
                return("Person["+ id + "] updated.\n" );
        }
    }

    private static Boolean update(LinkedList<HumanBeing> humans, MyCommand myCommand, long id, LinkedList<String> commands, boolean interactive) {
        if(id < 0 ){
            return false;
        }

        else {
            for (HumanBeing i : humans) {
                if (id == i.getId()) {
                    if(interactive) {
                        System.out.println("Updating Person[" + id + "]:");

                        i.setName(myCommand.getTransfer().getName());
                        i.setCoordinates(myCommand.getTransfer().getCoordinates());
                        i.setRealHero(myCommand.getTransfer().getRealHero());
                        i.setHasToothpick(myCommand.getTransfer().getHasToothpick());
                        i.setImpactSpeed(myCommand.getTransfer().getImpactSpeed());
                        i.setSoundtrackName(myCommand.getTransfer().getSoundtrackName());
                        i.setMinutesOfWaiting(myCommand.getTransfer().getMinutesOfWaiting());
                        i.setMood(myCommand.getTransfer().getMood());
                        i.setCar(myCommand.getTransfer().getCar());
                        return true;
                    }
                    else{//script
                        commands.removeFirst();

                        if(commands.get(0).matches(InputValidation.USERNAME_PATTERN))
                            i.setName(commands.get(0));
                        else
                            throw new InvalidNameException();
                        commands.removeFirst();

                        i.setCoordinates(new Coordinates(Double.parseDouble(commands.get(0)), Integer.parseInt(commands.get(1))));
                        commands.removeFirst();
                        commands.removeFirst();

                        i.setCreationDate(ZonedDateTime.now());

                        Boolean bool = ifYESorNO(commands);
                        if(bool == null)
                            throw new InvalidRealHeroException();

                        i.setRealHero(Boolean.parseBoolean(commands.get(0)));
                        commands.removeFirst();

                        bool = ifYESorNO(commands);
                        if(bool == null)
                            throw new InvalidToothPickException();
                        i.setHasToothpick(Boolean.parseBoolean(commands.get(0)));
                        commands.removeFirst();

                        i.setImpactSpeed(Double.parseDouble(commands.get(0)));
                        commands.removeFirst();

                        i.setSoundtrackName(commands.get(0));
                        commands.removeFirst();

                        i.setMinutesOfWaiting(Double.parseDouble(commands.get(0)));
                        commands.removeFirst();

                        i.setMood(helpingMoodValidation(commands.get(0)));//может пропустить мууд
                        commands.removeFirst();

                        if (commands.get(0).equals("yes")) {
                            commands.removeFirst();

                            Car carObj = new Car();

                            if(commands.get(0).matches(InputValidation.CARNAME_PATTERN))
                                carObj.setName(commands.get(0));
                            else
                                throw new InvalidCarNameException();
                            commands.removeFirst();

                            bool = ifYESorNO(commands);
                            commands.removeFirst();
                            if(bool == null)
                                throw new InvalidCarCoolnessException();

                            carObj.setCool(bool);
                            i.setCar(carObj);
                            return true;
                        }
                        else if (commands.get(0).equals("no")) {
                            commands.removeFirst();
                            return true;
                        }
                        else
                            throw new ScriptContentException();
                    }
                }
            }
            return false;
        }
    }
}
