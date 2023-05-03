package CafeMangementSystem.com.dao;

import CafeMangementSystem.com.POJO.User;
import CafeMangementSystem.com.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;



public interface  UserDao extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUser();


    //write query
    //pass here two thing id and status
    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);


    List<String> getAllAdmin();


  User findByEmail(String email);


}
