import React, { Component } from 'react';
import { Container, Header, Footer, Body, Title, View, Input, Button, Form, Content } from 'native-base';
import { connect } from 'react-redux';
import { Text, Image,FlatList } from 'react-native'
import { ListPostItem } from '../listPostItem';
export class ListPostComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render() {
      
        return (
            <View >
                <FlatList
                
                    data = {this.props.data}
                    renderItem = {(item) => 
                        <ListPostItem item = {item} />
                    }
                />
               
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

export default connect(mapStateToProps, mapDispatchToProps)(ListPostComponent)