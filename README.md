# houseHolderBack

**项目介绍**:户籍管理系统，分为用户端和管理员端



**项目使用的软件**（需要下载的在我的[download_web_site](https://github.com/lhz1165/download_web_site) 仓库去找下载链接）

1. idea2021
2. mysql8(没有8的修改下application.properties的驱动就好了)
3. mavne3.8

**使用框架**:

- 前端:element-ui+VUE
- 后端:springboot+mysql

**运行方式**:
```
mvn packae
进入target目录 java -jar xxx.jar  
```

接口文档地址
http://localhost:8089/swagger-ui/index.html


异常解决
```
端口占用
netstat -aon|findstr "8089"

//找到进程id
tasklist|findstr "xxx"

//杀死这个程序
taskkill /f /t /im xxxx.exe
```
