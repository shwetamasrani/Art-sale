import React, { Component } from 'react'
import axios from 'axios';
import { Fragment } from 'react';
export default class MyBids extends Component {
    constructor(props){
        super(props)

        this.state={
            array: [],
            user_id: JSON.parse(localStorage.getItem('token')).user_id,
            purchases: [],
            bids: []
        }
        // this.myFunction=this.myFunction.bind(this)

        console.log(this.state.user_id)
        const url = "http://localhost:8090/paintings/getAllBiddings/"+this.state.user_id;
        
        axios.get(url,{
            headers: {'Content-Type': 'application/json'}
        }).then(res=>{
            console.log(res.data);
            console.log(res.status)
            console.log(res.data[0][5])
            this.setState({
                array: res.data
                // purchases: res.data
            })

            let localBids = []
            let localPurchase = []
            for(let i=0;i!=this.state.array.length;i+=1){
                console.log("New",this.state.array[i])
                if(this.state.array[i][3] && this.state.array[i][3]==this.state.user_id)
                {
                    localPurchase.push(this.state.array[i])
                
                }
                else{
                    localBids.push(this.state.array[i])
                    // this.setState({
                    //     bids: localBids
                    // })
                    
                }
            }
            this.setState({
                purchases: localPurchase,
                bids: localBids
            })
            //this.props.history.push({pathname:'/custom-art-request'});
            console.log("Bids",this.state.bids)
            console.log("Purchases",this.state.purchases)
        }).catch(err=>{
            console.log(err);
        });

        
        // console.log("array for each",this.state.array);
        // this.state.array.forEach(this.myFunction);
       

    }

    // myFunction=(element,index)=> {
    //     console.log(element);
    //     console.log(index);
    //     console.log("Now checking")
    //     if(element[3]){
    //         console.log(element)
    //         // if(element[3] == this.state.user_id){
    //         //     let removed = this.state.array.splice(index, 1);
    //         //     this.state.purchases.push(removed)
    //         // }
    //     }
    //     else{
    //         console.log("mm",element)
    //         let removed = this.state.array.splice(index, 1);
    //         this.state.bids.push(removed)
    //     }
    // };


    render() {
        const Bids = this.state.bids.map((bid) => {
        return (
            <Fragment>
            
            <div className="paintbox" >
                    <div className="details">

                        {/* { bid[3] == this.state.user_id ? this.state.purchases.push(bid) : ( */}
                            <div>
                                <p>Painitng:</p>
                                <img src={"data:image/jpg;base64," + bid[1]} style={{width: '18rem'}} alt="No"/>

                                <p>Category: {bid[4]}</p>
                                
                                <p>Price: {bid[6]}</p>
                                
                                <p>Highest Bidded Price: {bid[7]}</p>
                                {/* <p>Painting Name: {bid[9]}</p> */}
                                
                                <p>Your Bidded Price: {bid[11]}</p>
                                
                                <p>Bidded Date: {bid[12]}</p>
                                
                                <p>Bidding End Date: {bid[5]}</p>
                            </div>
                        {/* )} */}
                        
                        
                    </div>
                   
            </div>
            </Fragment>
        )
        })

        const Purchases = this.state.purchases.map((bid) => {
            return (
                <Fragment>
                    
                
                <div className="paintbox" >
                        <div className="details">
                                <div>
                                    <p>Painitng:</p>
                                    <img src={"data:image/jpg;base64," + bid[1]} style={{width: '18rem'}}/>
                                    <p>Category: {bid[4]}</p>
                                    <p>Price: {bid[6]}</p>
                                    <p>Highest Bidded Price: {bid[7]}</p>
                                    {/* <p>Painting Name: {bid[9]}</p> */}
                                    <p>Your Bidded Price: {bid[11]}</p>
                                    <p>Bidded Date: {bid[12]}</p>
                                    <p>Bidding End Date: {bid[5]}</p>
                                    <p>Date of Purchase: {bid[8]}</p>
                                </div>
                        </div>
                       
                </div>
                </Fragment>
            )
            })


        return (
            <div>
                    
                    {this.state.bids.length == 0 ? null : (
                        <div>
                            <h1 style={{textAlign:"center"}}>Current Bids</h1>  
                        </div>  
                    )}  
                    
                    <div className="container">
                        {Bids}
                    </div>

                    {this.state.purchases.length == 0 ? null : (
                        <div>
                            <h1 style={{textAlign:"center"}}>Your Purchases</h1>  
                        </div>  
                    )}  
                    <div className="container">
                        {Purchases}
                    </div>

            </div>
        )
        }
}
