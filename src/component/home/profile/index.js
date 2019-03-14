import React, { Component } from 'react';
import { Container, Header, Footer, Body, Title, View, Input, Button, Form, Content } from 'native-base';
import { connect } from 'react-redux';
import { Text,Image } from 'react-native'
export class ProfileComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render() {

        return (
           <View style={{backgroundColor:'blue',alignItems:'center'}}>
                <Image
        
                    style = {{width:60,height:60,borderRadius:30}}
                    source = {require('../../../resources/image/avatar.jpg')}
                />
                <Text style = {{color:'white'}}>
                    {this.props.name}
                </Text>
           </View>
        );
    }
}

const mapStateToProps = state => {

    return {
       
    }
}

const mapDispatchToProps = dispatch => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProfileComponent)