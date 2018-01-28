package com.brubix.service.event;

import com.brubix.entity.identity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Sanjaya on 28/01/18.
 */
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private User user;

    public OnRegistrationCompleteEvent(User user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
}
