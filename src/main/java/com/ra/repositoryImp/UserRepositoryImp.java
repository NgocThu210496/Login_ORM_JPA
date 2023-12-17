package com.ra.repositoryImp;

import com.ra.model.User;
import com.ra.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository

public class UserRepositoryImp implements UserRepository {
    @PersistenceContext //lấy Context của Persistence ra sau đó lấy entityManager
    private EntityManager entityManager; //để lấy ra bộ quản lý các entity trong model
    @Override
    public List<User> findAll() { // from User: from từ class User, chứ k phải từ bảng nha!.   User.class: lấy từ class User.    getResultList(): lấy ra tất cả
        return entityManager.createQuery("select u from User as u", User.class).getResultList();
    }

    @Override
    public User findById(int userId) {
       User user = null;
        try { //u.id: id này là thuộc tính (private int id;) chứ k phải tên cột.  :id : là tham số truyền vào (mình tự đặt tên)
            user=entityManager.createQuery("select u from User as u where u.id= :id" ,User.class)
                    .setParameter("id",userId).getSingleResult(); //name:"id" nó là tên tham số này :id
                    // userId: nó là userId truyền vào ở đây  public User findById(int userId)
                    //getSingleResult(): lấy 1 dữ liệu duy nhất
        }catch (Exception ex){
            ex.printStackTrace(); //nếu k tìm thấy id thì vào đây
        }
        return user;
    }

    @Override
    public List<User> findByName(String fullName) {
        return entityManager.createQuery("select u from User as u where u.fullName like :name",User.class)
                .setParameter("name",fullName).getResultList(); //trả về một danh sách chứa kết quả của truy vấn
    }

    @Override
    public Optional<User> login(User userEntity) {
        User user = null;
        try {
            user=entityManager.createQuery("select  u from User as u where u.userName = :uName and u.password = :uPass and u.status = true",User.class)
                    .setParameter("uName",userEntity.getUserName())
                    .setParameter("uPass",userEntity.getPassword()).getSingleResult(); //lay 1 du lieu duy nhat
        }catch (Exception ex){
            ex.printStackTrace();
        }if (user==null){
            return Optional.ofNullable(user); //ofNullable là:tạo Optional cho giá trị null
        }
        return Optional.of(user); //nếu null: thì Optional.of trả về entity,nếu khác null thì nó trả về user
    }

    @Transactional // khi them, sua, xoa nó có thể gây lỗi or k thành công->ảnh hưởng đến DB, nên quản lý nó
    @Override
    public boolean register(User user) { //them moi
        try {
            entityManager.persist(user); //persist: insert
            return true; //neu thanh cong
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false ;
    }

    @Transactional
    @Override
    public boolean update(User user) {
        try {
            entityManager.merge(user); //merge: update
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean delete(int userId) {
        try {
            User user = findById(userId);
            entityManager.remove(user); //remove: vì nó xoá theo object, nên mình phải tạo đối tượng ở trên để lấy Id
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
