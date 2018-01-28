package com.acme.recipes.database.util;

import android.arch.lifecycle.LiveData;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmObject;

public class RealmLiveData <T extends RealmModel> extends LiveData<T> {

    private T model;

    private RealmChangeListener<T> listener;

    public RealmLiveData(T model) {
        this.model = model;

        listener = update -> setValue(update);

    }

    @Override
    protected void onActive() {
        super.onActive();
        RealmObject.addChangeListener(model, listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        RealmObject.removeChangeListener(model, listener);
    }

}