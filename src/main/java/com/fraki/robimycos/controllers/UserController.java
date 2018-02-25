package com.fraki.robimycos.controllers;

import com.fraki.robimycos.config.security.TokenAuthenticationService;
import com.fraki.robimycos.data.businessmodels.RegisterForm;
import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.usecase.GetUser;
import com.fraki.robimycos.usecase.GetUsers;
import com.fraki.robimycos.usecase.LoginFirebase;
import com.fraki.robimycos.usecase.RegisterUser;
import com.fraki.robimycos.usecase.base.UseCase;
import com.fraki.robimycos.usecase.base.UseCaseExecutor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by bambo on 09.10.2017.
 */

@RestController
public class UserController {

    private UseCase<RegisterUser.Request, User> registerUser;
    private UseCase<GetUser.Request, User> getUser;
    private UseCase<GetUsers.Request, List<User>> getUsers;
    private UseCase<LoginFirebase.Request, String> loginFirebase;

    private UseCaseExecutor useCaseExecutor;

    public UserController(UseCase<RegisterUser.Request, User> registerUser, UseCase<GetUser.Request, User> getUser, UseCase<GetUsers.Request, List<User>> getUsers, UseCase<LoginFirebase.Request, String> loginFirebase, UseCaseExecutor useCaseExecutor) {
        this.registerUser = registerUser;
        this.getUser = getUser;
        this.getUsers = getUsers;
        this.loginFirebase = loginFirebase;
        this.useCaseExecutor = useCaseExecutor;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody @Valid RegisterForm registerForm, HttpServletResponse response) {
        User user = useCaseExecutor.response(registerUser, new RegisterUser.Request(registerForm));
        TokenAuthenticationService.addAuthentication(response, user.getLogin());
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginUser(HttpServletResponse response) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(response);
        return useCaseExecutor.response(getUser, new GetUser.Request(login));
    }

    @GetMapping("/firebase/login/{token}")
    public String updateToken(HttpServletRequest request, @PathVariable String token) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return useCaseExecutor.response(loginFirebase, new LoginFirebase.Request(login, token));
    }

    @GetMapping("/users")
    public List<User> getUsers(HttpServletRequest request) {
        String login = TokenAuthenticationService.getUserLoginFromHeader(request);
        return useCaseExecutor.response(getUsers, new GetUsers.Request(login));
    }

}
