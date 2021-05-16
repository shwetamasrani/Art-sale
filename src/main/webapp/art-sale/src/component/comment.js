import React, { Component } from 'react'
import {Redirect} from "react-router-dom"
import axios from 'axios';
import UserService from '../services/UserService'

class comment extends Component {

    constructor(props){
        super(props)

        this.state = 
        {
            details: this.props.location.details,
            email: JSON.parse(localStorage.getItem('token')).email_address,
            artist_id: "",
            comment: ""
        }
        this.handleChange = this.handleChange.bind(this);
        this.onClick =this.onClick.bind(this);
        console.log("From comment",this.state.details)
    }

    handleChange(event) {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        });
    }

    async onClick(e) {

        this.setState({
            email: JSON.parse(localStorage.getItem('token')).email_address
        })

        await UserService.viewProfile(this.state.email).then(res=>{
            console.log(res.data);
            console.log("Artistid",res.data.object[0])
            this.setState({
                artist_id: res.data.object[0]
            })
            let comment ={
                comments : this.state.comment,
                status_custom_id : this.state.details.custom_id,
                status_artist_id : this.state.artist_id
            }
    
            console.log(JSON.stringify(comment));
            axios.post("http://localhost:8090/artistComments/saveCommentsByArtist",JSON.stringify(comment),{
                headers: {'Content-Type': 'application/json'}
            })
            .then(res=>{
                console.log(res.data);
                console.log(res.status)
                this.props.history.push({pathname:'/art-request'});
                
            }).catch(err=>{
                console.log(err);
            });
        }).catch(err=>{
            console.log(err);
        })

       
    }

    render() {
        return (
            <div>
                 
                 <div className="details">
                        <p>Customer Name:{this.state.details.buyer}</p>
                        <p>Type of Art: {this.state.details.type}</p>
                        <p>Meduim of Paper: {this.state.details.paper}</p>
                        <p>Number of Art Piece: {this.state.details.quant}</p>
                        <p>Location to put art pieces: {this.state.details.loc}</p>
                        <p>Purpose: {this.state.details.use}</p>
                        <p>Description: {this.state.details.desc}</p>
                        <p>Reference Image:</p>
                        <img src={"data:image/jpg;base64," + this.state.details.refimg} style={{width: '18rem'}}/>
                        <br></br>
                        <form onSubmit={this.onClick}>
                        <label>Add your comment:</label>
                        <input type="text"
                            className="form-control"
                            placeholder="Add your requirements or comments for this request"
                            name="comment"
                            required="True"
                            value={this.state.comment}
                            onChange={this.handleChange}
                        />
                        <button className="btn btn-dark btn-lg btn-block"
                                // onClick={this.onClick}
                               >
                            <p>Post Comment</p>
                        </button>
                        </form>
                    </div>
            </div>
        )
    }
}

export default comment;
