package com.Lab_4;

import java.util.ArrayList;

public class Main {
/*Уж полночь прошла, как вдруг весь сад озарился розовым светом. Танцы приостановились: все решили, что это какой-то новый вид фейерверка.
Но это просто Тофсла и Вифсла открыли свой чемодан. Король рубинов, сверкая, лежал на лужайке, прекрасный как никогда. Все огни, фонари и
даже сама луна померкли, потеряли свой блеск. В благоговейном молчании пылающий самоцвет обступала все более густая и многочисленная толпа.
<локал в методе и статик может быть создан доп-но отдельно>

Король рубинов сверкал красным глазом на окутанной ночной тьмою Земле, и Волшебник на Луне заметил его.
Он уже совсем было отказался от дальнейших поисков. Усталый и печальный, отдыхал он на краю кратера, а его черная пантера спала поодаль.
Он сразу понял, что это сверкает красным там, на Земле. Самый большой рубин на свете, Король рубинов, который он проискал не одну сотню
лет! Не спуская с Земли горящего взора, он вскочил, натянул перчатки и набросил на плечи плащ. Собранные в него драгоценные камни он
попросту вытряхнул -- ведь его интересовал один-единственный самоцвет и он рассчитывал меньше чем через полчаса держать его в своих руках.
Пантера с хозяином на спине поднялась в воздух.*/

    public static void main(String[] args)  throws UnableToLie, IllegalSeeker{

        try {

             SearchingRubin searchingRubin = new SearchingRubin();
             searchingRubin.EarthScript();

             Seeker S_Volshebnick = new Seeker(
                     LifeFormTypes.HUMAN,
                     "Volshebnick",
                     Locations.SPACE,
                     new Pet(LifeFormTypes.PANTERA, Colors.BLACK, Locations.SPACE),
                     new Gem("King of rubies", Colors.RED, "largest among rubies"),
                     new Seeker.Bag<Gem>(
                             new Gem("gem", Colors.MULTY_COLORED, "simple")),
                     10);
             searchingRubin.start(S_Volshebnick);
//             Checking of exceptions
//             Seeker S_Volshebnick_with_ex = new Seeker(LifeFormTypes.HUMAN, "Volshebnick", Locations.SPACE, new Pet(LifeFormTypes.PANTERA, Colors.BLACK, Locations.SPACE), Simple_stones, gems, 10);
//             searchingRubin.start(S_Volshebnick_with_ex);
//             S_Volshebnick.action.lie_next_to_smth("smth");
//             S_Volshebnick.action.lie_next_to_smth("smth");
         print();
        }
        catch(Exception ex){
                System.out.print(ex);
        }
    }

    public static ArrayList<String> out = new ArrayList<>();
    private static void print() {
        for(String string: out) {
            System.out.print(string);
        }
    }

}