package com.mmc.springbootdemo.service.user.impl;

import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import my.jooq.generator.auto.Tables;
import my.jooq.generator.auto.tables.records.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jooq.*;

import java.util.*;

@Slf4j
@Service("jooqService")
public class JooqUserService implements IUserService{

    private DSLContext dsl;

    @Autowired
    public JooqUserService(DSLContext context){
        dsl = context;
    }

    @Override
    public List<User> getAllUsers(){
//        Result<Record> result = dsl.select().from(Tables.USERS).fetch();
        Result<Record> result = dsl.fetch("select * from users");

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

    @Override
    public User findByName(String name) {
        Result result = dsl.select().from(Tables.USERS).where(Tables.USERS.NAME.equal(name)).fetch();

        User user = new User();
        if ( 0 == result.size() ) return user;

        Record record = (Record) result.get(0);
        Integer recordId = record.getValue(Tables.USERS.ID);
        String recordName = record.getValue(Tables.USERS.NAME);
        Integer recordAge = record.getValue(Tables.USERS.AGE);

        user.setId(recordId);
        user.setName(recordName);
        user.setAge(recordAge);

        return user;
    }

    @Override
    public void addUser(String name, Integer age) {
        dsl.insertInto(Tables.USERS).columns(Tables.USERS.NAME, Tables.USERS.AGE).values(name, age).execute();
    }

    @Override
    public void batchAddUsers(List<User> users) {

        Result<UsersRecord> records = dsl.newResult(Tables.USERS);
        for (User user: users){
            UsersRecord userRecord = dsl.newRecord(Tables.USERS, user);
            records.add(userRecord);
        }
        dsl.batchStore(records).execute();
    }

    @Override
    public void deleteUser(String name) {
        dsl.deleteFrom(Tables.USERS).where(Tables.USERS.NAME.equal(name)).execute();
    }

    @Override
    public void updateUser(String conditionName, Integer newAge) {
        dsl.update(Tables.USERS).set(Tables.USERS.AGE, newAge).where(Tables.USERS.NAME.eq(conditionName)).execute();
    }

    @Override
    public void clear() {
        dsl.execute("truncate table users");
//        dsl.truncate(Tables.USERS).execute();
    }

    @Override
    public void doTranscation(){

        try {
            dsl.transaction(configuration -> {
                dsl.execute("insert into users (age, name) values(111,'111')");
                dsl.execute("insert into users (age, name) values(222,'222')");
                throw new Exception("使用jooq自带事务抛出的异常");
            });
        }
        catch (Exception e){
            log.info("捕获jooq事务异常");
        }
    }
}