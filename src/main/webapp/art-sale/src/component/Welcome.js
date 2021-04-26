import React, {Component} from "react";
import {Link} from 'react-router-dom';
import SignIn from "./SignIn";
import SignUp from "./SignUp";
class Welcome extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleSubmit() {
        alert("create account")
        //this.props.history.push('/SignUp')
    }


    render() {
        return (
            <div className="Welcome">
                <div className="content">
                    <div className="login">
                        <SignUp/>
                    </div>
                </div>
            </div>
        );
    }
}

export default Welcome