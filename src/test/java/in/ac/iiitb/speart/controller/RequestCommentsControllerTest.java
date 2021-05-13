package in.ac.iiitb.speart.controller;


import in.ac.iiitb.speart.model.*;
import in.ac.iiitb.speart.service.RequestCommentsService;
import in.ac.iiitb.speart.service.RequestCommentsServiceImpl;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class RequestCommentsControllerTest {

    private RequestCommentsController requestCommentsController;

    @Mock
    private RequestCommentsService requestCommentsService;


    @BeforeMethod
    private void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        this.requestCommentsController =
                new RequestCommentsController(this.requestCommentsService);


    }

    @Test
    public void happyTestReqController(){
        List<ArtCustomizationCommentsTrial> expected = new ArrayList<>();
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 1));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 2));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 3));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 4));
        Mockito.when(this.requestCommentsService.getAllCommentsCustomID(1)).thenReturn(expected);

        final List<ArtCustomizationCommentsTrial> actual = requestCommentsController.getAllCommentsByCustomID(1);

        Mockito.verify(this.requestCommentsService).getAllCommentsCustomID(1);
        Assert.assertEquals(actual, expected);


    }

    @Test
    public void failureTestReqController(){
        List<ArtCustomizationCommentsTrial> expected = new ArrayList<>();
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 1));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 2));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 3));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 4));

        List<ArtCustomizationCommentsTrial> expected1 = new ArrayList<>();
        expected1.add(this.getArtCustomizationCommentsTrial("Need it", 1));
        expected1.add(this.getArtCustomizationCommentsTrial("Need it", 2));
        expected1.add(this.getArtCustomizationCommentsTrial("Need it", 3));

        Mockito.when(this.requestCommentsService.getAllCommentsCustomID(1)).thenReturn(expected);

        final List<ArtCustomizationCommentsTrial> actual = requestCommentsController.getAllCommentsByCustomID(1);

        Mockito.verify(this.requestCommentsService).getAllCommentsCustomID(1);
        Assert.assertNotEquals(actual, expected1);


    }

    private ArtCustomizationCommentsTrial getArtCustomizationCommentsTrial(final String comments, final int id) {
        ArtCustomizationCommentsTrial artCustomizationCommentsTrial = new ArtCustomizationCommentsTrial();
        artCustomizationCommentsTrial.setComments(comments);
        artCustomizationCommentsTrial.setArtistDetails(this.getArtistDetails(id));
        artCustomizationCommentsTrial.setArtCustomizationDetails(this.getArtCustomDetails());
        return artCustomizationCommentsTrial;
    }

    private ArtCustomTrial getArtCustomDetails() {
        final ArtCustomTrial artCustomTrial = new ArtCustomTrial();
        artCustomTrial.setPaper_canvas("Paper");
        return artCustomTrial;

    }

    private ArtistDetails getArtistDetails(final int id) {
        final ArtistDetails artistDetails = new ArtistDetails();
        artistDetails.setExperience(8);
        artistDetails.setArtist_id(id);
        artistDetails.setCategory_taught("self");
        artistDetails.setType_of_art("landscape");
        return artistDetails;
    }

    @Test
    public void happyTestPostReqController(){
        List<ArtCustomizationCommentsTrial> expected = new ArrayList<>();
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 1));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 2));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 3));
        expected.add(this.getArtCustomizationCommentsTrial("Need it", 4));
        ArtCustomizationCommentsTrial artCustomizationCommentsTrial = new ArtCustomizationCommentsTrial();
        artCustomizationCommentsTrial.setArtistDetails(getArtistDetails(6));
        artCustomizationCommentsTrial.setComments("Artist 6 can do it.");
        artCustomizationCommentsTrial.setArtCustomizationDetails(getArtCustomDetails());
        artCustomizationCommentsTrial.setReqStatus(ReqStatus.PENDING);
        artCustomizationCommentsTrial.setComments_id(80);
        RequestCommentsAPI requestCommentsAPI = new RequestCommentsAPI();
        requestCommentsAPI.setComments("Artist 6 can do it."); requestCommentsAPI.setStatus_custom_id(11);requestCommentsAPI.setStatus_artist_id(6);
//        Mockito.when(this.requestCommentsService.saveCommentsByAnArtist(artCustomizationCommentsTrial.getComments(), 11, 6)).thenReturn(artCustomizationCommentsTrial);
//
//        ResponseEntity<ArtCustomizationCommentsTrial>(artCustomizationCommentsTrial, HttpStatus.ACCEPTED) = requestCommentsController.saveCommentsByArtist(requestCommentsAPI);
//
//        Mockito.verify(this.requestCommentsService).getAllCommentsCustomID(1);
//        Assert.assertEquals(actual, expected);

    }


}
