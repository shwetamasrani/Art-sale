import React, {Component} from 'react';
import DashboardService from "../../services/DashboardService";
import '../style/Painting.css';

class Painting extends Component {

    constructor(props) {
        super(props);
        this.state = {
            key: this.props.id,
            img: this.props.img,
            name: this.props.name,
            price: this.props.price,
            artist: this.props.artist,
            bidEnd: this.props.bidEnd,
            category: this.props.category,
            size: ['12 inch X 14 inch', '10 inch X 12 inch', '8 inch X 8 inch', '24 inch X 18 inch']
            .find((_, i, ar) => Math.random() < 1 / (ar.length - i)),
            highestBid: this.props.highestBid

        }

        this.bid = this.bid.bind(this);
        
    }

    bid = (event) =>{


    }

    render() {
        return(
            <div className="inner" id={this.state.key}>
                {/* <div href="" style={{width: '30rem'}} ></div> */}
                    <img src={"data:image/jpg;base64," + this.state.img} style={{width: '18rem'}}/>
                    <div className="details">
                        <p>Paining Name:{this.state.name}</p>
                        <p>Category: {this.state.category}</p>
                        <p>Size: {this.state.size}</p>
                        <p>Artist:{this.state.artist}</p>
                        <p>Price: Rs.{this.state.price}</p>
                        {this.state.highestBid && (
                            <p>Highest Bid: Rs. {this.state.highestBid}</p>
                        )}
                        
                        { this.state.bidEnd && (
                                <p>Bidding ends: {this.state.bidEnd}</p>
                        )}
                        <button className="btn btn-dark btn-lg btn-block"
                                onClick={this.bid}>
                            {this.state.highestBid && (
                                <p>Bid Price</p>
                            )}
                            {!this.state.highestBid && (
                                <label>Bid First</label>
                            )}
                        </button>
                    </div>
                    {/*<Link to={{pathname:'/BookDetails',bookId:this.state.id,userId:this.state.userId}}>*/}
                    {/*    <button className="viewDetails"> View Details</button>*/}
                    {/*</Link>*/}
               {/* </div> */}
            </div>
        )
    }
}

export default Painting