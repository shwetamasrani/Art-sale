import React, {Component} from 'react';
import DashboardService from "../../services/DashboardService";
import '../style/Painting.css';
import axios from 'axios';
class Painting extends Component {

    constructor(props) {
        super(props);
        this.state = {
            key: this.props.p_id,
            img: this.props.img,
            name: this.props.name,
            price: this.props.price,
            artist: this.props.artist,
            bidEnd: this.props.bidEnd,
            category: this.props.category,
            size: ['12 inch X 14 inch', '10 inch X 12 inch', '8 inch X 8 inch', '24 inch X 18 inch']
            .find((_, i, ar) => Math.random() < 1 / (ar.length - i)),
            highestBid: this.props.highestBid,
            bid: false,
            user_id: ""

        }

        this.bid = this.bid.bind(this);
        this.handleClick=this.handleClick.bind(this);
        
    }

    handleClick(e){
        //e.preventdefault()
        this.setState({
            bid: true
        })
    }

    async bid(event){
        event.preventDefault();
        const bidprice = document.getElementById('bidprice').value;
        console.log("Bidprice",bidprice)

        let req = {
                p_id : this.state.key,
                bidded_price : bidprice,
                user_id : JSON.parse(localStorage.getItem('token')).user_id
        }
        this.setState({
            user_id: req.user_id
        })
        console.log(req);

        let url ="http://localhost:8090/paintings/postBiddingRequestBuyer";

        await axios.post(url,JSON.stringify(req),{
            headers: {'Content-Type': 'application/json'}
        }).then(res=>{
            console.log(res.data);
            console.log(res.status)
            this.props.history.push({pathname:'/my-bids'});
            
        }).catch(err=>{
            console.log(err);
        });
        
    }

    render() {
        return(
            <div className="paintbox" id={this.state.key}>
                {/* <div href="" style={{width: '30rem'}} ></div> */}
                    <img src={"data:image/jpg;base64," + this.state.img} style={{width: '18rem'}}/>
                    <div className="details">
                        <p>Painting Name:{this.state.name}</p>
                        <p>Category: {this.state.category}</p>
                        <p>Size: {this.state.size}</p>
                        <p>Artist: {this.state.artist}</p>
                        <p>Price: Rs.{this.state.price}</p>

                        {this.state.highestBid && (
                            <p>Highest Bid: Rs. {this.state.highestBid}</p>
                        )}
                        
                        { this.state.bidEnd && (
                                <p>Bidding ends: {this.state.bidEnd}</p>
                        )}


                        {/* <div style={{display: this.state.bid === true ? "block" : "none"}}> */}
                        {this.state.bid && (
                                <form onSubmit={this.bid}>
                                    <div className="form-group">
                                        <label>Enter your bid price:</label>
                                        <input type="text"
                                            // id={this.state.key+"bidprice"}
                                            id = "bidprice"
                                            className="form-control"
                                            placeholder="Enter Price"
                                            name="bidprice"
                                            required="True"
                                            //value={this.state.password}
                                            // ref={passwordInputRef}
                                            //onChange={this.handleChange}
                                        />
                                    </div>
                                    <button 
                                            className="btn btn-dark btn-lg btn-block"
                                            // onClick={this.bid}
                                            // type="submit"
                                    >Submit</button>
                                </form>
                            )}
                            
                        {/* </div> */}
                        
                        <button className="btn btn-dark btn-lg btn-block"
                                onClick={this.handleClick}
                                style={{display: this.state.bid === false ? "block" : "none"}}
                                >
                            {this.state.highestBid && (
                                <p>Bid your price</p>
                            )}
                            {!this.state.highestBid && (
                                <label>Be the first to Bid!</label>
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