package com.talataa.ecommerce_app.service;


import com.talataa.ecommerce_app.entities.User;

import java.util.List;

public interface IUserService {

  User save(User user);
  List<User> findAll();
  User findById(Long id);
  User update(User ser);
  void deleteById(Long id);
}
