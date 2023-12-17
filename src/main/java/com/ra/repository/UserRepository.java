package com.ra.repository;

import com.ra.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll(); //lay tat ca thong tin
    User findById(int userId); //Optional: sử dụng khi không tìm ,khi nó trả về null
    List<User> findByName(String fullName);
    Optional<User> login(User user); //trả về thông tin user của login
    boolean register(User user); //dang ky
    boolean update(User user);
    boolean delete(int userId);


}
