package com.teamtreehouse.core.config;

import com.teamtreehouse.model.User;
import com.teamtreehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//This is a spring authentication object.

//Implimentation of spring security with the AuditorAware.
//Spring Data provides sophisticated support to transparently
// keep track of who created or changed an entity and the point in time
// this happened. To benefit from that functionality you have to
// equip your entity classes with auditing metadata that can be defined
// either using annotations or by implementing an interface.

//what this code is doing:
//the auditing infrastructure somehow needs to become aware of the
// current principal. To do so, we provide an AuditorAware<T> SPI interface that
// you have to implement to tell the infrastructure who the current user or system
// interacting with the application is. The generic type T defines of what type the
// properties annotated with @CreatedBy or @LastModifiedBy have to be.
public class SpringSecurityAuditorAware implements AuditorAware<User>
{
    @Autowired
    private UserService userService;

    @Override
    public User getCurrentAuditor()
    {
        User currentAuditor = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated())
        {
            currentAuditor = userService.findByUsername(authentication.getName());
        }

        return currentAuditor;
    }
}
