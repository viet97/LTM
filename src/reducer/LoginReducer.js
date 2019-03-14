import {LOGIN_REQUEST,LOGIN_RESULT} from '../action/loginAction/actionType'
const initState = {
    isLoginSuccess:false
};

export const loginReducer = (state = initState, action) =>{
    switch(action.type){
        case LOGIN_REQUEST:   

        let username = action.state.username
        let password = action.state.password
        if (username === '1' && password === '1')
        {
            
            return{
                isLoginSuccess : true
            }
        }
        return{
            
            isLoginSuccess : false,
            text:'123123'
        }
        default:
        return state
    }
}