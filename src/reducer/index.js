import {combineReducers} from 'redux'
import {loginReducer} from './LoginReducer'
import {homeReducer} from './HomeReducer'
export default  allReducers = combineReducers({
    loginReducer,
    homeReducer
})