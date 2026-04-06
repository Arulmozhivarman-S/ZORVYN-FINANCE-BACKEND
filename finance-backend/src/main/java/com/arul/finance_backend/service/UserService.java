package com.arul.finance_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arul.finance_backend.dtos.UserDto;
import com.arul.finance_backend.enums.UserRole;
import com.arul.finance_backend.exceptions.InvalidAccessException;
import com.arul.finance_backend.exceptions.ResourceNotFoundException;
import com.arul.finance_backend.model.User;
import com.arul.finance_backend.repository.UserRepository;


@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public boolean addUser(User user, Long currentUserId) {

        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Current User Not Found"));

        if (currentUser.getUserRole() != UserRole.ADMIN) {
            throw new InvalidAccessException("Only ADMIN can create users");
        }

        if (userRepository.existsById(user.getUserId())) {
            throw new RuntimeException("User already exists");
        }

        userRepository.save(user);
        return true;
    }


   public UserDto getUser(Long userId, Long currentUserId){

        User currentUser = userRepository.findById(currentUserId).orElseThrow(() -> new ResourceNotFoundException("Current User Not Found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        
        if (currentUser.getUserRole() != UserRole.ADMIN && !currentUser.getUserId().equals(userId)) { throw new InvalidAccessException("Access denied");
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserRole(user.getUserRole());
        userDto.setUserStatus(user.getUserStatus());

        return userDto;
    }

    public User updateUser(Long id, User updatedUser, Long currentUserId) {

        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Current User Not Found"));

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    
        if (currentUser.getUserRole() != UserRole.ADMIN &&
            !currentUser.getUserId().equals(id)) {
            throw new InvalidAccessException("You cannot update this user");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setUserRole(updatedUser.getUserRole());
        existingUser.setUserStatus(updatedUser.getUserStatus());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepository.save(existingUser);
    }

   public void deleteUser(Long id, Long currentUserId) {

        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Current User Not Found"));

        if (currentUser.getUserRole() != UserRole.ADMIN) {
            throw new InvalidAccessException("Only ADMIN can delete users");
        }

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }
    
}
