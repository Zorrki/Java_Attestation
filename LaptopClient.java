import java.util.*;

public class LaptopClient {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>(Arrays.asList(
                new Laptop("Dell", 16, 512, "Windows", "Black"),
                new Laptop("HP", 8, 256, "Windows", "Silver"),
                new Laptop("Apple", 16, 512, "macOS", "Gray"),
                new Laptop("Asus", 8, 128, "Windows", "White"),
                new Laptop("Lenovo", 16, 1024, "Windows", "Black"),
                new Laptop("Acer", 8, 512, "Windows", "Blue"),
                new Laptop("MSI", 32, 1024, "Windows", "Red"),
                new Laptop("Samsung", 16, 256, "Windows", "Silver"),
                new Laptop("Sony", 8, 128, "Windows", "Black"),
                new Laptop("Toshiba", 4, 256, "Windows", "White")
        ));

        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ОЗУ");
        criteriaMap.put(2, "Объем ЖД");
        criteriaMap.put(3, "Операционная система");
        criteriaMap.put(4, "Цвет");

        Scanner scanner = new Scanner(System.in);
        Map<String, String> filterCriteria = new HashMap<>();

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            criteriaMap.forEach((key, value) -> System.out.println(key + " - " + value));

            System.out.print("Выберите критерий (или введите 0 для завершения): ");
            int criterion = scanner.nextInt();
            if (criterion == 0) {
                break;
            }
            if (!criteriaMap.containsKey(criterion)) {
                System.out.println("Неверный критерий! Попробуйте снова.");
                continue;
            }

            if (criterion == 3) {
                System.out.print("Введите операционную систему (например, Windows, macOS, Linux): ");
            } else if (criterion == 4) {
                System.out.print("Введите цвет (например, Black, Silver, White): ");
            } else {
                System.out.print("Введите минимальное значение для " + criteriaMap.get(criterion) + ": ");
            }
            String value = scanner.next();
            filterCriteria.put(criteriaMap.get(criterion), value);

            System.out.print("Хотите добавить еще один критерий? (yes/no): ");
            String moreCriteria = scanner.next();
            if (!moreCriteria.equalsIgnoreCase("yes")) {
                break;
            }
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filterCriteria);
        System.out.println("Ноутбуки, соответствующие критериям:");
        filteredLaptops.forEach(laptop -> {
            System.out.println("------------------------------");
            System.out.println("Бренд: " + laptop.getBrand());
            System.out.println("ОЗУ: " + laptop.getRam() + " ГБ");
            System.out.println("Объем ЖД: " + laptop.getStorage() + " ГБ");
            System.out.println("Операционная система: " + laptop.getOs());
            System.out.println("Цвет: " + laptop.getColor());
        });
        System.out.println("------------------------------");
        System.out.print("Хотите произвести повторный поиск по нужному критерию? (yes/no): ");
        String repeat = scanner.next();
        if (repeat.equalsIgnoreCase("yes")) {
            main(args); // Перезапуск программы для нового выбора критериев
        } else {
            System.out.println("Завершение программы.");
        }
    }

    private static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> filterCriteria) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Map.Entry<String, String> entry : filterCriteria.entrySet()) {
            String criterion = entry.getKey();
            String value = entry.getValue();

            switch (criterion) {
                case "ОЗУ":
                    filteredLaptops.removeIf(laptop -> laptop.getRam() < Integer.parseInt(value));
                    break;
                case "Объем ЖД":
                    filteredLaptops.removeIf(laptop -> laptop.getStorage() < Integer.parseInt(value));
                    break;
                case "Операционная система":
                    filteredLaptops.removeIf(laptop -> !laptop.getOs().equalsIgnoreCase(value));
                    break;
                case "Цвет":
                    filteredLaptops.removeIf(laptop -> !laptop.getColor().equalsIgnoreCase(value));
                    break;
            }
        }

        return filteredLaptops;
    }
}
