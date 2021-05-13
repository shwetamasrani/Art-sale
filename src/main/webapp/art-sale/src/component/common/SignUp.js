import React, { Component } from "react";
import {Link, withRouter} from "react-router-dom";
import UserService from '../../services/UserService';
import Navbar from "./Navbar";

class SignUp extends Component {
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
            image: "",
            category: "",
            price: ""
        }
        this.handleChange = this.handleChange.bind(this)
        this.saveUser = this.saveUser.bind(this);
        this.handleImageChange = this.handleImageChange.bind(this)
    }

    handleChange(event) {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        })
    }

    handleImageChange(e){
        this.setState({
            image: e.target.files[0]
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
            address: this.state.address}

        let artist = {
            category_taught: this.state.categoryTaught,
            experience: this.state.experience,
            type_of_art: this.state.typeOfArt,
            sample_image_name: this.state.image.name,
            category: this.state.category,
            user_category: this.state.isArtist === "Yes" ? "artist" : "buyer",
            price: this.state.price
        }

        console.log('User =>' + JSON.stringify(user));

        let finalUser;
        if (this.state.isArtist)
        {
            finalUser = {
                ...user,
                ...artist
            }
        }
        else{
            finalUser = user;
        }
        console.log(finalUser);

        UserService.createUser(finalUser).then(res => {
            console.log("Response data:")
            console.log(res.data);
            console.log(res.data.artist_id)
            console.log(this.state.art);

            if (res.data.artist_id){
                let form_data = new FormData();
                form_data.append('file', this.state.image, this.state.image.name);
                form_data.append('artist_id', res.data.artist_id);
                UserService.saveImage(form_data).then(response=>{
                    console.log(response);
                })
            }
            this.props.history.push('/sign-in');
        });
    }


    render() {
        return (
            <div className="inner">
                {/* <Navbar/> */}
                <form>
                    <h3>Register</h3>
                    <div className="form-group">
                        <label className="firstLabel">Do you want a Artist account?  </label>
                        <select
                            name="isArtist"
                            id="isArtist"
                            onChange={this.handleChange}
                        >
                            <option value="No">No</option>
                            <option value="Yes">Yes</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>First name</label>
                        <input type="text"
                               className="form-control"
                               placeholder="First name"
                               name="firstName"
                               required="True"
                               value={this.state.firstName}
                               onChange={this.handleChange}
                        />
                    </div>

                    <div className="form-group">
                        <label>Last name</label>
                        <input type="text"
                               className="form-control"
                               placeholder="Last name"
                               name="lastName"
                               required="True"
                               value={this.state.lastName}
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
                                   name="image"
                                   required
                                   id="image"
                                   accept="image/png, image/jpeg"
                                   onChange={this.handleImageChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Medium of Art Piece</label>
                            <input type="text"
                                   className="form-control"
                                   placeholder="Enter Address"
                                   name="category"
                                   required
                                   value={this.state.category}
                                   onChange={this.handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Price</label>
                            <input type="Number"
                                   className="form-control"
                                   placeholder="Enter Price in INR"
                                   name="price"
                                   required
                                   value={this.state.price}
                                   onChange={this.handleChange}
                            />
                        </div>

                    </div>


                    <button type="submit"
                            className="btn btn-dark btn-lg btn-block"
                            onClick={this.saveUser}
                    >Register</button>
                    <p className="forgot-password text-right">
                        Already registered <a href="#">log in?</a>
                    </p>
                </form>
            </div>

        );
    }
}

export default withRouter (SignUp);