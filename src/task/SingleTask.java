package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class SingleTask extends Task{

    public SingleTask(String name, String description, LocalDateTime date, TaskType type, RepeatType repeatType) {
        super(name, description, date, type, repeatType);
        this.repeatType = RepeatType.SINGLE;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return this.date.toLocalDate().equals(localDate);
    }
}
