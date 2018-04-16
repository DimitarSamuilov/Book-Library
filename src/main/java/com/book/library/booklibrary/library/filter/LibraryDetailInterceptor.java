package com.book.library.booklibrary.library.filter;

import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Optional;

@Component
public class LibraryDetailInterceptor extends HandlerInterceptorAdapter {

    public static final String NULL_LIBRARY_DETAILS = "You must fill details before you can adda a book";

    private UserServiceInterface userService;

    public LibraryDetailInterceptor(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SecurityContextImpl secContext = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        // TODO: 15.4.2018 г. Just check if library details are entered before allowing Library to add book
        if (secContext != null) {
            UserDetails principal = (UserDetails) secContext.getAuthentication().getPrincipal();
            String username = principal.getUsername();
            Optional<User> userOptional = this.userService.getUserByUsername(username);
            if (userOptional.isPresent()) {
                if (userOptional.get().getLibrary() == null) {
                    System.out.println(userOptional.get());
                    response.sendRedirect(
                                    "/libraries/editDetails/" +
                                            userOptional.get().getUsername() +
                                            "?message=" +
                                            LibraryDetailInterceptor.NULL_LIBRARY_DETAILS);
                    return true;
                }
            }
        }
        return true;
    }
}
