package com.ra.serviceImp;

import com.ra.dto.request.LoginRequest;
import com.ra.dto.response.LoginResponse;
import com.ra.mapper.LoginMapper;
import com.ra.model.User;
import com.ra.repository.UserRepository;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginMapper loginMapper;

    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//        //1. chuyen tu request sang entity
//        User user = loginMapper.mapperRequestToEntity(loginRequest);
//        //2. nhận kết quả sau khi gọi repnsitory là entity
//       Optional<User> userLogin = userRepository.login(user);
//      //entity ->response dể trả về cho controller
//       if(userLogin.isPresent()){
//           return  loginMapper.mapperEntityToResponse(userLogin.get()); //chuyen sang entity va tra ra optional
//       }
//        return null;
//    }
    public LoginResponse login(LoginRequest loginRequest) {
        //1. Chuyển từ request sang entity - user: entity - request: loginRequest
        User user = loginMapper.mapperRequestToEntity(loginRequest);
        //2. Nhận kết quả sau khi gọi repository là: entity
        Optional<User> userLogin = userRepository.login(user);
        //entity --> response để trả về cho controller
        if (userLogin.isPresent()){
            return loginMapper.mapperEntityToResponse(userLogin.get());
        }
        return null;
    }
}
