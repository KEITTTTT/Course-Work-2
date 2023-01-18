package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{


    public DailyTask(String name, String description, LocalDateTime date, TaskType type, RepeatType repeatType) {
        super(name, description, date, type, repeatType);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return this.date.toLocalDate().equals(localDate) || this.date.toLocalDate().isBefore(localDate);
    }
}