package com.ra.service;

import com.ra.dto.request.LoginRequest;
import com.ra.dto.response.LoginResponse;

public interface UserService {
   LoginResponse login(LoginRequest loginRequest);
}
