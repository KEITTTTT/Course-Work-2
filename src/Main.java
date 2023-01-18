import task.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Scanner;

public class Main {
    private final static DateTimeFormatter TASK_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm");
    private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public static void main(String[] args) {
        Service<Task> service = new Service<>();

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    scanner.reset();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            System.out.print("Введите название задачи, которую необходимо удалить: ");
                            String taskName = scanner.next();
                            service.removeTask(taskName);
                            break;
                        case 3:
                            Service<Task> service1 = new Service<>();
                            System.out.print("Введите дату, на которую хотите посмотреть задачи: ");
                            LocalDate date = LocalDate.parse(scanner.next(), FORMAT);
                            service1.getTasksForDay(LocalDate.from((TemporalAccessor) date));
                            System.out.println(service1.getTasksForDay(LocalDate.from(date)));
                            break;
                        case 4:
                            service.printTasks();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка:");
                }
            }
        }
        printMenu();
        Service<Task> service2 = new Service<>();
        service2.printTasks();
    }

    private static void inputTask(Scanner scanner) {
        String taskName = readString("Введите название задачи", scanner);
        System.out.print("Введите тип повторяемости задачи: ");
        RepeatType repeatType = readTaskRepeatType(scanner);
        String description = readString("Введите описание задачи", scanner);
//        System.out.print("Введите дату первого выполнения задачи: ");
        LocalDateTime date = readTaskDate(scanner);
        System.out.print("Выберите тип задачи: ");
        TaskType type = readTaskType(scanner);
        Service<Task> service = new Service<>();
        switch (repeatType) {
            case SINGLE:
                service.addTask(new SingleTask(taskName, description, date, type, repeatType));
                break;
            case DAILY:
                service.addTask(new DailyTask(taskName, description, date, type, repeatType));
                break;
            case WEEKLY:
                service.addTask(new WeeklyTask(taskName, description, date, type, repeatType));
                break;
            case MONTHLY:
                service.addTask(new MonthlyTask(taskName, description, date, type, repeatType));
                break;
            case ANNUAL:
                service.addTask(new AnnualTask(taskName, description, date, type, repeatType));
                break;
        }
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на нужную дату
                        4. Получить список всех задач
                        0. Выход
                        """
        );
    }

    public static TaskType readTaskType(Scanner scanner) {
        System.out.println("Выберите тип задачи\n1. Личная \n2. Рабочая");
        while (true) {
            try {
                System.out.println("Введите тип задачи:");
                int taskTypeSelector = scanner.nextInt();
                switch (taskTypeSelector) {
                    case 1:
                        return TaskType.PERSONAL;
                    case 2:
                        return TaskType.PROFESSIONAL;
                    default:
                        System.out.println("Введен некорректный тип задачи");
                }
            } catch (Exception e) {
                System.out.println("Введен некорректный тип задачи");
            }
        }
    }

    public static RepeatType readTaskRepeatType(Scanner scanner) {
        System.out.println("Повторяемость задачи\n1. Разовая \n2. Ежедневная \n3. Еженедельная \n4. Ежемесячная \n5. Ежегодная");
        while (true) {
            try {
                System.out.println("Введите тип повторяемости задачи:");
                int taskRepeatTypeSelector = scanner.nextInt();
                switch (taskRepeatTypeSelector) {
                    case 1:
                        return RepeatType.SINGLE;
                    case 2:
                        return RepeatType.DAILY;
                    case 3:
                        return RepeatType.WEEKLY;
                    case 4:
                        return RepeatType.MONTHLY;
                    case 5:
                        return RepeatType.ANNUAL;
                    default:
                        System.out.println("Введен некорректный тип повторяемости задачи");
                }
            } catch (Exception e) {
                System.out.println("Введен некорректный тип повторяемости задачи");
            }
        }
    }

    public static LocalDateTime readTaskDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату и время выполнения задачи в формате дд.мм.гггг чч.мм:");
                String dateTimeToken = scanner.nextLine();
                return LocalDateTime.parse(dateTimeToken, TASK_DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Дата и время введены неправильно, повторите ввод");
            }
        }
    }

    public static String readString(String message, Scanner scanner) {
        while (true) {
            try {
                System.out.println(message);
                String string = scanner.nextLine();
                return scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Необходимо заполнить поле");
            }
        }
    }
}