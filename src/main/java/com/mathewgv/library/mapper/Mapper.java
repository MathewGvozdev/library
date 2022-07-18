package com.mathewgv.library.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
