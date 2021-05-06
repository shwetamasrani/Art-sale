import React, {Component} from 'react';
import DashboardService from "../../services/DashboardService";

class Dashboard extends Component {

    constructor(props) {
        super(props);

        DashboardService.getAllPaintings().then(res=>{
            console.log(res.data);
            console.log(res.data.json());
            console.log(res.status)
        }).catch(err=>{
            console.log(err);
        });
    }

    render() {
        return(
            <p>Dashboard</p>
        )
    }
}

export default Dashboard