package TicketsBookingPlatform.Services;

import TicketsBookingPlatform.Repostories.UserRepository;
import TicketsBookingPlatform.db.UserLoginDTO;
import TicketsBookingPlatform.db.UserRegisterDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import TicketsBookingPlatform.db.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User convertFromDTO(UserRegisterDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return user;
    }


    public void registerCheck(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        userRepository.save(user);
    }


     public boolean loginCheck(UserLoginDTO user){
        userRepository.findByEmail(user.email());
        if(userRepository.findByEmail(user.email()).isPresent()) {
            String password = userRepository.findByEmail(user.email()).get().getPassword();
            return passwordEncoder.matches(user.password(), password);
        }
        else {
            return false;
        }
     }

}
