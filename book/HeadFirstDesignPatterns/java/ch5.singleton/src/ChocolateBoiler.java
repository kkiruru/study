public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;

    private volatile static ChocolateBoiler uniqueInstance = null;

    private ChocolateBoiler() {
            empty = true;
            boiled = false;
    }

    public static ChocolateBoiler getInstance() {
        if (uniqueInstance == null) {
            synchronized (ChocolateBoiler.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ChocolateBoiler();
                }
            }
        }
        return uniqueInstance;
    }

   public void fill() {
         if (isEmpty()) {
            empty = false;
            boiled = false;
            System.out.println("초콜릿을 채운다");
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            //끓인 재료를 다음 단계로 넘김
            empty = true;
            System.out.println("초콜릿을 붓는다");
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            //재료를 끓임
            boiled = true;
            System.out.println("초콜릿을 끓인다");
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
