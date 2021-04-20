package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.model.CustomizedRequestStatus;
import in.ac.iiitb.speart.model.CustomizedRequestStatusTrial;

import java.util.List;

public interface ReqStatusService {

    public void save(CustomizedRequestStatusTrial customizedRequestStatus);

    List<CustomizedRequestStatusTrial> getAll(Integer custom_id);

    void updateReqStatus(List<CustomizedRequestStatusTrial> list);
}
