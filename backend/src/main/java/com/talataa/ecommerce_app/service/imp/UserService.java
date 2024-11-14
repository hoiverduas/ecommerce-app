package com.talataa.ecommerce_app.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talataa.ecommerce_app.dto.loginDto.RequestRegisterDto;
import com.talataa.ecommerce_app.dto.loginDto.ResponseRegisterDto;
import com.talataa.ecommerce_app.model.Product;
import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(IUserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public User createUser(User user) {

        user.setDateCreated(LocalDateTime.now());
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
            userExist.setDateUpdated(LocalDateTime.now());

            return this.userRepository.save(userExist);
        }else {
            throw new RuntimeException("Not found");
        }

    }

    @Override
    public ResponseRegisterDto register(RequestRegisterDto requestRegisterDto) {

        User user = mapToEntity(requestRegisterDto);
        this.userRepository.save(user);
        ResponseRegisterDto responseRegisterDto = mapToDto(user);
        return responseRegisterDto ;
    }

    @Override
    public void deleteUserById(Long id) {
        findUserById(id);
        this.userRepository.deleteById(id);
    }

    private ResponseRegisterDto mapToDto(User user){
        return this.objectMapper.convertValue(user,ResponseRegisterDto.class);
    }

    private User mapToEntity(RequestRegisterDto requestRegisterDto){
        return this.objectMapper.convertValue(requestRegisterDto,User.class);
    }
}
