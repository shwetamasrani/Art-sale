import React, { useState,useContext, useRef } from "react";
import { Fragment } from "react";
import {Link, useHistory} from "react-router-dom";
import AuthContext from '../../stores/auth-context';

const Navbar=() => {

    const history = useHistory();
    const authCtx = useContext(AuthContext);

    const isLoggedIn = authCtx.isLoggedIn;
  
    const logoutHandler = () => {
      authCtx.logout();
      // optional: redirect the user
      history.replace('/');
    };

    
        return(
            <nav className="navbar navbar-expand-lg navbar-light fixed-top">
                <div className="container">
                    <Link className="navbar-brand" to={"/sign-in"}>Art-Sale</Link>
                    <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul className="navbar-nav ml-auto">

                            {!isLoggedIn && (
                                <li className="nav-item">
                                    <Link className="nav-link" to='/sign-in'>Sign in</Link>
                                </li>
                            )}
                            {!isLoggedIn && (
                                <li className="nav-item">
                                    <Link className="nav-link" to='/sign-up'>Sign up</Link>
                                </li>
                            )}
                            {isLoggedIn && (
                                <Fragment>
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/dashboard'>Dashboard</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/custom-art'>CustomArt</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/register-artist'>Register Artist</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/profile'>Profile</Link>
                                    </li>
                                    
                                    <li>
                                        <button onClick={logoutHandler}>Logout</button>
                                    </li>
                                </Fragment>
                            )}
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }


export default Navbar