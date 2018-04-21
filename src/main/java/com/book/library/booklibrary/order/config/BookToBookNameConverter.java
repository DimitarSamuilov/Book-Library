package com.book.library.booklibrary.order.config;

import com.book.library.booklibrary.library.model.entity.Book;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class BookToBookNameConverter implements Converter<Book,String> {
    @Override
    public String convert(MappingContext<Book, String> mappingContext) {
        Book book = mappingContext.getSource();

        return book.getName();
    }
}
