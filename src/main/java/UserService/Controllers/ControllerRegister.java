package TicketsBookingPlatform.Controllers;


import TicketsBookingPlatform.Services.UserService;
import TicketsBookingPlatform.db.UserLoginDTO;
import TicketsBookingPlatform.db.UserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static TicketsBookingPlatform.Services.JWTTokenCreateService.generateToken;

@RestController
@RequestMapping("/auth")
public class ControllerRegister {
    private final UserService userService;
    ControllerRegister(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO user){

        try {
            userService.registerCheck(userService.convertFromDTO(user));
        } catch (RuntimeException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO user){
        try {
            userService.loginCheck(user);
        } catch (RuntimeException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.ok().body(generateToken(user.email()));
    }


}
