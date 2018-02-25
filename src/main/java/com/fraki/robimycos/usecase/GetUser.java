package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.data.entities.UserEntity;
import com.fraki.robimycos.data.mappers.UserMapper;
import com.fraki.robimycos.exceptions.UserWithIdNotFoundException;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by bambo on 10.10.2017.
 */
@Component
public class GetUser extends UseCase<GetUser.Request, User> implements UserDetailsService {

    private UsersDAO usersDAO;

    public GetUser(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public User response(Request request) {
        User user;
        if(request.login == null) {
            user = UserMapper.convertToUser(usersDAO.findById(request.id).orElseThrow(() -> new UserWithIdNotFoundException(request.id)));
        } else {
            user = UserMapper.convertToUser(findUserByLogin(usersDAO, request.login));
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return findUserByLogin(usersDAO, s);
    }

    private static UserEntity findUserByLogin(UsersDAO usersDAO, String login) {
        return usersDAO.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }

    public static class Request implements UseCase.Request {
        private long id;
        private String login;

        public Request(long id) {
            this.id = id;
        }

        public Request(String login) {
            this.login = login;
        }

    }

}
