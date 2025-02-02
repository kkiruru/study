public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        PizzaStore nyStore = new NYPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

    }
}
