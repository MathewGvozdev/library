package com.mathewgv.library.mapper.factory;

import com.mathewgv.library.mapper.impl.*;

public class MapperFactory {

    private static final MapperFactory INSTANCE = new MapperFactory();

    private final AuthorCreationMapper authorCreationMapper = AuthorCreationMapper.getInstance();
    private final BookCreationMapper bookCreationMapper = BookCreationMapper.getInstance();
    private final BookMapper bookMapper = BookMapper.getInstance();
    private final BookMetaCreationMapper bookMetaCreationMapper = BookMetaCreationMapper.getInstance();
    private final BookMetaMapper bookMetaMapper = BookMetaMapper.getInstance();
    private final GenreCreationMapper genreCreationMapper = GenreCreationMapper.getInstance();
    private final PublisherCreationMapper publisherCreationMapper = PublisherCreationMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserRegistrationMapper userRegistrationMapper = UserRegistrationMapper.getInstance();

    private MapperFactory() {
    }

    public AuthorCreationMapper getAuthorCreationMapper() {
        return authorCreationMapper;
    }

    public BookCreationMapper getBookCreationMapper() {
        return bookCreationMapper;
    }

    public BookMapper getBookMapper() {
        return bookMapper;
    }

    public BookMetaCreationMapper getBookMetaCreationMapper() {
        return bookMetaCreationMapper;
    }

    public BookMetaMapper getBookMetaMapper() {
        return bookMetaMapper;
    }

    public GenreCreationMapper getGenreCreationMapper() {
        return genreCreationMapper;
    }

    public PublisherCreationMapper getPublisherCreationMapper() {
        return publisherCreationMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public UserRegistrationMapper getUserRegistrationMapper() {
        return userRegistrationMapper;
    }

    public static MapperFactory getInstance() {
        return INSTANCE;
    }
}
