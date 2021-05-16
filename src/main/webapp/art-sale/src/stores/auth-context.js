import React, { useState, useEffect, useCallback } from 'react';
import {useHistory} from 'react-router-dom';
let logoutTimer;

const AuthContext = React.createContext({
    token: {},
    isLoggedIn: false,
    isArtist: false,
    login: (token) => {},
    logout: () => {},
});


const calculateRemainingTime = (expirationTime) => {
    const currentTime = new Date().getTime();
    const adjExpirationTime = new Date(expirationTime).getTime();

    const remainingDuration = adjExpirationTime - currentTime;

    return remainingDuration;
};

const retrieveStoredToken = () => {
    const storedToken = localStorage.getItem('token');
    const storedExpirationDate = localStorage.getItem('expirationTime');

    const remainingTime = calculateRemainingTime(storedExpirationDate);

    if (remainingTime <= 3600) {
        localStorage.removeItem('token');
        localStorage.removeItem('expirationTime');
        return null;
    }

    return {
        token: storedToken,
        duration: remainingTime,
    };
};


export const AuthContextProvider = (props) => {

    const history = useHistory();

    const tokenData = retrieveStoredToken();

    let initialToken;
    let flag;
    if (tokenData) {
        initialToken = tokenData.token;
        flag = tokenData.token.user_category === "artist" ? true : false;
    }

    const [token, setToken] = useState(initialToken);
    const [userIsArtist, setuserIsArtist] = useState(flag);

    const userIsLoggedIn = !!token;
    // const userIsArtist;
    // if(userIsLoggedIn)
    // {
    //     setuserIsArtist(token.user_category === "artist" ? true : false);
    // }

    const logoutHandler = useCallback(() => {
        setToken(null);
        localStorage.removeItem('token');
        localStorage.removeItem('expirationTime');

        if (logoutTimer) {
            clearTimeout(logoutTimer);
        }
        window.location.href = '/';
    }, []);

    const loginHandler = (token, expirationTime) => {
        setToken(token);
        setuserIsArtist(token.user_category === "artist" ? true : false);
        // console.log("From auth context",token);
        // console.log("userIsLoggedIn",userIsLoggedIn);
        // userIsArtist = token.user_category === "artist" ? true : false;
        // console.log("userIsArtist",userIsArtist);
        // console.log("isLoggedIn",isLoggedIn);
        //JSON.parse(localStorage.getItem('token'));

        localStorage.setItem('token', JSON.stringify(token));
        localStorage.setItem('expirationTime', expirationTime);

        const remainingTime = calculateRemainingTime(expirationTime);

        logoutTimer = setTimeout(logoutHandler, remainingTime);
    };

    useEffect(() => {
        if (tokenData) {
            console.log(tokenData.duration);
            logoutTimer = setTimeout(logoutHandler, tokenData.duration);
        }
    }, [tokenData, logoutHandler]);

    const contextValue = {
        token: token,
        isLoggedIn: userIsLoggedIn,
        isArtist : userIsArtist,
        login: loginHandler,
        logout: logoutHandler,
    };


    return (
        <AuthContext.Provider value={contextValue}>
            {props.children}
        </AuthContext.Provider>
    );
};

export default AuthContext;