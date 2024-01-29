package com.messenger.tweet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.messenger.tweet.dtos.TweetDTO;
import com.messenger.tweet.models.TweetModel;
import com.messenger.tweet.models.UserModel;
import com.messenger.tweet.repositories.TweetRepository;
import com.messenger.tweet.repositories.UserRepository;

@Service
public class TweetService {

    final TweetRepository tweetRepository;
    final UserRepository userRepository;

    TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public Optional<TweetModel> save(TweetDTO dto){

        Optional<UserModel> user = userRepository.findById(dto.getUserId());

        if(!user.isPresent()){
            return Optional.empty();
        }

        TweetModel tweet = new TweetModel(dto, user.get());
        return Optional.of(tweetRepository.save(tweet));
    }

    public List<TweetModel> findAll(){
        return tweetRepository.findAll();
    }

    public Optional<List<TweetModel>> findTweetsByUserId(Long userId){

        Optional<UserModel> user = userRepository.findById(userId);

        if(!user.isPresent()){
            return Optional.empty();
        }

        List<TweetModel> tweets = tweetRepository.findTweetsByUserId(userId);

        return Optional.ofNullable(tweets);
    }
}