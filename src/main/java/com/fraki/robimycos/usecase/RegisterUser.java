package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.RegisterForm;
import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.data.entities.UserEntity;
import com.fraki.robimycos.data.mappers.UserMapper;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by bambo on 09.10.2017.
 */

@Component
public class RegisterUser extends UseCase<RegisterUser.Request, User> {

    private UsersDAO usersDAO;

    public RegisterUser(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public User response(Request request) {
        RegisterForm form = request.form;
        UserEntity entity = new UserEntity();
        entity.setLogin(form.getLogin());
        entity.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        entity.setPhone(form.getPhone());
        return UserMapper.convertToUser(usersDAO.save(entity));
    }

    public static class Request implements UseCase.Request {

        public Request(RegisterForm form) {
            this.form = form;
        }

        private RegisterForm form;
    }
}
