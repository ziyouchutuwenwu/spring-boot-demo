package com.mmc.springbootdemo.service;

import com.mmc.springbootdemo.model.User;
import my.jooq.generator.auto.Tables;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class JooqUserService {

    private DSLContext dsl;

    @Autowired
    public JooqUserService(DSLContext context){
        dsl = context;
    }

    public void delete(int id) {
        dsl.deleteFrom(Tables.USERS).where(Tables.USERS.ID.eq(id)).execute();
    }

    public List<User> getAllUsers(){
        Result<Record> result = dsl.select().from(Tables.USERS).fetch();

        List<User> users = new ArrayList<>();

        for ( Record record : result ) {
            Integer id = record.getValue(Tables.USERS.ID);
            String name = record.getValue(Tables.USERS.NAME);
            Integer age = record.getValue(Tables.USERS.AGE);

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setAge(age);

            users.add(user);
        }

        return users;
    }
}