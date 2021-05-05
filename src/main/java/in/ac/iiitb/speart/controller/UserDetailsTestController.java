package in.ac.iiitb.speart.controller;


import in.ac.iiitb.speart.model.UserDetails;
import in.ac.iiitb.speart.service.UserDetailsService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserDetailsController.class)
@WithMockUser
public class UserDetailsTestController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    UserDetails mockUserDetails = new UserDetails("kanchan", "mahajan", "kanchanmahajan67@gmail.com",
            "sm12", 94023, "India", "buyer");

//    String userJSON = {}



}
