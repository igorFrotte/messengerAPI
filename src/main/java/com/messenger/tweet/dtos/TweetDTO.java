package com.messenger.tweet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TweetDTO {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 280, message = "Maximum length for message is 280 characters!")
    private String tweet;

}