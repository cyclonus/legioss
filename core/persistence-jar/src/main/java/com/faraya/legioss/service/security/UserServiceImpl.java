package com.faraya.legioss.service.security;

import com.faraya.legioss.core.dao.security.ICredentialDAO;
import com.faraya.legioss.core.dao.security.IUserDAO;
import com.faraya.legioss.core.entity.security.Credential;
import com.faraya.legioss.core.entity.security.AbstractUser;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.security.PasswordHash;
import com.faraya.legioss.util.EmailValidator;
import com.faraya.legioss.util.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by fabrizzio on 9/4/15.
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    ICredentialDAO credentialDAO;

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        return (userDAO.findByEmail(email) != null);
    }

    @Override
    public boolean isValidEmail(String email) {
        EmailValidator validator = new EmailValidator();
        return validator.validate(email);
    }

    @Override
    public boolean isValidPassword(String password) {
        PasswordValidator validator = new PasswordValidator();
        return validator.validate(password);
    }


    @Transactional
    @Override
    public User createUser(String email, String firstName, String lastName, String password) throws Exception {

        boolean validEmailAddress = isValidEmail(email);
        if(!validEmailAddress) {
           throw new Exception("Invalid e-mail address");
        }

        boolean validPassword = isValidPassword(password);
        if(!validPassword){
            throw new Exception("Password does not match policy");
        }

        boolean emailAlreadyInUse = isEmailAlreadyInUse(email);
        if(emailAlreadyInUse){
            throw new Exception("e-mail address, already in use");
        }

        User user = new User(email, firstName, lastName);
        userDAO.save(user);
        String hashedPassword = PasswordHash.createHash(password);
        Credential credential = new Credential(user, hashedPassword);
        credentialDAO.save(credential);
        return user;

    }

    public User authenticate(String email, String password) throws Exception {
        User user = userDAO.findByEmail(email);
        if(user != null && user.getStatus() == User.Status.ACTIVE){
           Credential credential = credentialDAO.findByOwnerId(user.getId());
           boolean match = PasswordHash.validatePassword(password,PasswordHash.createHash(credential.getPassword()));
           if(match){
              return user;
           }
        }
        return null;
    }

}
