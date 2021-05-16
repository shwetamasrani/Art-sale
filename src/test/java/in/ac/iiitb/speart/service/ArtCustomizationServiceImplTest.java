package in.ac.iiitb.speart.service;

import in.ac.iiitb.speart.controller.RequestCommentsController;
import in.ac.iiitb.speart.dao.ArtCustomizationDao;
import in.ac.iiitb.speart.dao.ReqStatusDao;
import in.ac.iiitb.speart.model.ArtCustomTrial;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ArtCustomizationServiceImplTest {
    @Mock
    private ArtCustomizationDao artCustomizationDao;

    @Mock
    private ReqStatusDao reqStatusDao;

    private ArtCustomizationService artCustomizationService;

    @BeforeMethod
    private void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        this.artCustomizationService = new ArtCustomizationServiceImpl(this.reqStatusDao, this.artCustomizationDao);
    }

    @Test
    public void test(){
        List<ArtCustomTrial> artCustomTrials = new ArrayList<>();
        Mockito.when(this.artCustomizationDao.get(1)).thenReturn(artCustomTrials);
        List<ArtCustomTrial> artCustomTrials1 = this.artCustomizationService.get(1);
        Mockito.verify(this.artCustomizationDao).get(1);
        Assert.assertEquals(artCustomTrials, artCustomTrials1);
    }



}
