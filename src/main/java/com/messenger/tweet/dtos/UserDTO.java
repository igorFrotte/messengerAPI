package com.messenger.tweet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank
    @Size(max = 100, message = "Maximum length is 100 characters for username.")
    private String username;

    @NotBlank(message = "Avatar cannot be empty!")
    private String avatar;

}