package com.mathewgv.library.service.mapper.factory;

import com.mathewgv.library.service.mapper.impl.*;

public class MapperFactory {

    private static final MapperFactory INSTANCE = new MapperFactory();

    private final AuthorCreationMapper authorCreationMapper = AuthorCreationMapper.getInstance();
    private final BookMapper bookMapper = BookMapper.getInstance();
    private final BookMetaMapper bookMetaMapper = BookMetaMapper.getInstance();
    private final GenreCreationMapper genreCreationMapper = GenreCreationMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserRegistrationMapper userRegistrationMapper = UserRegistrationMapper.getInstance();
    private final OrderCreationMapper orderCreationMapper = OrderCreationMapper.getInstance();
    private final OrderMapper orderMapper = OrderMapper.getInstance();

    private MapperFactory() {
    }

    public AuthorCreationMapper getAuthorCreationMapper() {
        return authorCreationMapper;
    }

    public BookMapper getBookMapper() {
        return bookMapper;
    }

    public BookMetaMapper getBookMetaMapper() {
        return bookMetaMapper;
    }

    public GenreCreationMapper getGenreCreationMapper() {
        return genreCreationMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public UserRegistrationMapper getUserRegistrationMapper() {
        return userRegistrationMapper;
    }

    public OrderCreationMapper getOrderCreationMapper() {
        return orderCreationMapper;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    public static MapperFactory getInstance() {
        return INSTANCE;
    }
}
