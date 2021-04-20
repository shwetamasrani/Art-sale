package in.ac.iiitb.speart.dao;

import in.ac.iiitb.speart.model.CustomizedRequestStatus;
import in.ac.iiitb.speart.model.CustomizedRequestStatusTrial;

import java.util.List;

public interface ReqStatusDao {

    public void save(CustomizedRequestStatusTrial customizedRequestStatus);

    public List<CustomizedRequestStatusTrial> getAll(Integer custom_id) ;

    public void updateReqStatus(List<CustomizedRequestStatusTrial> list) ;

    }
