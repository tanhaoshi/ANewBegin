package com.example;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig liveDaoConfig;

    private final UserDao userDao;
    private final liveDao liveDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        liveDaoConfig = daoConfigMap.get(liveDao.class).clone();
        liveDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        liveDao = new liveDao(liveDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(live.class, liveDao);
    }
    
    public void clear() {
        userDaoConfig.getIdentityScope().clear();
        liveDaoConfig.getIdentityScope().clear();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public liveDao getLiveDao() {
        return liveDao;
    }

}