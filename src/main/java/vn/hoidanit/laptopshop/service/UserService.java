package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser =  this.userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleUpdateUser(User user) {
        User data = this.getUserById(user.getId());
        if (data != null) {
            data.setPhone(user.getPhone());
            data.setFullName(user.getFullName());
            data.setAddress(user.getAddress());
        }
    }

    public void handleDeleteUser(Long id) {
        System.out.println(id);
        this.userRepository.deleteById(id);
    }
}
