import React, { Component } from 'react';
import axios from 'axios';

export default class ApproveComment extends Component {

    constructor(props){
        super(props);
        this.state = {
            comments_id: this.props.comments_id,
            comment: this.props.comment,
            artist: this.props.artist,
            custom_id: this.props.custom_id,
            status: this.props.status
        }

        this.onClick = this.onClick.bind(this)
    }

    onClick(){
        let approve ={
            status_custom_id : this.state.custom_id,
            status_artist_id : this.state.artist.artist_id
        }

        console.log(JSON.stringify(approve));
        axios.post("http://localhost:8090/artistComments/approveCustRequestStatusTrial",JSON.stringify(approve),{
            headers: {'Content-Type': 'application/json'}
        })
        .then(res=>{
            console.log(res.data);
            console.log(res.status)
            this.props.history.push({pathname:'/custom-art-request'});
            
        }).catch(err=>{
            console.log(err);
        });
    }

    render() {
        return (
            <div className="paintbox" id={this.state.comments_id}>
                    <div className="details">
                        {/* <p>Customer Name:{this.state.buyer}</p> */}
                        <label>Comment:</label>
                        <p>{this.state.comment}</p>
                        <label>Artist Details:</label>

                        <p>Name: {this.state.artist.userDetails.first_name + " " + this.state.artist.userDetails.last_name}</p>
                        <p>Experiece in Art: {this.state.artist.experience} years</p>
                        <p>Contact: {this.state.artist.userDetails.email_address}</p>
                        <p>From: {this.state.artist.userDetails.address}</p>

                        
                            <button className="btn btn-dark btn-lg btn-block"
                                onClick={this.onClick}>
                            <p>Approve</p>
                            </button>
                        
                        
                    </div>
                   
            </div>
        )
    }
}
