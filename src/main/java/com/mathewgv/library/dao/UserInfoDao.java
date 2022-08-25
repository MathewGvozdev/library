package com.mathewgv.library.dao;

import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoDao extends Dao<Integer, UserInfo> {

    Optional<UserInfo> findInfoByUserId(Integer userId) throws DaoException;

    List<UserInfo> findAllWithLimit(Integer page, Integer limit) throws DaoException;
}
