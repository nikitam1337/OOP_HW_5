
package OOP_HW_5;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите команду 'create-map m n'(где m и n -размеры карты) для создания карты:");
        RobotMap map = null;
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("При создании карты возникло исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        List<CommandHandler> handlers = List.of(
                new ChangeDirectionCommandHandler(),
                new CreateRobotCommandHandler(),
                new MoveRobotCommandHandler(),
                new ShowRobotsCommandHandler()
        );
        CommandManager commandManager = new CommandManager(map, handlers);

        System.out.println("Карта создана успешно!");
        System.out.println("ИГРАЕМ...");

        while (true) {
            System.out.println();
            System.out.println(
                    "Список команд:\n" +
                            "1. create-robot m n (где m и n - расположение робота) - для создания робота.\n" +
                            "2. move-robot id - для перемещения робота на 1 шаг.\n" +
                            "3. change-direction id LEFT (TOP, RIGHT, BOTTOM, LEFT) - изменить направление движения нужного робота\n"
                            +
                            "4. show-all-robots - для показа списка существующих роботов и их позиций на карте.\n" +
                            "5. exit - завершение программы!\n");

            String command = sc.nextLine();
            if (command.startsWith("exit")) {
                System.out.println("Завершение программы....");
                break;
            } else{
                commandManager.handleCommand(command);
            }
        }
    }
}
