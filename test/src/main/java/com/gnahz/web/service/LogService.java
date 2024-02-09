package com.gnahz.web.service;


import com.gnahz.common.api.ApiResult;
import com.gnahz.web.param.LoginUserParam;

public interface LogService {

    ApiResult login(LoginUserParam param);

    ApiResult logOut();
}
