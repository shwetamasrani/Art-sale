import React, { Component,useHistory } from 'react'

class ArtRequest extends Component {

    constructor(props){
        super(props);
        this.state = {
            custom_id: this.props.custom_id,
            buyer : this.props.buyer,
            refimg: this.props.refimg,
            type:this.props.type,
            paper:this.props.paper,
            quant:this.props.quant,
            loc:this.props.loc,
            use:this.props.use,
            desc: this.props.desc
            
        }
        this.onClick =this.onClick.bind(this);
        console.log(this.props.history);
    }

    onClick(e) {
        this.props.history.push({pathname:'/comment',
            details: this.state
        });

    }

    render() {
        return (
            <div className="paintbox" id={this.state.key}>
                {/* <div href="" style={{width: '30rem'}} ></div> */}
                    {/* <img src={"data:image/jpg;base64," + this.state.refimg} style={{width: '18rem'}}/> */}
                    <div className="details">
                        <p>Customer Name:{this.state.buyer}</p>
                        <p>Type of Art: {this.state.type}</p>
                        <p>Meduim of Paper: {this.state.paper}</p>
                        {/* <p>Number of Art Piece: {this.state.quant}</p> */}
                        {/* <p>Location to put art pieces: {this.state.loc}</p> */}
                        {/* <p>Purpose: {this.state.use}</p> */}
                        {/* <p>Description: {this.state.desc}</p> */}
                        <button className="btn btn-dark btn-lg btn-block"
                                onClick={this.onClick}
                               >
                            <p>View Details</p>
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
export default ArtRequest