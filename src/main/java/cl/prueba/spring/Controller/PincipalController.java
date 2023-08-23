package cl.prueba.spring.Controller;

import cl.prueba.spring.Controller.request.CreateUserDTO;
import cl.prueba.spring.models.ERole;
import cl.prueba.spring.models.RoleEntity;
import cl.prueba.spring.models.UserEntity;
import cl.prueba.spring.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PincipalController {

    @Autowired
    private UserRepository userRepository;

@GetMapping("/hello")
    public String Hola(){
        return "Hola Mundo not Secured";
    }

    @GetMapping("/helloSecured")
    public String HolaSecured(){
        return "Hola Mundo Secured";
    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

       Set<RoleEntity> roles = createUserDTO.getRoles().stream()
               .map(role -> RoleEntity.builder()
                       .Name(ERole.valueOf(role))
                       .build())
               .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return  ResponseEntity.ok(userEntity);
    }
@DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
    userRepository.deleteById(Long.parseLong(id));

    return "Se ha borrado el user con id".concat(id);
    }
}