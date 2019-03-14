import {takeEvery, put, takeLatest} from 'redux-saga/effects'
import {LOGIN_REQUEST} from '../action/loginAction/actionType'
function* login(action){
    let data =  new URLSearchParams()
    data.append('username',action.state.username)
    data.append('password',action.state.password)

    try{
       const response = yield fetch('192.168.1.58:8080/api/login',{
            method:'POST',
            body:data
        }) 
        console.log(response)
        yield response = JSON.parse(response)
        
    }catch(error){
        yield console.log(error);
    }
}
function* watch_login_request(){
    yield takeLatest(LOGIN_REQUEST,login)
}
export const API = {
    watch_login_request
}