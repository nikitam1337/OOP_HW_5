package OOP_HW_5;

public interface CommandHandler {

    String commandName();

    void handleCommand(RobotMap map, String[] args);

}
