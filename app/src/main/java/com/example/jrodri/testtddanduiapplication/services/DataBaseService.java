package com.example.jrodri.testtddanduiapplication.services;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by jrodri on 7/7/17.
 */

public class DataBaseService<T extends BaseModel> {

    public boolean entityExists(T entity) {
        return entity.exists();
    }

    public boolean saveEntity(T entity) {
        return !entityExists(entity) && entity.save();
    }

    public boolean deleteEntity(T entity) {
        return entity.delete();
    }

    public boolean updateEntity(T entity) {
        return entity.update();
    }

}