import axios from 'axios';

const USER_API_BASE_URL="http://localhost:8090/addUser/userDetails"
class UserService{

    getUser(user)
    {
        return axios.post("http://localhost:8090/addUser/login",user);   //get the data from the API mentioned
    }

    createUser(user)
    {
        console.log(user);
        return axios.post('http://localhost:8090/addUser/userDetails',user);
    }
}
export default new UserService()   //exporting the object of this class