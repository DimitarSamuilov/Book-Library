package com.book.library.booklibrary.library.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter{

    private LibraryDetailInterceptor libraryDetailInterceptor;

    public WebMvcConfig(LibraryDetailInterceptor libraryDetailInterceptor) {
        this.libraryDetailInterceptor = libraryDetailInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(libraryDetailInterceptor).order(Integer.MAX_VALUE).addPathPatterns("/books/add");

    }
}
