
package OOP_HW_5;

import java.lang.reflect.InvocationHandler;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1.
        // Карта с каким-то размером nxm.
        // На ней можно создать робов, указывая начальное положение.
        // Если начальное положение некорректное ИЛИ эта позиция занята другим робом, то
        // кидаем исключение.
        // Робот имеет направление (вверх, вправо, вниз, влево). У роботов можно менять
        // направление и передвигать их на 1 шаг вперед
        // 2.
        // Написать контроллер к этому коду, который будет выступать посредником между
        // консолью (пользователем) и этой игрой.
        // (0,0) ------------------ (0, m)
        // ...
        // (n, 0) ----------------------- (n, m)

        // Robot, Map, Point

        // Домашнее задание:
        // Реализовать чтение команд с консоли и выполнить их в main методе
        // Список команд:
        // create-map 3 5 -- РЕАЛИЗОВАНО!
        // create-robot 2 7
        // move-robot id
        // change-direction id LEFT

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
        System.out.println("Карта создана успешно!");
        System.out.println("ИГРАЕМ...");

        // Scanner sc2 = new Scanner(System.in);
        System.out.println(
                "Список команд:\n" +
                        "1. create-robot m n (где m и n - расположение робота) для создания робота.\n" +
                        "2. move-robot id - для перемещения робота на 1 шаг.\n" +
                        "3. change-direction id LEFT - изменить направление движения нужного робота\n");
        RobotMap.Robot robot = null;
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-robot")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    robot = map.createRobot(new Point(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1])));
                    System.out.println("Робот №" + robot.getId() + " успешно создан и помещен на карту.");
                    //System.out.println(robot); - выводит в консоль робота в формате: "[id] [m,n]"
                    break;
                } catch (PositionException e) {
                    System.out.println("Во время создания робота случилось исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else if (command.startsWith("move-robot")) {
                String[] split = command.split(" ");
                long robotId = Long.parseLong(split[1]);

                // Если robots в RobotMap содержит id равный полученному, то двигаем нашего
                // робота. Иначе пишем об отсутствии робота с таким id.

            } else if (command.startsWith("change-direction")) {
                String[] split = command.split(" ");
                long robotId = Long.parseLong(split[1]);
                
                // Если robots в RobotMap содержит id равный полученному, то меняем роботу направление.
                //Иначе пишем, что такого робота нет

            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        // map.getAllRobots();  - выводит список всех роботов в формате: "ID: ххх, Position: [m,n]"

        sc.close();
    }

}
