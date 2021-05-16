import React, { useState,useContext, useRef } from "react";
import {Link, useHistory} from 'react-router-dom';
import UserService from "../../services/UserService";
import Dashboard from "./Dashboard";
import Navbar from "./Navbar";
import AuthContext from '../../stores/auth-context';

const Login = (props) => {
    const history = useHistory();
    const emailInputRef = useRef();
    const passwordInputRef = useRef();
    const authCtx = useContext(AuthContext);
    const [errorMessage, setErrorMessage] = useState(false);
    // constructor(props) {
    //     super(props);
    //     this.state = {
    //         email: "",
    //         password: "",
    //         errorMessage: false
    //     }
    //     this.handleChange=this.handleChange.bind(this)
    //     this.handleClick=this.handleClick.bind(this)
    // }

    // handleChange = (event) => {
    //     // this.setState(
    //     //     {errorMessage : false}
    //     //     )
    //     const {name, value} = event.target
    //     this.setState({
    //         [name]: value
    //     })
    // };

    const handleClick = (e) => {
        e.preventDefault();
        const enteredEmail = emailInputRef.current.value;
        const enteredPassword = passwordInputRef.current.value;

        let user = {
            email_address: enteredEmail,
            password: enteredPassword
        }
        console.log("HandleClick")
        console.log(user);
        UserService.getUser(user).then(res => {
            console.log(res);

            const expirationTime = new Date(
                new Date().getTime() + 90000000
            );
            authCtx.login(res.data, expirationTime.toISOString());

            history.push('/dashboard');
           
            // localStorage.setItem('currentUser', JSON.stringify(res.data));
            // this.props.history.push('/Dashboard');
        })
            .catch(err =>{
                console.log(err);
                setErrorMessage(err);
                alert("Username or Password doesn't Match!");
                //window.location.reload(true);
            });
    };


    return (
        <div className="inner">
            {/* <Navbar/> */}
            <form>

                <h3>Log in</h3>

                <div className="form-group">
                    <label>Email</label>
                    <input type="email"
                           className="form-control"
                           placeholder="Enter email"
                           name="email"
                           required="True"
                        //    value={this.state.email}
                           ref={emailInputRef}
                        //  onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password"
                           className="form-control"
                           placeholder="Enter password"
                           name="password"
                           required="True"
                        //    value={this.state.password}
                           ref={passwordInputRef}
                        // onChange={handleChange}
                    />
                </div>
                <h3 style={{display: errorMessage ? "block" : "none"}}>Incorrect
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
                        onClick={handleClick}
                >Sign in</button>
                <p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>
            </form>


        </div>
    );

}

export default Login;