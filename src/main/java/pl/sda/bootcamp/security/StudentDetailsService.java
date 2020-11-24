/*
package pl.sda.bootcamp.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.bootcamp.model.Student;
import pl.sda.bootcamp.repository.StudentRepository;

@Service
@AllArgsConstructor
public class StudentDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Student student = this.studentRepository.findByUsername(s);
        return new StudentDetails(student);
    }
}
*/
