package com.fraki.robimycos.usecase;


import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.data.entities.UserEntity;
import com.fraki.robimycos.exceptions.UserWithIdNotFoundException;
import com.fraki.robimycos.exceptions.UserWithLoginNotFoundException;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by bambo on 10.10.2017.
 */

@Component
public class LoginFirebase extends UseCase<LoginFirebase.Request, String> {

    private UsersDAO usersDAO;

    public LoginFirebase(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Transactional
    @Override
    public String response(Request request) {
        UserEntity userEntity = usersDAO.findByLogin(request.login).orElseThrow(() -> new UserWithLoginNotFoundException(request.login));
        userEntity.setTokenFirebase(request.token);
        return request.token;
    }

    public static class Request implements UseCase.Request{
        private String login;
        private String token;

        public Request(String login, String token) {
            this.login = login;
            this.token = token;
        }
    }
}
