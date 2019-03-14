import {GET_DATA,DELETE_ITEM} from '../action/homeAction/actionType'
const initState = {
    data: []
};
export const homeReducer = (state = initState,action) =>{
       
    switch(action.type){
        case GET_DATA:
            
        return {
            ...state,
            data: [
                ...state.data,
                {
                name:'viet',
                    content:'1 2 3 4 5 6 7 8'
                }, {
                    name: 'nam',
                    content: '1 2 3 4 5 6 7 8'
                }, {
                    name: 'quan',
                    content: '1 2 3 4 5 6 7 8'
                }]}
        case DELETE_ITEM : 
        return {
            data:[
                ...data,
                {
                    name:123,
                    content:123,
                }
            ]
        }
            
        
        default:
        return state
    }
}