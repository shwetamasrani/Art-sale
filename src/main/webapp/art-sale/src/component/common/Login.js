import React, { Component } from "react";
import {Link} from 'react-router-dom';
import UserService from "../../services/UserService";
import Dashboard from "./Dashboard";
import Navbar from "./Navbar";
export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            errorMessage: false
        }
        this.handleChange=this.handleChange.bind(this)
        this.handleClick=this.handleClick.bind(this)
    }
    handleChange = (event) => {
        // this.setState(
        //     {errorMessage : false}
        //     )
        const {name, value} = event.target
        this.setState({
            [name]: value
        })
    }
    handleClick(e){
        this.setState(
                 {errorMessage : false}
                 )
        e.preventDefault();

        let user = {
            email_address: this.state.email,
            password: this.state.password
        }
        console.log("HandleClick")
        console.log(user);
        UserService.getUser(user).then(res => {
            console.log("response",res);
            console.log("SignIn Component", res.data);
            console.log("Publisher", res.data);

            // if(res.data.publisherFlag){
            //     this.props.history.push({
            //         pathname: "/AdminDashboard",
            //         userId: res.data.user_id
            //     })
            //     //this.props.history.push('/AdminDashboard');
            // }
            // else{
            //     this.props.history.push({
            //         pathname: "/Dashboard",
            //         userId: res.data.user_id
            //     })
            //
            //     //this.props.history.push('/Dashboard');
            // }
           // if(res.data ==="SUCCESS"){
                localStorage.setItem('currentUser', JSON.stringify(res.data));
                this.props.history.push('/Dashboard');

           // }
           /* else{
                this.state.errorMessage = true;
            }*/
            console.log("LoggedIn");
        })
        .catch(err =>{
            console.log(err);
            alert("Username or Password doesn't Match!");
            window.location.reload(true);
        });
    }

    render() {
        return (
            <div>
                <Navbar/>
                <form>

                    <h3>Log in</h3>

                    <div className="form-group">
                        <label>Email</label>
                        <input type="email"
                               className="form-control"
                               placeholder="Enter email"
                               name="email"
                               required="True"
                               value={this.state.email}
                               onChange={this.handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password"
                               className="form-control"
                               placeholder="Enter password"
                               name="password"
                               required="True"
                               value={this.state.password}
                               onChange={this.handleChange}
                        />
                    </div>
                    <h3 style={{display: this.state.errorMessage ? "block" : "none"}}>Incorrect
                        Username/Password</h3>
                    <br/>
                    {/*<div className="form-group">
                        <div className="custom-control custom-checkbox">
                            <input type="checkbox" className="custom-control-input" id="customCheck1" />
                            <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                        </div>
                    </div>*/}

                    <button type="submit"
                            className="btn btn-dark btn-lg btn-block"
                            onClick={this.handleClick}
                    >Sign in</button>
                    <p className="forgot-password text-right">
                        Forgot <a href="#">password?</a>
                    </p>
                </form>


            </div>
        );
    }
}
