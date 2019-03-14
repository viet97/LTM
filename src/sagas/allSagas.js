import {all} from 'redux-saga/effects';
import {API} from './api'

export default function*(){
    yield all (API.watch_login_request)
}