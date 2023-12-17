package com.ra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse { //trả ra kết quả và lưu vào cookie
    private int Id;
    private String userName;
    private boolean permission;
}
