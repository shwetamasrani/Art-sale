import axios from 'axios';

const USER_API_BASE_URL="http://artSale-spring:8099/"
class UserService{

    getUser(user)
    {
        return axios.post(USER_API_BASE_URL+"addUser/login",user);
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
        return axios.post(USER_API_BASE_URL+'addUser/userDetails',user);
    }

    saveImage(artist){
        return axios.post(USER_API_BASE_URL+"addUser/saveArtistSampleImage",artist,
            {headers: {
                    'content-type': 'multipart/form-data'
                }});
    }

    saveReferenceImage(image){
        return axios.post(USER_API_BASE_URL+"dashNavigationMenu/getArtCustmizedSampleImage",image,
            {headers: {
                    'content-type': 'multipart/form-data'
                }}
            );
    }

    requestCustomArt(request){
        return axios.post(USER_API_BASE_URL+"dashNavigationMenu/getArtCustomized",request);
    }

    viewProfile(email)
    {
        let URL = USER_API_BASE_URL+"dashNavigationMenu/getMyProfile/" + email;
        console.log(URL);
        return axios.get(URL);
    }
}
export default new UserService()   //exporting the object of this class