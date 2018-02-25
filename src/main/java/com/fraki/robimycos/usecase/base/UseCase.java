package com.fraki.robimycos.usecase.base;

/**
 * Created by bambo on 09.10.2017.
 */
abstract public class UseCase<Q extends UseCase.Request, P> {

    public abstract P response(Q request);

    public interface Request {

    }

}
