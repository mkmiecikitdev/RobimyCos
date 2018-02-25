package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.data.mappers.UserMapper;
import com.fraki.robimycos.usecase.base.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by bambo on 10.10.2017.
 */

@Component
public class GetUsers extends UseCase<GetUsers.Request, List<User>> {

    private UsersDAO usersDAO;

    public GetUsers(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public List<User> response(Request request) {
        return UserMapper.convertToUserList(usersDAO.findAllOthers(request.login));
    }

    public static class Request implements UseCase.Request {
        private String login;

        public Request(String login) {
            this.login = login;
        }
    }

}
