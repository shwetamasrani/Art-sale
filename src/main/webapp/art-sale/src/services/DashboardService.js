import axios from 'axios';

class DashboardService{

    getAllPaintings()
    {
        return axios.get("http://localhost:8090/dashboard/getPaintings");

    }

}
export default new DashboardService()   //exporting the object of this class