import React, { Component } from 'react';
import { Container, Header, Footer, Body, Title, View, Input, Button, Form, Content } from 'native-base';
import { connect } from 'react-redux';
import {Text} from 'react-native'
import { ProfileComponent } from './profile';
import { ListPostComponent } from './listPosts';
import { homeAction} from '../../action/homeAction/index'
export class HomeComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            
        }
    }
    componentDidMount(){
        this.props.getData()
    }
    render() {
    
        return (
            <Container>
                <Header />
                <Content >
                <ProfileComponent
                 style={{width:'100%',height:900}}
                />
                <ListPostComponent
                    data = {this.props.data}
                />
                </Content>
            </Container>
        );
    }
}

const mapStateToProps = state => {

    return {
        isLoginSuccess: state.loginReducer.isLoginSuccess,
        data:state.homeReducer.data,
        text: state.loginReducer.text
    }
}

const mapDispatchToProps = dispatch =>{
    return{
        getData:()=>dispatch(homeAction())
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(HomeComponent)