package com.talataa.ecommerce_app.service.imp;

import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IUserService;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository
                .save(user);
    }

    @Override
    public Iterable<User> findAllUser() {
        return this.userRepository
                .findAll();
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public User updateUser(User user) {

        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()){
            User userExist = optionalUser.get();

            userExist.setUsername(user.getUsername());
            userExist.setFirstName(user.getFirstName());
            userExist.setLastName(user.getLastName());
            userExist.setEmail(user.getEmail());
            userExist.setUserType(user.getUserType());
            userExist.setAddress(user.getAddress());
            userExist.setCellphone(user.getCellphone());
            userExist.setPassword(user.getPassword());

            return this.userRepository.save(userExist);
        }else {
            throw new RuntimeException("Not found");
        }

    }

    @Override
    public void deleteUserById(Long id) {
        findUserById(id);
        this.userRepository.deleteById(id);
    }
}
