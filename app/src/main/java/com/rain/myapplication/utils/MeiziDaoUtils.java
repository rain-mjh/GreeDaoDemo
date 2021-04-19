package com.rain.myapplication.utils;

import android.content.Context;
import android.util.Log;

import com.rain.myapplication.entity.Meizi;
import com.rain.rxjavaretrofitdemo.entity.MeiziDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MeiziDaoUtils {
    private static final String TAG = MeiziDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public MeiziDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param meizi
     * @return
     */
    public boolean insertMeizi(Meizi meizi){
        boolean flag = false;
        flag = mManager.getDaoSession().getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert Meizi :" + flag + "-->" + meizi.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<Meizi> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Meizi meizi : meiziList) {
                        mManager.getDaoSession().insertOrReplace(meizi);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param meizi
     * @return
     */
    public boolean updateMeizi(Meizi meizi){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param meizi
     * @return
     */
    public boolean deleteMeizi(Meizi meizi){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(Meizi.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Meizi> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(Meizi.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Meizi queryMeiziById(long key){
        return mManager.getDaoSession().load(Meizi.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<Meizi> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Meizi.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<Meizi> queryMeiziByQueryBuilder(long id){
        QueryBuilder<Meizi> queryBuilder = mManager.getDaoSession().queryBuilder(Meizi.class);
        return queryBuilder.where(MeiziDao.Properties._id.eq(id)).list();
    }
}
