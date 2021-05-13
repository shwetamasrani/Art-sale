# Art-sale

ArtistDetailsController

url: "http://localhost:8090/artist/saveArtist" --> Saves an artist after registering as buyer.

{
    "artist_user_id" : "22",

    "category_taught" : "self", 

    "experience" : "15", 

    "type_of_art" : "still-life", 

    "sample_image_name" : "saishp",

    "category" : "paper", 

    "price" : "3900"

}

To save painting image of artist, use userdetailscontroller’s save image api after above api.


url: "http://localhost:8090/artist/getArtists" --> Get list of all artists

url: "http://localhost:8090/artist/getArtistId/{emailAdd}  --> Get artist by email address. 



=============================================================================================================
DashBoard

url : http://localhost:8090/dashboard/getPaintings   --> Get all paintings from painting_repo table.

Url : http://localhost:8090/dashboard/getArtistName/{id} --> Get painting of an artist using artist_id.



==============================================================================================================
DashBoardNavigationMenu

url :  http://localhost:8090/dashNavigationMenu/getArtCustomized  --> Post a customization request.

{
    "buyer_id" : "6",

    "artist_customizer" : "15",

    "type_of_art": "landscape",

    "paper_canvas" : "canvas",

    "art_location" : "garden",

    "quantity" : "5",
    
    "art_use" : "personal",

    "description" : "Need it urgently"
}
 
url : "http://localhost:8090/dashNavigationMenu/getArtCustmizedSampleImage" --> Post a customization sample image.(Requestparam) 
file, custom_id, buyer_id

url : "http://localhost:8090/dashNavigationMenu/getAllCustomizedOrdersUser/{buyerId}" -->  Get all customization orders of a buyer using buyer-id.

url :  "http://localhost:8090/dashNavigationMenu/getAllCustOrdersArtist/{artistId}" --> Get all customization orders done by an artist using artist-id.

url : "http://localhost:8090/dashNavigationMenu/getMyProfile/{email_add}" --> Get user profile. Returning UserArtistPaintingAPI object which consists of UserDetails object and a generic Object(For storing data from ArtistDetails and PaintingRepo details)

url : "http://localhost:8090/dashNavigationMenu/getAllCustOrders" --> Get list of all Customization Orders.

url : "http://localhost:8090/dashNavigationMenu/getAllCustomizedOrdersUser/{buyerId}" --> Get list of all customized orders by buyer.

url : "http://localhost:8090/dashNavigationMenu/getAllCustOrdersArtist/{artistId}" --> Get list of customized orders by artist.
=================================================================================================================




UserDetailsCtrlr

url : "http://localhost:8090/addUser/userDetails"  --> Register user as a buyer/artist.

{
	"first_name" : "sammu",
	"last_name" : "pratapwar",
	
    "email_address" : "sammup@gmail.com",

    "password" : "sm123",

    "contact_no" : "12345",

    "address" : "UK",

    "user_category" : "artist"

}

url : "http://localhost:8090/addUser/saveArtistSampleImage" --> saves an image while registering as an artist. (RequestParam)
file, artist_id

url : "http://localhost:8090/addUser/logout" --> Logs a user out.

url : "http://localhost:8090/addUser/login"  --> Logins a user. 
{
	"email_address" : "sm@gmail.com",
	"password" : "sm12"
}



================================================================================================================

RequestCommentsController

url : "http://localhost:8090/artistComments/getCommentsByCustomID/{custom_id}" --> Get all comments of a customization request using custom_id

url : "http://localhost:8090/artistComments/saveCommentsByArtist" --> Post a comment by an artist using custom_id, artist_id

url : "http://localhost:8090/artistComments/approveCustRequestStatus/{comm_id}" --> update status as approved using comment_id.


=====================================================================================================================

PaintingRepoController

url : "http://localhost:8090/paintings/getArtBiddingDetails/{painting_id}" --> Get Bidding details of a painting.

url : "http://localhost:8090/paintings/postBiddingRequestBuyer" --> Post a bidding request by user.

{
	"p_id" : "12",
	"bidded_price" : "87867",
	"user_id" : "32"
	
}

url : "http://localhost:8090/paintings/addExtraArtistImage" --> Post extra image of artist's painting.

{

    "category": "canvas",

    "painting_name" : "The Happiness!",

    "price" : "49000",

    "artist_id" : "16"

}

url : "http://localhost:8090/paintings/saveArtistSampleImage" --> Post image using painting_id. Painting_id can be obtained from above API. 

@RequestParam(file, painting_id)

url : "http://localhost:8090/paintings//getAllBiddingPurchasesByUserID/{bidder_conf_id}" --> Get all purchased bids of a buyer using buyer_id.

