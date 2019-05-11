package ea.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    @Autowired
    private CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            throw new RuntimeException("Could not fetch security context to determine user");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(username);
        return userRepository.findByUsername(username);
    }

}
