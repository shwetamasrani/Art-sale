package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.modal.UserDetails;
import in.ac.iiitb.speart.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addUser")
public class UserDetailsController {
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/getUsers")
    public List<UserDetails> get() {
        return userDetailsService.get();
    }

//    @PostMapping("/userDetails")
    @RequestMapping(value = "/userDetails",method = RequestMethod.POST)
    public UserDetails save(UserDetails userDetails) {
        userDetailsService.save(userDetails);
        return userDetails;
    }







}
