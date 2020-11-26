package pl.sda.bootcamp.model;

import lombok.*;
import org.aspectj.bridge.IMessage;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Brak nazwy kursu")
    private String name;

    private String city;

    @NotNull(message = "Brak daty rozpoczęcia")
    @Future(message = "Data rozpoczęcia nie może być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beginDate;

    @NotNull(message = "Brak daty zakończenia")
    @Future(message = "Data zakończenia nie może być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @NotNull(message = "Nie wybrano trybu")
    @Enumerated(EnumType.STRING)
    private Mode mode;

    @NotNull(message = "Proszę podać cenę kursu")
    @Min(value = 1, message = "Cena musi być dodatnia")
    private Integer price;

    @ManyToOne
    private User teacher;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public void addUser(User user) {
        user.getCourses().add(this);
        this.users.add(user);
    }

    public void removeUser(User user) {
        user.getCourses().remove(this);
        this.users.remove(user);
    }

    public void removeAllUsers() {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            it.next().getCourses().remove(this);
            it.remove();
        }
    }
}
