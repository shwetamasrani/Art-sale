import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Login from "./component/Login";
import SignUp from "./component/SignUp";
import Dashboard from "./component/Dashboard";
import Welcome from "./component/Welcome";

function App() {
    let cors = require('cors');
    return (<Router>
                <div className="outer">
                    <div className="inner">
                        <Switch>
                            <Route exact path='/' component={Welcome} />
                            <Route path="/sign-in" component={Login} />
                            <Route path="/sign-up" component={SignUp} />
                            <Route path="/Dashboard" component={Dashboard} />
                        </Switch>
                    </div>
                </div>
        </Router>
    );
}

export default App;