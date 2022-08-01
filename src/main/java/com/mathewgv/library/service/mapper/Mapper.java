package com.mathewgv.library.service.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
