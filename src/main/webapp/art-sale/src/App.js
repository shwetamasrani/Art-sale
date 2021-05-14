import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Login from "./component/common/Login";
import SignUp from "./component/common/SignUp";
import Dashboard from "./component/common/Dashboard";
import Welcome from "./component/Welcome";
import CustomArt from "./component/CustomArt";
import RegisterArtist from "./component/RegisterArtist";
import Profile from "./component/Profile";
import Layout from './component/common/Layout';
import CustomRequestsArtist from './component/CustomRequestsArtist';

class App extends React.Component {
    // constructor(props) {
    //     super(props);
    //
    //     this.state = {
    //         currentUser: null,
    //         isArtist: false
    //     };
    // }
    //
    // componentDidMount() {
    //     // console.log("from app componentDidMount")
    //     // console.log(localStorage.getItem('currentUser'));
    // }
    //
    // logout() {
    //
    //     //history.push('/login');
    // }

    render(){
            return (
            <Layout name="Hii">
                {/* <Router> */}
                {/* <div className="outer"> */}
                <div className="App">
                    
                        
                            <Switch>
                                <Route exact path='/'>
                                    <Login/>
                                </Route>
                                <Route path="/sign-in" >
                                    <Login/>
                                </Route>
                                <Route path="/sign-up">
                                    <SignUp/>
                                </Route>
                                <Route path="/dashboard">
                                    <Dashboard/>
                                </Route>
                                <Route path="/custom-art">
                                    <CustomArt/>
                                </Route>
                                <Route path="/register-artist">
                                    <RegisterArtist/>
                                </Route>
                                <Route path="/profile">
                                    <Profile/>
                                </Route>
                                <Route path="/art-request">
                                    <CustomRequestsArtist/>
                                </Route>
                            </Switch>
                        
                    {/* </div> */}

                </div>
                {/* </Router> */}
            </Layout>
            );
    }
}

export default App;