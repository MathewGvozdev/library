package com.mathewgv.library.mapper.impl;

import com.mathewgv.library.dao.DaoConnection;
import com.mathewgv.library.service.dto.PublisherDto;
import com.mathewgv.library.entity.book.Publisher;
import com.mathewgv.library.mapper.Mapper;

public class PublisherCreationMapper extends DaoConnection implements Mapper<PublisherDto, Publisher> {

    private static final PublisherCreationMapper INSTANCE = new PublisherCreationMapper();

    private PublisherCreationMapper() {
    }

    @Override
    public Publisher mapFrom(PublisherDto object) {
        return new Publisher(
                object.getTitle(),
                object.getCity()
        );
    }

    public static PublisherCreationMapper getInstance() {
        return INSTANCE;
    }
}
