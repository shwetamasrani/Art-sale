import React, {Component} from 'react';

class CustomArt extends Component {
    constructor(props) {
        super(props);

        this.state = {
            sizeOfArt: "",
            paper: "",
            quantity: "",
            location: "",
            usage: "",
            reference: "",
            description: ""

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

    handleClick(event){
        console.log("Submit")
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

                <div className="form-group">
                    <label>Size of Art piece</label>
                    <input type="text"
                           className="form-control"
                           placeholder="Size of Art"
                           name="sizeOfArt"
                           required="True"
                           value={this.state.sizeOfArt}
                           onChange={this.handleChange}
                    />
                </div>

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
                    <input type="email"
                           className="form-control"
                           placeholder="Location"
                           name="location"
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
                           name="usage"
                           required="True"
                           value={this.state.usage}
                           onChange={this.handleChange}
                    />
                </div>

                <div className="form-group">
                    <label>Upload a reference picture</label>
                    <input type="file"
                           placeholder="Reference Picture"
                           name="art"
                           required="False"
                           value={this.state.reference}
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


                <button type="submit"
                        className="btn btn-dark btn-lg btn-block"
                        onClick={this.handleClick}
                        >Submit</button>

            </form>
        )
    }
}

export default CustomArt