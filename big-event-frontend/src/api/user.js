import request from "../utils/request";


export const userRegisterService = (registerData) => {
    //借助urlSearchParams完成传递
    let urlSearchParams = new URLSearchParams();
    for(let key in registerData){
        urlSearchParams.append(key,registerData[key])
    }
    return request.post('user/register',urlSearchParams)
}


export const userLoginService = (registerData) => {
    //借助urlSearchParams完成传递
    let urlSearchParams = new URLSearchParams();
    for(let key in registerData){
        urlSearchParams.append(key,registerData[key])
    }
    return request.post('user/login',urlSearchParams)
}