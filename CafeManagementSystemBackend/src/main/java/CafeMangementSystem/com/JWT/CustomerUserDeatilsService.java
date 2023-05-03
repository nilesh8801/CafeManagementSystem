package CafeMangementSystem.com.JWT;

import CafeMangementSystem.com.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.image.PixelGrabber;
import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j


public class CustomerUserDeatilsService implements UserDetailsService {

    @Autowired
    UserDao userDao;


    // User class already available in spring sequrity also that why specify this way
    private CafeMangementSystem.com.POJO.User userDeatils;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDeatils = userDao.findByEmailId(username);
        if(!Objects.isNull(userDeatils))
            return new User(userDeatils.getEmail(),userDeatils.getPassword(),new ArrayList<>());
        else
            throw new UsernameNotFoundException("User not found.");
    }

    public CafeMangementSystem.com.POJO.User getUserDetails(){
        return userDeatils;
    }


}
