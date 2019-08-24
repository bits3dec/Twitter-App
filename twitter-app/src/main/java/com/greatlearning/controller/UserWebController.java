package com.greatlearning.controller;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.greatlearning.model.Tweet;
import com.greatlearning.model.User;
import com.greatlearning.service.UserService;

@Controller
public class UserWebController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String displayHomePage(){
        return "welcome";
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<User> listOfUsers = this.userService.getAllUsers();
        model.addAttribute("users", listOfUsers);
        return "list";
    }

    @GetMapping("/register")
    public String registrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/submit")
    public String registerUser( @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        this.userService.saveUser(user);
        return "redirect:/users";//redirect the url
        //dont use the redirect: forwarded and the url will not change
    }

    @GetMapping("/users/{id}/tweets")
    public String getAllTweetsByUserId(@PathVariable("id") long userId, Model model){
        Set<Tweet> tweets = this.userService.getAllTweetsByUser(userId);
        model.addAttribute("tweets", tweets);
        return "tweets";
    }
    
    @GetMapping("/postTweet")
    public String postTweet(Model model){
        model.addAttribute("tweet", new Tweet());
        return "postTweet";
    }
    
    @PostMapping("/postTweet/submit")
    public String postTweet( @Valid @ModelAttribute("tweet")Tweet tweet, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return "postTweet";
    	}
    	this.userService.postTweet(30, tweet);
    	return "redirect:/postTweet";
    }
    
//    @PostMapping("/postTweet/submit")
//    public String postTweet( @Valid @ModelAttribute("tweet") Tweet tweet,  @Valid @ModelAttribute("user") long userId, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "post-tweet";
//        }
//        this.userService.addTweet(userId, tweet);
//        return "redirect:/post-tweet";// redirect the url
//        //dont use the redirecr: forwarded and the url will not change
//    }
//    /*
//       @PostMapping
//        method which accepts twee from the form
//        and saves to the database
//        tweet message
//        size: max 150characters
//
//     */
    
}