package com.Lab_5.ServerProject.Commands;

import com.Lab_5.ClientProject.Client.MyCommand;
import com.Lab_5.ClientProject.HumanBeing.HumanBeing;
import com.Lab_5.ServerProject.Collection.Data;
import com.Lab_5.ServerProject.Collection.For_counter;

import java.util.*;

public class Group_counting_by_has_toothpick {
    /**
     * сгруппировывает элементы коллекции по значению поля hasToothpick, вывести количество элементов в каждой группе
     * @return
     */
    public static String execute(Data data, MyCommand myCommand, LinkedList<String> commands){
        LinkedList<HumanBeing> tempHumans = new LinkedList<HumanBeing>(data.getdataSet());

        tempHumans.sort(new Comparator<HumanBeing>() {
            @Override
            public int compare(HumanBeing o1, HumanBeing o2) {
                return o1.compareToTooth(o2);
            }
        });
        //String response = Print.Show.execute(tempHumans, commands);

//        int counter = 0;
//        for(HumanBeing i : tempHumans){
//            if(i.getHasToothpick())
//                counter++;
//        }
        int countWithToothPick = data.count_if(new For_counter<HumanBeing>() {
            @Override
            public boolean is(HumanBeing for_comp) {
                return for_comp.getHasToothpick();
            }
        });
        String response = "";
        response += "Count of persons with tooth pick: " + countWithToothPick;

        if(countWithToothPick>0)
            response+="\nPersons with tooth pick:\n";

        ListIterator<HumanBeing> it = tempHumans.listIterator(0);
        int index = 0;
        while (index < countWithToothPick) {
            index++;
            response += (it.next().toString() + "\n");
        }
        response += "\nCount of persons without tooth pick: " + (data.getdataSet().size() - countWithToothPick);
        if(data.getdataSet().size() - countWithToothPick>0)
            response +="\nPersons without tooth pick:\n";
        while (it.hasNext())
            response += (it.next().toString()+"\n");

        commands.removeFirst();
        return response;

    }



}
