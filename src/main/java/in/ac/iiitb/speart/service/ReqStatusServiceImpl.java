package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.dao.ReqStatusDao;
import in.ac.iiitb.speart.model.CustomizedRequestStatus;
import in.ac.iiitb.speart.model.CustomizedRequestStatusTrial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReqStatusServiceImpl implements ReqStatusService{

    @Autowired
    ReqStatusDao reqStatusDao;

    public void save(CustomizedRequestStatusTrial customizedRequestStatus){
        reqStatusDao.save(customizedRequestStatus);
    }

    @Override
    public List<CustomizedRequestStatusTrial> getAll(Integer custom_id) {
        return reqStatusDao.getAll(custom_id);
    }

    @Override
    public void updateReqStatus(List<CustomizedRequestStatusTrial> list) {

    }


}
