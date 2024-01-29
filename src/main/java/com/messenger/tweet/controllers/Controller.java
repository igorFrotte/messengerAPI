package com.messenger.tweet.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.messenger.tweet.dtos.TweetDTO;
import com.messenger.tweet.dtos.UserDTO;
import com.messenger.tweet.models.TweetModel;
import com.messenger.tweet.models.UserModel;
import com.messenger.tweet.services.TweetService;
import com.messenger.tweet.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping
public class Controller {

    final UserService userService;
    final TweetService tweetService;

    public Controller(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> userRegister(@RequestBody @Valid UserDTO body) {

        Optional<UserModel> user = userService.save(body);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exist!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/tweets")
    public ResponseEntity<Object> postTweets(@RequestBody @Valid TweetDTO body) {

        Optional<TweetModel> tweet = tweetService.save(body);

        if(!tweet.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserId invalid!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }

    @GetMapping("/tweets")
    public ResponseEntity<List<TweetModel>> getTweets() {

        List<TweetModel> tweets = tweetService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }

    @GetMapping("/tweets/user/{userId}")
    public ResponseEntity<Object> filterTweetsByUser(@PathVariable("userId") Long userId) {

        Optional<List<TweetModel>> tweets = tweetService.findTweetsByUserId(userId);

        if(!tweets.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("This user was not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }

}