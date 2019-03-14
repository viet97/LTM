/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';
import { Provider } from "react-redux";
import {createStore,applyMiddleware} from 'redux'
import { StackNavigator} from 'react-navigation';
import LoginComponent from './src/component/login/index'
import HomeComponent from './src/component/home/index'
import { Root } from 'native-base';
import App from './src/App'
import allReducers from './src/reducer/index'
import createSagaMiddleware from 'redux-saga'
import allSagas from './src/sagas/allSagas';
const sagaMiddleware = createSagaMiddleware()
let store = createStore(allReducers,applyMiddleware(sagaMiddleware))
sagaMiddleware.run(allSagas)
export default class Setup extends React.Component {
  render() {
    return (
      <Provider store = {store}>
      <App/>
      </Provider>
    );
  }
}

