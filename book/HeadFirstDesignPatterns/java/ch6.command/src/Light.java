public class Light {
    String name;

    public Light() {
        this("");
    }

    public Light(String room){
        this.name = room;
    }

    public void on() {
        System.out.println( name + " Light를 On한다");
    }

    public void off() {
        System.out.println(name + " Light를 Off한다");
    }
}
