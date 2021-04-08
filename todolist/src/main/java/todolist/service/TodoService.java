package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.persist.entity.Todo;
import todolist.persist.repo.TodoRepository;
import todolist.persist.repo.UserRepository;
import todolist.represent.TodoRepr;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static todolist.service.UserService.getCurrentUser;

@Service
@Transactional
public class TodoService {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Optional<TodoRepr> findById(Long id) {
        return todoRepository.findById(id)
                .map(TodoRepr::new);
    }

    public List<TodoRepr> findTodoByUser_Username(String username){
        return todoRepository.findToDoByUser_Username(username);
    }

    public void save(TodoRepr toDoRepr) {
        getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .ifPresent(user -> {
                    Todo todo = new Todo();
                    todo.setId(toDoRepr.getId());
                    todo.setDescription(toDoRepr.getDescription());
                    todo.setTargetDate(toDoRepr.getTargetDate());
                    todo.setUser(user);
                    todoRepository.save(todo);
                });
    }

    public void delete(Long id) {
        todoRepository.findById(id)
                .ifPresent(todo -> todoRepository.delete(todo));
    }
}
