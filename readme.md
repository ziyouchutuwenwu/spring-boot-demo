# idea需要安装lombok插件

- 可以使用如下方式启动
```
mvn spring-boot:run -D spring.profiles.active=development
```

数据库初始工具
```
pip install -r ./tools/requirements.txt
python ./tools/db_init.py
```

启动需要生成代码
```
mvn mybatis-generator:generate
mvn jooq-codegen:generate
```

# mbg不支持yml！！！
- pgsql的jdbc驱动需要手工下载