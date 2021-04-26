import React, {Component} from 'react';
import SignIn from'./SignIn'
import UserService from '../services/UserService';
import {Link} from "react-router-dom";

class SignUp extends Component {

    constructor(props) {
        super(props);

        this.state = {
            firstName: "",
            lastName: "",
            email: "",
            contactNumber: "",
            password: "",
            address: "",
            user_category: ""
        }
        this.handleChange = this.handleChange.bind(this)
        this.saveUser=this.saveUser.bind(this);

    }

    handleChange(event) {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        })
    }

    saveUser=(e)=>{
        e.preventDefault();
        let user={first_name:this.state.firstName, last_name: this.state.lastName,
            email_address: this.state.email , password:this.state.password, contact_no: this.state.contactNumber,
            address:this.state.address, user_category: this.state.user_category, log_status: "false"}

        //console.log('User =>' + JSON.stringify(user));
        console.log("From signUp.js:",user);
                                                                                                                                                                                                            
        UserService.createUser(user).then(res=>{
            // this.props.history.push('/SignIn');
            console.log(res);
            console.log("Registered!")
        });
    }

    render() {
        return(
            <div className="SignUp">
                <div className="register">
                    <h1>Create an account</h1>
                    <p>Already have an account?<Link to="/SignIn"> Sign in</Link></p>
                </div>
                <div className="main">
                    <form onSubmit={this.handleSubmit}>
                        <div id="name">
                            {/*<h2 className="name">Name</h2>*/}
                            <label className="firstLabel">First Name: </label>
                            <input
                                type="text"
                                name="firstName"
                                required="True"
                                className="firstName"
                                placeholder="First Name"
                                value={this.state.firstName}
                                onChange={this.handleChange}
                            />
                            <br/>
                            <label className="lastLabel">Last Name: </label>
                            <input
                                type="text"
                                name="lastName"
                                className="lastName"
                                placeholder="Last Name"
                                value={this.state.lastName}
                                onChange={this.handleChange}
                            />
                        </div>
                        {/*<h2 className="name">Email Address</h2>*/}
                        <label className="emailAddress">Email Address: </label>
                        <input
                            type="email"
                            name="email"
                            required="True"
                            className="email"
                            placeholder="Email address"
                            value={this.state.email}
                            onChange={this.handleChange}
                        />
                        <br/>
                        {/*<h2 className="name">Phone</h2>*/}
                        <label className="contactNumber">Contact Number: </label>
                        <input
                            type="number"
                            name="contactNumber"
                            required="True"
                            className="contactNumber"
                            placeholder="Contact Number"
                            value={this.state.contactNumber}
                            onChange={this.handleChange}
                        />
                        <br/>
                        {/*<h2 className="name">Password</h2>*/}
                        <label className="password">Password: </label>
                        <input
                            type="password"
                            name="password"
                            required="True"
                            className="password"
                            placeholder="Password"
                            value={this.state.password}
                            onChange={this.handleChange}
                        />
                        <br/>
                        {/*<h2 className="name">Password</h2>*/}
                        <label className="address">Address: </label>
                        <input
                            type="text"
                            name="address"
                            required="True"
                            className="address"
                            placeholder="Address"
                            value={this.state.address}
                            onChange={this.handleChange}
                        />
                        <br/>
                        {/*<h2 className="name">Password</h2>*/}
                        <label className="userType">User Type: </label>
                        <input
                            type="text"
                            name="user_category"
                            required="True"
                            className="userType"
                            placeholder="User Type"
                            value={this.state.user_category}
                            onChange={this.handleChange}
                        />
                        <button className="registerButton" onClick={this.saveUser}>Register</button>
                    </form>
                </div>
            </div>
        )
    }

}

export default SignUp