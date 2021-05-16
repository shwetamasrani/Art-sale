import React, {Component} from 'react';
import DashboardService from "../../services/DashboardService";
import Painting from "./Painting";

class Dashboard extends Component {

    constructor(props) {
        super(props);
        this.state ={
            paintings: []
        }

        DashboardService.getAllPaintings().then(res=>{
            console.log(res.data);
            console.log(res.status)
            this.setState({
                paintings: res.data
            })
        }).catch(err=>{
            console.log(err);
        });
    }

    render() {

        const paintings = this.state.paintings.map((painting) => {
            return (
                <Painting
                    key={painting.p_id}
                    p_id = {painting.p_id}
                    name={painting.painting_name}
                    artist={painting.artistDetails.userDetails.first_name +" "+ painting.artistDetails.userDetails.last_name}
                    price={painting.price}
                    img={painting.painting_image}
                    bidEnd = {painting.bidding_end_date}
                    category = {painting.category}
                    highestBid = {painting.highest_price}
                    history={this.props.history}
                />
            )
        })
        return (
            <div>

                    <div className="container">
                        {paintings}
                    </div>
            </div>
        )

    }
}

export default Dashboard