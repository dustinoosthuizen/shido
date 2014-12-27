package com.webward.shido.resources;


import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.webward.shido.dao.UserDao;
import com.webward.shido.dto.LoginDto;
import com.webward.shido.dto.LoginResponseDto;
import com.webward.shido.entities.User;
import com.webward.shido.utils.EncryptionUtil;
import com.webward.shido.utils.jwt.JWTUtil;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dustin on 2014/07/31.
 */
@Path("/login")
@Singleton
public class LoginResource {

    @Inject
    private UserDao userDao;
    @Inject
    private EncryptionUtil encryptionUtil;
    @Inject
    private JWTUtil jwtUtil;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public LoginResponseDto login(LoginDto loginDto) {

        LoginResponseDto loginResponse = new LoginResponseDto();
        User aUser = userDao.findByMail(loginDto.getEmail());
        if(aUser!=null) {
            if(aUser.getStatus().equals("active")) {
                boolean validEmail = encryptionUtil.digesterMatches(loginDto.getPassword(),aUser.getPassword());
                if (validEmail) {
                    loginResponse.setSuccess(true);
                    loginResponse.setMessage("successful");
                    try {
                        loginResponse.setToken(jwtUtil.generateToken(aUser.getEmail()));
                    } catch (Exception exc) {
                        loginResponse.setSuccess(false);
                        loginResponse.setMessage("Error occured on login:"+ exc.getMessage());
                    }
                } else {
                    loginResponse.setSuccess(false);
                    loginResponse.setMessage("Invalid username or password");
                }
            }else
            {
                loginResponse.setSuccess(false);
                loginResponse.setMessage("Please confirm registration by clicking on link sent to email");
            }
        }else
        {
            loginResponse.setSuccess(false);
            loginResponse.setMessage("Please register");
        }
        return loginResponse;

    }


}
