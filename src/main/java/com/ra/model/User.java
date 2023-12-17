package com.ra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor //sử dụng lombok. cái này là tạo constructor không có tham số
@AllArgsConstructor //constructor có tất cả tham số
@Data //setter and getter
@Builder //xây dựng constructor theo ý mình
@Table(name = "User")
public class User {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id tu dong tang
    @Column(name = "user_id") //cột tương ứng với biến private int id; có tên là cột name = "user_id"
    private int id;
    //columnDefinition = "varchar(50)": kieu du lieu, nullable = false: not null, unique = true: duy nhat
    @Column(name = "user_name",columnDefinition = "varchar(50)",nullable = false, unique = true)
    private String userName;
    @Column(name = "password",columnDefinition = "varchar(50)", nullable = false)
    private String password;
    @Column(name = "fullName",columnDefinition = "varchar(100)", nullable = false) //full_name: default jpa tạo
    private String fullName;
    @Column(columnDefinition = "varchar(100)", nullable = false,unique = true) //không có name: bởi vì muốn tạo tên cột giống với tên thuộc tính private String email này
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated;
    //Mặc định có quyền user (user:0 - admin:1)
    private boolean permission=false;
    @Column(name = "user_status")
    private boolean status=true;


}
