import React, { Component,Fragment } from 'react';
import BuyerRequest from './BuyerRequest';
import axios from 'axios';

class MyCustomRequest extends Component {

    constructor(props) {
        super(props);
        this.state ={
            requests: [],
            user_id: JSON.parse(localStorage.getItem('token')).user_id
        }

        console.log(this.state.user_id)

        axios.get("http://localhost:8090/dashNavigationMenu/getAllCustomizedOrdersUser/"+this.state.user_id).then(res=>{
            console.log(res.data);
            console.log(res.status)
            this.setState({
                requests: res.data
            })
        }).catch(err=>{
            console.log(err);
        });
       
    }

    render() {
        const Requests = this.state.requests.map((request) => {
            return (
               <Fragment>
                    <BuyerRequest
                        key={request.custom_id}
                        buyer={request.userDetails.first_name +" "+ request.userDetails.last_name}
                        refimg={request.ref_art_image}
                        type={request.type_of_art}
                        paper={request.paper_canvas}
                        quant={request.quantity}
                        loc={request.art_location}
                        use={request.art_use}
                        desc={request.description}
                    />
            </Fragment>
            )
        })
        return (
            <div>

                    <div className="container">
                        {Requests}
                    </div>
            </div>
        )
    }
}
export default MyCustomRequest;