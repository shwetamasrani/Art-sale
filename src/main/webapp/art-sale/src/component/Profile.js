import React, {Component} from 'react';
import UserService from "../services/UserService";

class Profile extends Component {
    constructor(props){
        super(props);

        this.state={
            email: "shweta@gmail.com"
        }
        this.view = this.view.bind(this);
    }


    view(e){
        e.preventDefault();

        UserService.viewProfile(this.state.email).then(res=>{
            console.log(res.data);
            console.log("Artistid",res.data.object[0])
        }).catch(err=>{
            console.log(err);
        })
    }
    render() {
        return(
            <button type="submit"
                    className="btn btn-dark btn-lg btn-block"
                    onClick={this.view}
            >View Profile</button>
        )
    }
}

export default Profile