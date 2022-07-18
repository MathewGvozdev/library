package com.mathewgv.library.dao.user;

import com.mathewgv.library.dao.Dao;
import com.mathewgv.library.dao.exception.DaoException;
import com.mathewgv.library.entity.user.UserInfo;

import java.util.Optional;

public interface UserInfoDao extends Dao<Integer, UserInfo> {

    Optional<UserInfo> findInfoByUserId(Integer userId) throws DaoException;
}
