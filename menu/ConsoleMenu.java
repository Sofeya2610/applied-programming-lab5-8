package menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu implements MenuCommand {
    private String name;
    private String description;
    private Map<String, MenuCommand> menuItems = new LinkedHashMap<>();
    private Scanner scanner = new Scanner(System.in);

    // Конструктор для імені підменю
    public ConsoleMenu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Конструктор для головного меню
    public ConsoleMenu() {
        this("Головне меню", "Меню програми");
    }

    public void addMenuItem(String key, MenuCommand command) {
        // додає пункт меню у карту: "ключ" -> "команда"
        menuItems.put(key, command);
    }

    // Основний цикл роботи меню
    public void run() {
        // Нескінченний цикл роботи меню
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            if ("help".equalsIgnoreCase(choice)) {
                printHelp();
                System.out.print("Ваш вибір: ");
                choice = scanner.nextLine().trim();
            }

            if ("0".equals(choice)) {
                System.out.println("Вихід з " + name + ".");
                break;
            }

            MenuCommand command = menuItems.get(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Невірна команда. Введіть 'help' для підказки.");
            }
            System.out.println("-----------------------------------");
        }
    }
    // менюшку теж можна виконати як команду
    @Override
    public void execute() {
        run();
    }
    //повертаємо назву меню
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private void printMenu() {
        System.out.println("------" + name + "------");
        for (Map.Entry<String, MenuCommand> entry : menuItems.entrySet()) {
            System.out.println(entry.getKey() + "). " + entry.getValue().getName());
        }
        System.out.println("help. Показати підказку");
        System.out.println("0. Вийти з меню");
        System.out.print("Ваш вибір: ");
    }

    private void printHelp() {
        System.out.println("------ДОСТУПНІ КОМАНДИ------");
        for (Map.Entry<String, MenuCommand> entry : menuItems.entrySet()) {
            System.out.println(entry.getKey() + "). "
                    + entry.getValue().getName()
                    + " - " + entry.getValue().getDescription());
        }
        System.out.println("help - показати цю підказку");
        System.out.println("0 - вийти з меню");
        System.out.println("-----------------------------");
    }
}