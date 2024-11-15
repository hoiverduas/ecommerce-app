package com.talataa.ecommerce_app.service.impl;

import com.talataa.ecommerce_app.entities.User;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user) ;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No found user with id : " + id));
    }

    @Override
    public User update(User user) {

        Optional<User> userOptional = Optional.ofNullable(findById(user.getId()));

        if (userOptional.isPresent()){

            User  existingUser = userOptional.get();


            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());

            return this.userRepository.save(existingUser);

        }else {
           throw new RuntimeException(" No Found ");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){

            this.userRepository.deleteById(id);
        }else {
            throw new RuntimeException("No found");
        }
    }


}
