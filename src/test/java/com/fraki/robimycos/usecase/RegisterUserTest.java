package com.fraki.robimycos.usecase;

import com.fraki.robimycos.data.businessmodels.RegisterForm;
import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.daos.UsersDAO;
import com.fraki.robimycos.usecase.base.UseCase;
import com.fraki.robimycos.usecase.base.UseCaseExecutor;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bambo on 09.10.2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegisterUserTest {

    private UseCaseExecutor useCaseExecutor;
    private UseCase<RegisterUser.Request, User> registerUser;

    private UsersDAO usersDAO;

    public RegisterUserTest(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Before
    public void setup() {
        useCaseExecutor = new UseCaseExecutor();
        registerUser = new RegisterUser(usersDAO);
    }

//    @Test(expected = MethodArgumentNotValidException.class)
    public void whenLoginRegisterFormInvalid_badLoginRegisterFormException() {
        RegisterForm form1 = new RegisterForm();
        useCaseExecutor.response(registerUser, new RegisterUser.Request(form1));
    }
}
