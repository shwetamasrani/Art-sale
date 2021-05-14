import React, { useState,useContext, useRef,useEffect } from "react";
import { Fragment } from "react";
import {Link, useHistory} from "react-router-dom";
import AuthContext from '../../stores/auth-context';

const Navbar=() => {

    const history = useHistory();
    const authCtx = useContext(AuthContext);

    const isLoggedIn = authCtx.isLoggedIn;
    let isArtist = authCtx.isArtist;
    console.log("isArtist",isArtist);
    useEffect(() =>{
        console.log("navbar")
        console.log("isArtist",isArtist);
        // if (isLoggedIn)
        // {
        //     const user = authCtx.token;
        //     isArtist = JSON.parse(user).user_category === "artist" ? true : false;
        //     // console.log(JSON.parse(user).user_category)
        //     console.log(JSON.parse(user));
        //     console.log("isArtist",isArtist);
        //     console.log(isLoggedIn);
        // }
    },[isLoggedIn,isArtist]); 
    
   
    // const isArtist = userLoggedIn.user_category === "artist" ? "true" : "false";
    // console.log(isArtist);
  
    const logoutHandler = () => {
      authCtx.logout();
      // optional: redirect the user
    };

    
        return(
            <nav className="navbar navbar-expand-lg navbar-light fixed-top">
                <div className="container">
                    <Link className="navbar-brand" to={"/sign-in"}>Art-Sale</Link>
                    <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul className="navbar-nav ml-auto">

                            {!isLoggedIn && (
                                <Fragment>
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/sign-in'>Sign in</Link>
                                    </li>
                                
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/sign-up'>Sign up</Link>
                                    </li>
                                </Fragment>
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
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/art-request'>Art Requests</Link>
                                    </li>
                                    
                                    <li>
                                        <button onClick={logoutHandler}>Logout</button>
                                    </li>
                                </Fragment>
                            )}
                            {/* {!isArtist && (
                                    <li className="nav-item">
                                        <Link className="nav-link" to='/register-artist'>Register Artist</Link>
                                    </li>
                            )} */}
                                    
                                    
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }


export default Navbar