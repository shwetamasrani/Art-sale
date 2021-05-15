import axios from 'axios';

const USER_API_BASE_URL="http://artSale-spring:8099/"
class DashboardService{

    getAllPaintings()
    {
        return axios.get("/dashboard/getPaintings");

    }

}
export default new DashboardService()   //exporting the object of this class