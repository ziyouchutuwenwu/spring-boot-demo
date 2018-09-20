# idea需要安装lombok插件

- 可以使用如下方式启动
```
mvn spring-boot:run -D spring.profiles.active=development
```

# jpa的ddl-auto属性会根据model自动建表，注意相关属性

- 数据库初始工具
```
pip install -r ./tools/requirements.txt
python ./tools/db_init.py
```

- 启动需要生成代码
```
mvn mybatis-generator:generate
mvn jooq-codegen:generate
```

- 验证器国际化需要自己实现，默认的文件需要ASCII编码

# mbg不支持yml！！！
- pgsql的jdbc驱动需要手工下载

# mybatis-plus输入模块为auto
- 注意代码生成的xml文件的位置