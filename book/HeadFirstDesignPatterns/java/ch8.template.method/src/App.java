import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {

        Tea myTea = new Tea();
        myTea.prepareRecipe();

        CoffeeWithHook coffeeHook = new CoffeeWithHook();
        System.out.println("\n커피 준비중");
        coffeeHook.prepareRecipe();


        //Duck Sorting
        Duck[] ducks = {
                    new Duck("Daffy", 8),
                    new Duck("Dewey", 2),
                    new Duck("Howard", 7),
                    new Duck("Louie", 2),
                    new Duck("Donald", 10),
                    new Duck("Huey", 2)
        };

        System.out.println("\nBefore sorting: ");
        display(ducks);

        Arrays.sort(ducks);

        System.out.println("\nAfter sorting: ");
        display(ducks);
    }

    public static void display(Duck[] ducks) {
        for (int i = 0; i < ducks.length; i++) {
            System.out.println(ducks[i]);
        }
    }
}
