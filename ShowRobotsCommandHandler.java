package OOP_HW_5;

public class ShowRobotsCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "show-all-robots";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        System.out.println("Список всех роботов на карте:");
        System.out.println();
        map.printAllRobots();
    }
}