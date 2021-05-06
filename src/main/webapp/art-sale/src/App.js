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

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: null,
            isArtist: false
        };
    }

    componentDidMount() {
        console.log("from app componentDidMount")
        console.log(localStorage.getItem('currentUser'));
    }

    logout() {

        //history.push('/login');
    }

    render(){
            return (<Router>
            <div className="App">

                <div className="outer">
                    <div className="inner">
                        <Switch>
                            <Route exact path='/' component={Login} />
                            <Route path="/sign-in" component={Login} />
                            <Route path="/sign-up" component={SignUp} />
                            <Route path="/Dashboard" component={Dashboard} />
                            <Route path="/custom-art" component={CustomArt} />
                            <Route path="/register-artist" component={RegisterArtist} />
                            <Route path="/profile" component={Profile} />
                        </Switch>
                    </div>
                </div>

            </div>
        </Router>
            );
    }
}

export default App;