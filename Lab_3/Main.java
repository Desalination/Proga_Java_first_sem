package com.Lab_3;

import java.util.ArrayList;

public class Main {
/*Король рубинов сверкал красным глазом на окутанной ночной тьмою Земле, и Волшебник на Луне заметил его. Он уже совсем было отказался от
дальнейших поисков. Усталый и печальный, отдыхал он на краю кратера, а его черная пантера спала поодаль. Он сразу понял, что это сверкает
красным там, на Земле. Самый большой рубин на свете, Король рубинов, который он проискал не одну сотню лет! Не спуская с Земли горящего
взора, он вскочил, натянул перчатки и набросил на плечи плащ. Собранные в него драгоценные камни он попросту вытряхнул -- ведь его
интересовал один-единственный самоцвет и он рассчитывал меньше чем через полчаса держать его в своих руках.*/


    /*
    * */
    public static void main(String[] args)  throws UnableToFLy{
//        try {
            Gem Rubin = new Gem("king of rubies", Colors.RED, "largest among rubies");
            Gem Simple_stones = new Gem("gem", Colors.MULTY_COLORED, "simple");
            ArrayList<Gem> gems = new ArrayList<>();
            gems.add(Simple_stones);
            Seeker S_Volshebnick = new Seeker(LifeFormTypes.HUMAN, "Volshebnick", Locations.SPACE, Rubin, gems, 10);
            Pet Pantera = new Pet(LifeFormTypes.PANTERA, Colors.BLACK, Locations.SPACE, S_Volshebnick);
            SearchingRubin searchingRubin = new SearchingRubin();
            searchingRubin.start(S_Volshebnick);
            print();
//        }
//        catch(UnableToFLy UTF){
//            System.out.println(UTF.getMessage());
//        }
    }
    public static ArrayList<String> out = new ArrayList<>();
    private static void print() {
        for(String string: out) {
            System.out.print(string);
        }
    }

}