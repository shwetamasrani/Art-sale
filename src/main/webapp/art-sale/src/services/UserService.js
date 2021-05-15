import axios from 'axios';

const USER_API_BASE_URL="http://artSale-spring:8099/"
class UserService{

    getUser(user)
    {
        return axios.post("/addUser/login",user);
        // {headers: {
            //     Accept: 'application/json',
            //         'Content-Type': 'application/json',
            //         'Access-Control-Allow-Origin': '*',
            // }},
              //get the data from the API mentioned
    }

    createUser(user)
    {
        console.log(user);
        return axios.post('/addUser/userDetails',user);
    }

    saveImage(artist){
        return axios.post("/addUser/saveArtistSampleImage",artist,
            {headers: {
                    'content-type': 'multipart/form-data'
                }});
    }

    saveReferenceImage(image){
        return axios.post("/dashNavigationMenu/getArtCustmizedSampleImage",image,
            {headers: {
                    'content-type': 'multipart/form-data'
                }}
            );
    }

    requestCustomArt(request){
        return axios.post("/dashNavigationMenu/getArtCustomized",request);
    }

    viewProfile(email)
    {
        let URL = "/dashNavigationMenu/getMyProfile/" + email;
        console.log(URL);
        return axios.get(URL);
    }
}
export default new UserService()   //exporting the object of this class