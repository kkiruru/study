public class NoCommand implements Command {

    @Override
    public void execute() {
        System.out.println("아무 기능이 없음");
    }

    @Override
    public void undo() {
        System.out.println("되돌릴 상황이 없음");
    }

}
