package com.book.library.booklibrary.order.config;

import com.book.library.booklibrary.user.model.entity.User;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class UserToUsernameConverter implements Converter<User ,String> {
    @Override
    public String convert(MappingContext<User, String> mappingContext) {
        User source= mappingContext.getSource();
        String output=source.getUsername();

        return output;
    }
}
