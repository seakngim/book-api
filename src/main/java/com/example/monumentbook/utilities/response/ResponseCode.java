package com.example.monumentbook.utilities.response;

public interface ResponseCode {

    int FAIL                    = 000;
    int OK                      = 200;
    int CREATED                 = 201;
    int FOUND                   = 302;
    int BAD_REQUEST             = 400;
    int UNAUTHORIZED            = 401;
    int FORBIDDEN               = 403;
    int NOT_FOUND               = 404;
    int METHOD_NOT_ALLOW        = 405;
    int REQUEST_TIMEOUT         = 408;
    int INTERNAL_SERVER_ERROR   = 500;

}