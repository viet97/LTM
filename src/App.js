/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';

import { StackNavigator } from 'react-navigation';
import LoginComponent from './component/login/index'
import HomeComponent from './component/home/index'
import { Root } from 'native-base';
const AppRouter = StackNavigator(
    {
        Login: {
            screen: LoginComponent,
            navigationOptions: {
                header: null,
                headerBackTitle: 'Login',
            },
        },
        Home: {
            screen: HomeComponent,
            navigationOptions: {
                header: null,
                headerBackTitle: 'Home',
            },
        },
    },
    {
        initialRouteName: 'Login',
        navigationOptions: {
            headerTintColor: 'black'
        }
    }
);

export default () =>(
    <Root>
        <AppRouter/>
    </Root>
)
