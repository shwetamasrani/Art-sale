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

url: "http://localhost:8090/artist//getArtistId/{emailAdd}  --> Get artist by email address. 




DashBoard

url : http://localhost:8090/dashboard/getPaintings   --> Get all paintings from painting_repo table.

Url : http://localhost:8090/dashboard/getArtistName/{id} --> Get painting of an artist using artist_id.




DashBoardNavigationMenu

url :  http://localhost:8090/dashNavigationMenu/getArtCustomized  --> Post a customization request. 
url : http://localhost:8090/dashNavigationMenu/getArtCustmizedSampleImage --> Post a customization sample image. 


url : http://localhost:8090/dashNavigationMenu/getAllCustomizedOrdersUser/{buyerId} -->  Get all customization orders of a buyer using buyer-id.

url :  http://localhost:8090/dashNavigationMenu/getAllCustOrdersArtist/{artistId} --> Get all customization orders done by an artist using artist-id.







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

url : 

url : "http://localhost:8090/addUser/login"  --> Logins a user. 
{
	"email_address" : "sm@gmail.com",
	"password" : "sm12"
}




