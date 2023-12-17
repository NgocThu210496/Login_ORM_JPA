package com.ra.mapper;

import com.ra.dto.request.LoginRequest;
import com.ra.dto.response.LoginResponse;
import com.ra.model.User;
import org.springframework.stereotype.Component;

@Component  //nó tương ứng với @vervice và  @repository
public class LoginMapper implements MapperGeneric<User, LoginRequest, LoginResponse>{ //public interface MapperGeneric<E,U,V>
    @Override
    public User mapperRequestToEntity(LoginRequest loginRequest) {
        //builder(): đang xây dựng các constractor gồm các tham số : userName và password
        //gọi cóntrusctor và truyền vào các đối số (getUserName(),getPassword()):là các thông tin của LoginRequest
        return User.builder().userName(loginRequest.getUserName())
                .password(loginRequest.getPassword()).build();
    }

    @Override
    public LoginResponse mapperEntityToResponse(User user) {
        return new LoginResponse(user.getId(), user.getUserName(), user.isPermission());
    }
}
