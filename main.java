
package OOP_HW_5;

import java.lang.reflect.InvocationHandler;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Spliterator;

import OOP_HW_5.RobotMap.Direction;

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
        System.out.println("Карта создана успешно!");
        System.out.println("ИГРАЕМ...");
        System.out.println(Direction.LEFT);

        while (true) {
            System.out.println();
            System.out.println(
                    "Список команд:\n" +
                            "1. create-robot m n (где m и n - расположение робота) - для создания робота.\n" +
                            "2. move-robot id - для перемещения робота на 1 шаг.\n" +
                            "3. change-direction id LEFT (TOP, RIGHT, BOTTOM, LEFT) - изменить направление движения нужного робота\n"
                            +
                            "4. show-all-robots - для показа списка существующих роботов и их позиций на карте.\n" +
                            "5. q - завершение программы!\n");
            RobotMap.Robot robot = null;
            String command = sc.nextLine();
            if (command.startsWith("create-robot")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    robot = map.createRobot(new Point(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1])));
                    System.out.println("Робот №" + robot.getId() + " успешно создан и помещен на карту.");
                } catch (PositionException e) {
                    System.out.println("Во время создания робота случилось исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else if (command.startsWith("move-robot")) {
                String[] split = command.split(" ");
                long robotId = Long.parseLong(split[1]);
                robot = map.getRobotById(robotId);

                if (robot == null) {
                    System.out.println("Робота с идентификатором " + robotId + "Не найден. Задайте другой id");
                } else {
                    try {
                        robot.move();
                        System.out.println("Робот №" + robot.getId() + " успешно двигается на шаг вперед.");
                        // System.out.println(robot);
                    } catch (PositionException e) {
                        System.out.println("Во время движения робота случилось исключение: " + e.getMessage() + "." +
                                " Попробуйте еще раз");
                    }
                }

            } else if (command.startsWith("change-direction")) {
                String[] split = command.split(" ");
                String[] argumenst = Arrays.copyOfRange(split, 1, split.length);
                long robotId = Long.parseLong(split[1]);
                String direction = argumenst[1];

                robot = map.getRobotById(robotId);

                if (robot == null) {
                    System.out.println("Робота с идентификатором " + robotId + "Не найден. Задайте другой id");
                } else {
                    try {
                        robot.changeDirection(direction);
                        System.out.println("Робот №" + robot.getId() + " сменил направление на " + direction);
                        System.out.println(robot);
                    } catch (DirectionException e) {
                        System.out.println("Во время смены направления случилось исключение: " + e.getMessage() + "." +
                                " Попробуйте еще раз");
                    }
                }

            } else if (command.equals("show-all-robots")) {
                System.out.println("Список всех ротботов на карте:");
                System.out.println();
                map.printAllRobots();

            } else if (command.equals("q")) {
                System.out.println("Завершение программы....");
                break;

            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        sc.close();
    }

}
