package com.fraki.robimycos.usecase.base;

import org.springframework.stereotype.Component;

/**
 * Created by bambo on 09.10.2017.
 */
@Component
public class UseCaseExecutor  {

    public <Q extends UseCase.Request, T>T response(UseCase<Q, T> useCase, Q request) {
        return useCase.response(request);
    }
}
