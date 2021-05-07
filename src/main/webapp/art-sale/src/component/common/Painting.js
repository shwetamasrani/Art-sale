import React, {Component} from 'react';
import DashboardService from "../../services/DashboardService";

class Painting extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.id,
            img: this.props.img,
            name: this.props.name,
            price: this.props.price,
            artist: this.props.artist
        }
    }

    render() {
        return(
            <div className="Products" href="" style={{width: '30rem'}}>
                <img src={this.state.img} style={{width: '18rem'}}/>
                <h3>{this.state.name}</h3>
                <p>Artist:{this.state.artist}</p>
                <p>Price:Rs.{this.state.price}</p>
                {/*<Link to={{pathname:'/BookDetails',bookId:this.state.id,userId:this.state.userId}}>*/}
                {/*    <button className="viewDetails"> View Details</button>*/}
                {/*</Link>*/}
            </div>
        )
    }
}

export default Painting