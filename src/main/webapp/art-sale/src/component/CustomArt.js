import React, {Component} from 'react';
import UserService from "../services/UserService";

export default class CustomArt extends Component {
        constructor(props) {
            super(props);

            this.state = {
                typeOfArt: "landscape",
                paper: "",
                quantity: "",
                artLocation: "",
                use: "",
                image: "",
                description: ""

            }
            this.handleChange = this.handleChange.bind(this);
            this.handleImage = this.handleImage(this);
            this.handleClick = this.handleClick.bind(this);
        }

        handleChange(event) {
            const {name, value} = event.target;
            this.setState({
                [name]: value
            });
        }

        handleImage(e){
            this.setState({
                image: e.target.files[0]
            })
        }

        handleClick(event){
            event.preventDefault();
            let request = {
                    buyer_id : "10",
                    type_of_art: this.state.typeOfArt,
                    paper_canvas : this.state.paper,
                    art_location : this.state.artLocation,
                    quantity : this.state.quantity,
                    art_use : this.state.use,
                    description : this.state.description
                }

            console.log("Submit")
            console.log(request);
            UserService.requestCustomArt(request).then(res=>{
                    console.log(res.data)
                    let form_data = new FormData();
                    form_data.append('file', this.state.image, this.state.image.name);
                    form_data.append('artist_id', res.data.custom_id);
                    form_data.append('buyer_id',"10");
                    UserService.saveReferenceImage(form_data).then(response=>{
                        console.log(response);
                    }).catch(err=>{
                        console.log(err);
                    })
            }).catch(err=>{
                    console.log(err);
            })
        }


        render() {
        return(
            <form>
                <h3>Request for a Customized Art piece</h3>
                <div className="form-group">
                    <label className="firstLabel">Which type of Art do you want?</label>
                    <select
                        name="typeOfArt"
                        id="typeOfArt"
                        onChange={this.handleChange}
                        placeholder="Type of Art"
                    >
                        <option value="landscape">Landscape</option>
                        <option value="portrait">Portrait</option>
                        <option value="abstract">Abstract</option>
                        <option value="sketch">Sketch</option>
                        <option value="other">Other</option>
                    </select>
                </div>

                {/*<div className="form-group">
                    <label>Size of Art piece</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Size of Art"
                           name="sizeOfArt"
                           required="True"
                           //value={this.state.sizeOfArt}
                           onChange={this.handleChange}
                    />
                </div>*/}

                <div className="form-group">
                    <label>Medium of Paper</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Medium"
                           name="paper"
                           required="True"
                           value={this.state.paper}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Number of pieces you want</label>
                    <input type="Number"
                           className="form-control"
                           placeholder="Enter a number"
                           name="quantity"
                           required="True"
                           value={this.state.quantity}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Where do you want to put it?</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Location"
                           name="artLocation"
                           required="True"
                           value={this.state.location}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Type of Use</label>
                    <input type="password"
                           className="form-control"
                           placeholder="Usage?"
                           name="use"
                           required="True"
                           value={this.state.use}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Description</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Describe your requirement in a few words "
                           name="description"
                           required="True"
                           value={this.state.description}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Upload one reference picture</label>
                    <input type="file"
                           placeholder="Enter Address"
                           name="image"
                           required
                           id="image"
                           accept="image/png, image/jpeg"
                           onChange={this.handleImage}
                    />
                </div>

                <button type="submit"
                        className="btn btn-dark btn-lg btn-block"
                        onClick={this.handleClick}
                        >Submit</button>

            </form>
        );
    }
}
