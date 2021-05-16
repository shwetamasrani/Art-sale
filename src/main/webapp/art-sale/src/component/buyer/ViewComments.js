import React, { Component, Fragment } from 'react';
import axios from 'axios';
import ApproveComment from './ApproveComment';
export default class ViewComments extends Component {

    constructor(props){
        super(props)

        this.state = 
        {
            details: this.props.location.details,
            user_id: JSON.parse(localStorage.getItem('token')).user_id,
            comments: [],
            noComment: false
        }

        console.log("From comment",this.state.details)

        

        // this.handleChange = this.handleChange.bind(this);
        // this.onClick =this.onClick.bind(this);
        console.log("From comment",this.state.comments)

    }

    async componentDidMount() {
        await axios.get("http://localhost:8090/artistComments/getCommentsByCustomID/"+this.state.details.custom_id)
        .then(res=>{
            console.log(res.data);
            if(res.data.length == 0)
            {
                this.setState({
                    noComment: true
                })
            }
            console.log(res.data.length)
            this.setState({
                comments: res.data
            })
            
        }).catch(err=>{
            console.log(err);
        });
        console.log("From compdidmoint",this.state.comments)
    }

    render() {
        const AllComments = this.state.comments.map((comment) => {
            return (
               <Fragment>
                   
                   

                  <ApproveComment
                        key={comment.comments_id}
                        comments_id={comment.comments_id}
                        comment={comment.comments}
                        artist={comment.artistDetails}
                        status={comment.artCustomizationDetails.artistDetails3}
                        custom_id={this.state.details.custom_id}
                        history= {this.props.history}
                    />
                </Fragment>
            )
        })
        return (
            <Fragment>
            {console.log(this.state.noComment)}
                   {this.state.noComment && (
                       <div>
                            <h1>No Comments Yet!</h1>
                        </div>
                   )}
            <div className="commentContainer"> 
               {AllComments}      
            </div>
            </Fragment>
        )
    }
}
