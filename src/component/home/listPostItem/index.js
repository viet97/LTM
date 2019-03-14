import React, { Component } from 'react';
import { Container, Header, Footer, Body, Title, View, Input, Button, Form, Content } from 'native-base';
import { connect } from 'react-redux';
import {deleteItem} from '../../../action/homeAction/index'
import { Text, Image,TouchableOpacity } from 'react-native'
export class ListPostItem extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    componentDidMount(){
        
    }

    render() {
        let item = this.props.item
    
        return (
            <View >
            <TouchableOpacity 
            onPress = {() =>{
                this.props.delete()
            }}>
                    <Text>{item.item.name}</Text>
                    <Text>{item.item.content}</Text>
            </TouchableOpacity>
                
            </View>
        );
    }
}

const mapStateToProps = state => {

    return {
        data: state.homeReducer.data
    }
}

const mapDispatchToProps = dispatch => {
    return {
        delete: ()=> dispatch(deleteItem())
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ListPostItem)