import React, { Component } from "react";
import {Link} from "react-router-dom";
import UserService from '../services/UserService';

export default class SignUp extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isArtist: "No",

            firstName: "",
            lastName: "",
            email: "",
            password: "",
            contactNumber: "",
            address: "",

            categoryTaught:"",
            experience: "",
            typeOfArt: "",
            art: "",
            category: ""

        }
        this.handleChange = this.handleChange.bind(this)
        this.saveUser = this.saveUser.bind(this);
    }

    handleChange(event) {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        })
    }

    saveUser = (e) => {
        e.preventDefault();
        let user = {
            first_name: this.state.firstName,
            last_name: this.state.lastName,
            email_address: this.state.email,
            contact_no: this.state.contactNumber,
            password: this.state.password,
            address: this.state.address,

            category_taught: this.state.categoryTaught,
            experience: this.state.experience,
            type_of_art: this.state.typeOfArt,
            sample_image_name: this.state.art,
            category: this.state.category,
            user_category: this.state.isArtist === "Yes" ? "artist" : "buyer"

        }

        console.log('User =>' + JSON.stringify(user));

        UserService.createUser(user).then(res => {
            this.props.history.push('/Login');
        });
    }


    render() {
        return (
            <form>
                <h3>Register</h3>
                <div className="form-group">
                    <label className="firstLabel">Do you want a Artist account?  </label>
                    <select
                        name="isArtist"
                        id="isArtist"
                        onChange={this.handleChange}
                    >
                        <option value="No" selected>No</option>
                        <option value="Yes">Yes</option>
                    </select>
                </div>

                <div className="form-group">
                    <label>First name</label>
                    <input type="text"
                           className="form-control"
                           placeholder="First name"
                           name="firstname"
                           required="True"
                           value={this.state.firstname}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Last name</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Last name"
                           name="lastname"
                           required="True"
                           value={this.state.lastname}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Email</label>
                    <input type="email"
                           className="form-control"
                           placeholder="Enter email"
                           name="email"
                           required="True"
                           value={this.state.email}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password"
                           className="form-control"
                           placeholder="Enter password"
                           name="password"
                           required="True"
                           value={this.state.password}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Contact Number</label>
                    <input type="number"
                           className="form-control"
                           placeholder="Enter Contact Number"
                           name="contactNumber"
                           required="True"
                           value={this.state.contactNumber}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Address</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Enter Address"
                           name="address"
                           required="True"
                           value={this.state.address}
                           onChange={this.handleChange}
                    />
                </div>



                <div style={{display: this.state.isArtist === "Yes" ? "block" : "none"}}>

                    <div className="form-group">
                        <label>Are you a trained or self taught Artist?</label>
                        <input type="text"
                               className="form-control"
                               placeholder="Enter Address"
                               name="categoryTaught"
                               required="True"
                               value={this.state.categoryTaught}
                               onChange={this.handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>How many years of experience do you have as an Artist?</label>
                        <input type="number"
                               className="form-control"
                               placeholder="Enter Address"
                               name="experience"
                               required="True"
                               value={this.state.experience}
                               onChange={this.handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>What type of art you do?</label>
                        <input type="text"
                               className="form-control"
                               placeholder="Enter Address"
                               name="typeOfArt"
                               required="True"
                               value={this.state.typeOfArt}
                               onChange={this.handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Upload one of your art piece</label>
                        <input type="file"
                               placeholder="Enter Address"
                               name="art"
                               required="True"
                               value={this.state.art}
                               onChange={this.handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Medium of Art Piece</label>
                        <input type="text"
                               className="form-control"
                               placeholder="Enter Address"
                               name="category"
                               required="True"
                               value={this.state.category}
                               onChange={this.handleChange}
                        />
                    </div>

                </div>


                <button type="submit"
                        className="btn btn-dark btn-lg btn-block">Register</button>
                <p className="forgot-password text-right">
                    Already registered <a href="#">log in?</a>
                </p>
            </form>
        );
    }
}