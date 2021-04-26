import React, {Component} from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import SignIn from "./component/SignIn";
import SignUp from "./component/SignUp"
import Welcome from "./component/Welcome";
import Navbar from "./component/Navbar"

/*
import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Book from './components/Book/Book';
import BookList from './components/Book/BookList';
import UserList from './components/User/UserList';
import Register from './components/User/Register';
import Login from './components/User/Login';
import Footer from './components/Footer';*/

class App extends Component {



    constructor(props) {
        super(props);
        const heading = "Welcome to Art Sale";
        const quote = "Good friends, good books, and a sleepy conscience: this is the ideal life.";
        const footer = "";


        var cors = require('cors')
        /*var app = express()*/

    }

    render(){
        return (
            <div>
                <Router>
                    <div>
                        <Navbar/>
                    </div>
                    <div className="container">
                        <Switch>

                            <Route exact path='/' component={Welcome}/>
                            <Route exact path='/SignIn' component={SignIn}/>
                            <Route exact path='/SignUp' component={SignUp}/>
                            <Route exact path='/Dashboard' component={Welcome}/>

                        </Switch>
                    </div>
                </Router>
            </div>
        );
    }

}

export default App;