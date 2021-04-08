package todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import todolist.controllers.exeptions.ResourceNotFoundExeption;
import todolist.represent.TodoRepr;
import todolist.service.TodoService;

import java.util.List;

import static todolist.service.UserService.getCurrentUser;

@Controller
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String indexPage(Model model){
        List<TodoRepr> todos = getCurrentUser()
                .map(todoService::findTodoByUser_Username)
                .orElseThrow(IllegalAccessError::new);
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/todo/{id}")
    public String todoPage(@PathVariable("id") Long id, Model model){
        TodoRepr todoRepr = todoService.findById(id).orElseThrow(ResourceNotFoundExeption::new);
        model.addAttribute("todo", todoRepr);
        return "todo";
    }

    @GetMapping("/todo/create")
    public String createTodoPage(Model model){
        model.addAttribute("todo", new TodoRepr());
        return "todo";
    }

    @PostMapping("/todo/create")
    public String createTodoPost(@ModelAttribute("todo") TodoRepr todoRepr){
        todoService.save(todoRepr);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(Model model, @PathVariable Long id){
        todoService.delete(id);
        return "redirect:/";
    }
}
