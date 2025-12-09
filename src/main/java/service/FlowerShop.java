package service;

import flower.Bouquet;
import flower.*;
import flower.Flower;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlowerShop {

    private static final Logger logger = LogManager.getLogger(FlowerShop.class);

    private List<Flower> catalog = new ArrayList<>(); // список доступних квітів
    private List<Bouquet> bouquets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    // назва файлу для серіалізації каталогу
    private final String FILE_NAME = "catalog.dat";

    public FlowerShop() {
        logger.info("Створення FlowerShop та завантаження каталогу");
        loadCatalogFromFile();
        if (catalog.isEmpty()) {
            logger.info("Каталог порожній, ініціалізуємо демо-дані");
            initDemoCatalog();
        }
    }

    // перегляд каталогу
    public void viewCatalog() {
        logger.info("Користувач переглядає каталог квітів. Поточний розмір: {}", catalog.size());
        System.out.println("=== Перегляд каталогу квітів ===");
        for (int i = 0; i < catalog.size(); i++) {
            Flower f = catalog.get(i);
            System.out.println((i + 1) + ". " + f.getName() + " (" + f.getClass().getSimpleName() + ") - " + f.getPrice() + " грн");
        }
    }

    // додати до каталогу
    public void addFlowerToCatalog() {
        logger.info("Користувач додає нову квітку до каталогу");
        System.out.println("\n--- Додавання нової квітки ---");
        System.out.println("Виберіть тип квітки:");
        System.out.println("1. Троянда (Rose)");
        System.out.println("2. Тюльпан (Tulip)");
        System.out.println("3. Ромашка (Chamomile)");
        int type = readInt("Ваш вибір: ");

        System.out.print("Назва: ");
        String name = scanner.nextLine();
        double price = readDouble("Ціна: ");
        double stem = readDouble("Довжина стебла (см): ");
        System.out.print("Колір: ");
        String color = scanner.nextLine();
        LocalDate date = readDate("Дата свіжості (РРРР-ММ-ДД): ");

        Flower newFlower = null;

        switch (type) {
            case 1: // Rose
                boolean thorns = readBoolean("Чи є шипи (true/false): ");
                System.out.print("Форма бутона: ");
                String bud = scanner.nextLine();
                newFlower = new Rose(name, price, stem, color, date, thorns, bud);
                break;
            case 2: // Tulip
                System.out.print("Форма пелюсток: ");
                String petal = scanner.nextLine();
                boolean isDouble = readBoolean("Махровий? (true/false): ");
                newFlower = new Tulip(name, price, stem, color, date, petal, isDouble);
                break;
            case 3: // Chamomile
                double core = readDouble("Розмір серцевини: ");
                int petalsCount = readInt("Кількість пелюсток: ");
                newFlower = new Chamomile(name, price, stem, color, date, core, petalsCount);
                break;
            default:
                logger.warn("Введено некоректний тип квітки: {}", type);
                System.out.println("Невірний тип квітки!");
                return;
        }

        catalog.add(newFlower);
        logger.info("Квітку '{}' додано. Новий розмір каталогу: {}", newFlower.getName(), catalog.size());
        System.out.println("Квітку успішно додано!");
    }

    // Створення букету
    public void createBouquet() {
        logger.info("Користувач створює новий букет");
        System.out.println("Введіть назву для нового букету: ");
        String name = scanner.nextLine();
        bouquets.add(new Bouquet(name));
        logger.info("Букет '{}' створено. Кількість букетів: {}", name, bouquets.size());
        System.out.println("Букет '" + name + "' створено.");
    }

    // Перегляд букетів
    public void viewAllBouquets() {
        logger.info("Користувач переглядає всі букети. Кількість: {}", bouquets.size());
        System.out.println("\n=== МОЇ БУКЕТИ ===");
        if (bouquets.isEmpty()) {
            System.out.println("У вас поки немає букетів.");
            return;
        }
        for (int i = 0; i < bouquets.size(); i++) {
            System.out.println((i + 1) + ". " + bouquets.get(i).toString());
        }
    }

    // редагування букету
    public void editBouquet() {
        logger.info("Користувач починає редагування букету");
        Bouquet b = selectBouquet();
        if (b == null) {
            logger.warn("Редагування букету скасовано: букет не обрано");
            return;
        }

        logger.info("Редагування букету '{}'", b.getName());
        System.out.println("\n=== НАПОВНЕННЯ БУКЕТУ '" + b.getName() + "' ===");

        // ЕТАП 1: ДОДАВАННЯ КВІТІВ
        boolean addingFlowers = true;
        while (addingFlowers) {
            viewCatalog(); // Показуємо, з чого можна вибрати
            System.out.println("0. -> ЗАВЕРШИТИ додавання квітів і перейти до аксесуарів");

            int flowerIndex = readInt("Введіть номер квітки для додавання: ");

            if (flowerIndex == 0) {
                addingFlowers = false; // Вихід з циклу квітів
            } else if (flowerIndex > 0 && flowerIndex <= catalog.size()) {
                // Отримуємо квітку з каталогу
                Flower flowerToAdd = catalog.get(flowerIndex - 1);

                // Питаємо кількість
                int quantity = readInt("Скільки штук '" + flowerToAdd.getName() + "' додати?: ");

                if (quantity > 0) {
                    for (int i = 0; i < quantity; i++) {
                        b.addFlower(flowerToAdd);
                    }
                    logger.info("До букету '{}' додано {} шт. квітки '{}'", b.getName(), quantity, flowerToAdd.getName());
                    System.out.println("--> Успішно додано " + quantity + " квіток.");
                }
            } else {
                logger.warn("Невірний номер квітки при редагуванні букету: {}", flowerIndex);
                System.out.println("Невірний номер квітки.");
            }
            System.out.println("-----------------------------------");
        }

        // ЕТАП 2: ДОДАВАННЯ АКСЕСУАРІВ
        System.out.println("\n=== ДОДАВАННЯ АКСЕСУАРІВ ===");
        while (true) {
            System.out.println("Бажаєте додати аксесуар до букету?");
            System.out.println("1. Так, додати новий аксесуар");
            System.out.println("0. Ні, завершити редагування");

            int choice = readInt("Ваш вибір: ");

            if (choice == 0) {
                break;
            } else if (choice == 1) {
                System.out.print("Назва аксесуару (напр. Стрічка, Папір): ");
                String accName = scanner.nextLine();
                double accPrice = readDouble("Ціна аксесару: ");

                b.addAccessory(new Accessory(accName, accPrice));
                logger.info("До букету '{}' додано аксесуар '{}' за ціною {}", b.getName(), accName, accPrice);
                System.out.println("--> Аксесуар '" + accName + "' додано.");
            }
        }

        logger.info("Букет '{}' оновлено. Нова ціна: {} грн", b.getName(), b.calculateTotalPrice());
        System.out.println("\nГотово! Букет '" + b.getName() + "' оновлено.");
        System.out.println("Загальна вартість: " + b.calculateTotalPrice() + " грн");
    }

    // Сортування букетів
    public void sortBouquet() {
        logger.info("Користувач запускає сортування букету за свіжістю");
        Bouquet b = selectBouquet();
        if (b != null) {
            logger.info("Сортується букет '{}'", b.getName());
            b.sortFlowersByFreshness();
            System.out.println("Квіти в букеті відсортовано за свіжістю!");
            b.getFlowers().forEach(f -> System.out.println(f.getName() + " - " + f.getFreshnessDate()));
        } else {
            logger.warn("Сортування скасовано: букет не обрано");
        }
    }

    // Пошук квітки за стеблом
    public void findFlowerInBouquet() {
        logger.info("Користувач запускає пошук квіток у букеті за довжиною стебла");
        Bouquet b = selectBouquet();
        if (b != null) {
            double min = readDouble("Мінімальна довжина стебла: ");
            double max = readDouble("Максимальна довжина стебла: ");
            logger.info("Пошук у букеті '{}' у діапазоні довжин {}–{} см", b.getName(), min, max);
            List<Flower> found = b.findFlowersByStemLenght(min, max);

            logger.info("Результат пошуку: знайдено {} квіток", found.size());
            System.out.println("Знайдено квіток: " + found.size());
            for (Flower f : found) {
                System.out.println("- " + f.getName() + " (" + f.getStemLength() + " см)");
            }
        } else {
            logger.warn("Пошук скасовано: букет не обрано");
        }
    }

    // збереження каталогу до файлу
    public void saveCatalogToFile() {
        logger.info("Користувач зберігає каталог у файл {}", FILE_NAME);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(catalog);
            logger.info("Каталог успішно збережено у файл {}", FILE_NAME);
            System.out.println("Каталог збережено.");
        } catch (IOException e) {
            logger.error("Помилка збереження каталогу у файл {}: {}", FILE_NAME, e.getMessage(), e);
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }

    // Видалення квітки з каталогу
    public void deleteFlowerFromCatalog() {
        logger.info("Користувач видаляє квітку з каталогу");
        viewCatalog();
        int index = readInt("Введіть номер квітки для видалення: ") - 1;
        if (index >= 0 && index < catalog.size()) {
            Flower removed = catalog.remove(index);
            logger.info("Квітку '{}' видалено. Новий розмір каталогу: {}", removed.getName(), catalog.size());
            System.out.println("Квітку видалено.");
        } else {
            logger.warn("Спроба видалення з некоректним індексом: {}", index + 1);
            System.out.println("Невірний номер.");
        }
    }

    private void loadCatalogFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            catalog = (List<Flower>) ois.readObject();
            logger.info("Каталог успішно завантажено з файлу {}", FILE_NAME);
        } catch (Exception e) {
            logger.error("Помилка читання файлу {}: {}", FILE_NAME, e.getMessage(), e);
            // Файлу немає або помилка - ігноруємо, створимо новий
        }
    }

    private void initDemoCatalog() {
        catalog.add(new Rose("Троянда Гран-Прі", 80, 60, "Червоний", LocalDate.now().minusDays(2), true, "Келих"));
        catalog.add(new Tulip("Тюльпан Голд", 40, 35, "Жовтий", LocalDate.now(), "Звичайний", false));
        catalog.add(new Chamomile("Ромашка Польова", 30, 25, "Білий", LocalDate.now().minusDays(1), 3.5, 20));
        logger.info("Демо-каталог ініціалізовано {} квітками", catalog.size());
    }

    private Bouquet selectBouquet() {
        viewAllBouquets();
        if (bouquets.isEmpty()) return null;
        int idx = readInt("Оберіть номер букету: ") - 1;
        if (idx >= 0 && idx < bouquets.size()) {
            return bouquets.get(idx);
        }
        logger.info("Невірний вибір букету: {}", idx + 1);
        System.out.println("Невірний вибір.");
        return null;
    }

    // перевіряємо чи введена саме цифра
    private int readInt(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.print("Це не число! Спробуйте ще раз: ");
            scanner.next();
        }
        int res = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return res;
    }

    private double readDouble(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextDouble()) {
            System.out.print("Це не число! Спробуйте ще раз: ");
            scanner.next();
        }
        double res = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        return res;
    }

    private boolean readBoolean(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextBoolean()) {
            System.out.print("Введіть true або false: ");
            scanner.next();
        }
        boolean res = scanner.nextBoolean();
        scanner.nextLine();
        return res;
    }

    private LocalDate readDate(String msg) {
        System.out.print(msg);
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.print("Невірний формат! Введіть РРРР-ММ-ДД: ");
            }
        }
    }
}
