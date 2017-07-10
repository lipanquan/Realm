package com.jzg.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    int keyId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        RealmResults<User> users = realm.where(User.class).findAll();
        if (users != null && users.size() > 0)
            keyId = users.get(users.size() - 1).getId();
    }

    public void add(View v) {
        realm.beginTransaction();
        User user = realm.createObject(User.class, ++ keyId);
        user.setAge(18);
        user.setName("小王");
        realm.commitTransaction();
    }

    public void delete(View v) {
        //先查找到数据
        final RealmResults<User> users = realm.where(User.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                users.get(users.size() - 1).deleteFromRealm();
            }
        });
    }

    private RealmResults<User> users;

    public void select(View v) {
        realm.beginTransaction();
        users = realm.where(User.class).findAll();
        if (users != null)
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i));
            }
        realm.commitTransaction();
    }

    public void update(View v) {
        if (users != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                }
            });
        }
        realm.beginTransaction();
        if (users != null) {
            users.get(0).setAge(22);
            users.get(0).setName("小王呵呵呵");
            System.out.println("修改成功");
        } else
            System.out.println("修改失败");
        realm.commitTransaction();
    }

}
