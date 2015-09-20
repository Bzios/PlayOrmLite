package com.bzio.playormlite.db;

import android.content.Context;

import com.bzio.playormlite.helper.OrmHelper;
import com.bzio.playormlite.item.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

/**
 * Created by pheerawat on 20/9/2558.
 */
public class UserDbManager {

    private Context context;
    private OrmHelper ormHelper;
    private RuntimeExceptionDao<User, Integer> runtimeExceptionDao;

    public UserDbManager(Context context) {
        this.context = context;
        ormHelper = new OrmHelper(context);
        runtimeExceptionDao = ormHelper.getUserRunTimeExceptionDao();
    }

    public boolean AddUser(User user){
        Dao.CreateOrUpdateStatus createOrUpdateStatus = runtimeExceptionDao.createOrUpdate(user);
        return createOrUpdateStatus.isCreated();
    }

    public boolean UpdateUser(User user){
        Dao.CreateOrUpdateStatus createOrUpdateStatus = runtimeExceptionDao.createOrUpdate(user);
        return createOrUpdateStatus.isUpdated();
    }

    public User AddUniqueUser(User user){
        User objUser = runtimeExceptionDao.createIfNotExists(user);
        return objUser;
    }

    public List<User> getAllUser(){
        List<User> userList = runtimeExceptionDao.queryForAll();
        return userList;
    }

    public User getUserById(int id){
        User user = runtimeExceptionDao.queryForId(id);
        return user;
    }
}
