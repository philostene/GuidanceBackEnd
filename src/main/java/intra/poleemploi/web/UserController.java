package intra.poleemploi.web;

import intra.poleemploi.dao.UserAppRepository;
import intra.poleemploi.entities.UserApp;
import intra.poleemploi.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserAppRepository userAppRepository;

    @PostMapping("/register")
    public UserApp register(@RequestBody UserForm userForm){ // données envoyées au format JSON
        return authService.saveUserApp(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }

    @GetMapping(value = "/adminUsers/{username}")
    public UserApp userAppByUsername(@PathVariable(name="username") String username){
        return userAppRepository.findUserByUsername(username);
    };
}
@Data
class UserForm {
    private String username;
    private String password;
    private String confirmedPassword;
}
