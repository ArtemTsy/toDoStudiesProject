package todolist.persist.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todolist.persist.entity.Todo;
import todolist.represent.TodoRepr;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<TodoRepr> findToDoByUser_Username(String username);
}
