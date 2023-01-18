package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class Task {
    private String name;
    private String description;
    private final int id;
    private static int counter = 1;
    protected TaskType type;
    protected LocalDateTime date;
    protected RepeatType repeatType;
    public Task(String name, String description, LocalDateTime date, TaskType type, RepeatType repeatType) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.id = counter++;
        this.date = date;
        this.repeatType = repeatType;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public static int getCounter() {
        return counter;
    }
    public TaskType getType() {
        return type;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(TaskType type) {
        this.type = type;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public boolean appearsIn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "Название задачи: " + name + ", описание задачи: " + description + ", id: " + id + ", тип задачи: " + type +
                ", дата первого выполнения: " + date + ", тип повторяемости: " + repeatType;
    }
}