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
            bidEnd: this.props.bidEnd
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
                        <p>Artist:{this.state.artist}</p>
                        <p>Price:Rs.{this.state.price}</p>
                        { this.state.bidEnd && (
                                <p>Bidding ends: {this.state.bidEnd}</p>
                        )}
                        <button className="btn btn-dark btn-lg btn-block"
                                onClick={this.bid}
                            >Bid Price</button>
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