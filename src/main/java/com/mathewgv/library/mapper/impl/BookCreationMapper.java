package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.dao.book.impl.BookMetaDaoImpl;
import com.mathewgv.library.dao.book.impl.PublisherDaoImpl;
import com.mathewgv.library.dao.factory.DaoFactory;
import com.mathewgv.library.service.dto.BookDto;
import com.mathewgv.library.entity.book.Book;
import com.mathewgv.library.mapper.Mapper;

public class BookCreationMapper extends DaoConnection implements Mapper<BookDto, Book> {

    private static final BookCreationMapper INSTANCE = new BookCreationMapper();

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private BookCreationMapper() {
    }

    @Override
    public Book mapFrom(BookDto object) {
        setConnectionForDependencies();
        return new Book(
                daoFactory.getPublisherDao().findById(object.getPublisherId()).orElse(null),
                daoFactory.getBookMetaDao().findById(object.getBookMetaId()).orElse(null),
                object.getPages(),
                object.getPublicationYear()
        );
    }

    private void setConnectionForDependencies() {
        PublisherDaoImpl.getInstance().setConnection(connection.get());
        BookMetaDaoImpl.getInstance().setConnection(connection.get());
    }

    public static BookCreationMapper getInstance() {
        return INSTANCE;
    }
}
