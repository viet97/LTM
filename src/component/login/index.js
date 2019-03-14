import React, {Component} from 'react';
import {Container, Header, Footer, Body, Title, View,Input, Button, Form, Content} from 'native-base';

import {LOGIN_REQUEST,LOGIN_RESULT} from '../../action/loginAction/actionType'
import {Keyboard,Text} from 'react-native';
import {connect} from 'react-redux';
import {loginAction} from '../../action/loginAction/index'
export  class LoginComponent extends Component{
    constructor(props){
        super(props)
        this.state = {
            username:'',
            password:'',
            isLoginSuccess:false
        }
    }

    render(){
        const {navigate} = this.props.navigation
        return <Container>
            <Header />
                <Content>
                    <Input
                    onChangeText = { username =>{
                        this.setState({username})
                    } } />
                    <Input 
                    onChangeText={password => {
                        this.setState({ password })
                        
                    }} />
                    <Button onPress={() => {
                        this.props.login(this.state.username,this.state.password);
                         navigate('Home')   
                        }}>
                        <Text>Dang Nhap</Text>
                    </Button>
                </Content>
          </Container>
    }
}
const mapStateToProps = state =>{
  

    return{
        isLoginSuccess: state.loginReducer.isLoginSuccess,
        
    }
}

const mapDispatchToProps = dispatch => {
    return {
        login: (username,password) => dispatch(loginAction({username,password})),
    
    };
};
export default connect(mapStateToProps,mapDispatchToProps)(LoginComponent)