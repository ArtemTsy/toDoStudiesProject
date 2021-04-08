package todolist.represent;

import org.springframework.format.annotation.DateTimeFormat;
import todolist.persist.entity.Todo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TodoRepr {

    private Long id;

    @NotEmpty
    private String description;

    private String username;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    public TodoRepr(Long id, @NotEmpty String description, String username, @NotNull LocalDate targetDate) {
        this.id = id;
        this.description = description;
        this.username = username;
        this.targetDate = targetDate;
    }

    public TodoRepr(Todo todo) {
        this.id = todo.getId();
        this.description = todo.getDescription();
        this.targetDate = todo.getTargetDate();
        this.username = todo.getUser().getUsername();
    }

    public TodoRepr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
