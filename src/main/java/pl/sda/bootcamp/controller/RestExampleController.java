package pl.sda.bootcamp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.bootcamp.model.Course;
import pl.sda.bootcamp.model.Message;
import pl.sda.bootcamp.repository.CourseRepository;
import pl.sda.bootcamp.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class RestExampleController {

    private final CourseService courseService;

    @GetMapping("/rest/hi")
    public ResponseEntity<String> sayHi() {
        return ResponseEntity.ok().body("Hi!");
    }

    @GetMapping("/rest/hello")
    public ResponseEntity<Message> sayHello() {
        return ResponseEntity.ok().body(new Message("Hello World!"));
    }

    @GetMapping("/rest/message")
    @ResponseBody
    public Message getMessage() {
        return new Message("Witaj świecie");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/rest/message")
    public void createMessage(@RequestBody final Message message) {
        System.out.println(message.getText());
    }
}
