package in.ac.iiitb.speart.controller;

import in.ac.iiitb.speart.modal.UserDetails;
import in.ac.iiitb.speart.service.UserDetailsService;
import org.apache.catalina.User;
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
        System.out.println("Fname:"+ userDetails.getFirst_name());
        userDetailsService.save(userDetails);
        return userDetails;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Status login(UserDetails user){
       String email = user.getEmail_address();
       String pass = user.getPassword();
       System.out.println("email"+ email+" "+pass);
       List<UserDetails> li = userDetailsService.get();
       System.out.println("Size" + li.size());

       for(UserDetails check : li){
           if(check.getEmail_address().equals(email) && check.getPassword().equals(pass)){
               System.out.println("flag before:"+ check.isLog_status());
               check.setLog_status(true);
               userDetailsService.save(check);
//               System.out.println("flag after:"+ check.isLog_status());
               return Status.SUCCESS;
           }
       }

       return Status.FAILURE;
    }


    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public Status logout(UserDetails user){
        String email = user.getEmail_address();
        String pass = user.getPassword();
        List<UserDetails> li = userDetailsService.get();
        for(UserDetails check : li){
            if(check.getEmail_address().equals(email) && check.getPassword().equals(pass)){
                System.out.println("flag before:"+ check.isLog_status());
                check.setLog_status(Boolean.FALSE);
                System.out.println("flag after:"+ check.isLog_status());
                userDetailsService.save(check);
                return Status.SUCCESS;
            }

        }

        return Status.FAILURE;
    }





}
