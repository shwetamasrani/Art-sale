import React, {Component} from 'react';
import {Link} from "react-router-dom";

class Navbar extends Component {
    render() {
        return(
            <nav className="navbar navbar-expand-lg navbar-light fixed-top">
                <div className="container">
                    <Link className="navbar-brand" to={"/sign-in"}>Art-Sale</Link>
                    <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link className="nav-link" to={"/sign-in"}>Sign in</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={"/sign-up"}>Sign up</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}

export default Navbar