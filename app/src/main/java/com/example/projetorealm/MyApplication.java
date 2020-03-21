package com.example.projetorealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("meuprimeirobanco.realm").build();

        //Toda Vez vai Apagar os Dados do Banco, Mod DEV, remover em produção
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
    }
}
