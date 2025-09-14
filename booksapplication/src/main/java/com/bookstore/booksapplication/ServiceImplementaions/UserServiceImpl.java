package com.bookstore.booksapplication.ServiceImplementaions;

import com.bookstore.booksapplication.DTO.SignupResponse;
import com.bookstore.booksapplication.DTO.signuprequest;
import com.bookstore.booksapplication.Models.user;
import com.bookstore.booksapplication.Repositories.UserRepository;
import com.bookstore.booksapplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SignupResponse registerUser(signuprequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        user user1 = new user();
        user1.setUsername(request.getUsername());
        user1.setEmail(request.getEmail());
        user1.setPassword(request.getPassword());
        user user2=userRepository.save(user1);
        return new SignupResponse(user2.getUserid(),user2.getEmail(),user2.getUsername());

    }

    @Override
    public List<SignupResponse> getAllUsers() {
        return userRepository.findAll().stream().map(b -> new SignupResponse(b.getUserid(),b.getEmail(),b.getUsername())).collect(Collectors.toList());
    }

    @Override
    public SignupResponse getUserById(Long userid) {
      user user1=userRepository.findById(userid) .orElseThrow(() -> new RuntimeException("User not found"));
      return new SignupResponse(user1.getUserid(),user1.getEmail(),user1.getUsername());
    }

    @Override
    public SignupResponse updateUser(Long id, signuprequest request) {
        user user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user updated = userRepository.save(user);
        return new SignupResponse(updated.getUserid(), updated.getUsername(), updated.getEmail());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
