import React, {Component} from 'react';
import UserService from "../services/UserService";

class RegisterArtist extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isArtist: "Yes",

            categoryTaught:"",
            experience: "",
            typeOfArt: "",
            art: "",
            category: ""

        }
        this.handleChange = this.handleChange.bind(this)
        this.handleClick = this.handleClick.bind(this);
    }

    handleChange(event) {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        })
    }

    handleClick = (e) => {
        e.preventDefault();
        let user = {

            category_taught: this.state.categoryTaught,
            experience: this.state.experience,
            type_of_art: this.state.typeOfArt,
            sample_image_name: this.state.art,
            category: this.state.category,
            user_category: this.state.isArtist === "Yes" ? "artist" : "buyer"

        }
        console.log('User =>' + JSON.stringify(user));


    }

    render() {
        return(
            <div className="inner">
            <form onSubmit={this.handleClick}>
                <h3>Register Yourself as an Artist here</h3>
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

                <button 
                        className="btn btn-dark btn-lg btn-block"
                        // onClick={this.handleClick}
                        // type="submit"
                >Register</button>

            </form>
            </div>
        )
    }
}

export default RegisterArtist