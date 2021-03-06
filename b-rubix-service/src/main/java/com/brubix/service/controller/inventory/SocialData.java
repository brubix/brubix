package com.brubix.service.controller.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
public class SocialData {


    @Length(min = 0, max = 255, message = "{invalid.length.facebook.handle}")
    @URL(message = "{invalid.url}")
    private String facebook;

    @Length(min = 0, max = 255, message = "{invalid.length.twitter.handle}")
    @URL(message = "{invalid.url}")
    private String twitter;

    @Length(min = 0, max = 255, message = "{invalid.length.gplus.handle}")
    @URL(message = "{invalid.url}")
    private String googlePlus;

    @Length(min = 0, max = 255, message = "{invalid.length.linkedin.handle}")
    @URL(message = "{invalid.url}")
    private String linkedin;
}
