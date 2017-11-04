# Spring Cloud 配置服务器



## 搭建 Spring Cloud Config Server



### 基于文件系统（File System）



#### 创建本地仓库



1. 激活应用配置服务器

   在引导类上标注`@EnableConfigServer`

   ```java
   package com.segmentfault.springcloudlesson3configserver;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.config.server.EnableConfigServer;

   @SpringBootApplication
   @EnableConfigServer
   public class SpringCloudLesson3ConfigServerApplication {

   	public static void main(String[] args) {
   		SpringApplication.run(SpringCloudLesson3ConfigServerApplication.class, args);
   	}
   }
   ```

   ​

2. 创建本地目录

> 理解 Java 中的 ${user.dir}，在 IDE 中是指的当前项目物理路径

在 IDEA 中`src/main/resources`目录下，创建一个名为“configs”，它的绝对路径：

`${user.dir}/src/main/resources/config`

2. 配置 git 本地仓库 URI

   ```properties
   ## 配置服务器文件系统git 仓库
   ## ${user.dir} 减少平台文件系统的不一致
   spring.cloud.config.server.git.uri = ${user.dir}/src/main/resources/configs
   ```

   ​

3. 给应用"segmentfault"创建三个环境的配置文件

   ```
   -rw-r--r--  1 segmentfault-prod.properties
   -rw-r--r--  1 segmentfault-test.properties
   -rw-r--r--  1 segmentfault.properties
   ```

   三个文件的环境 profile 分别（从上至下）是：`prod`、`test`、`default`

4. 初始化本地 git 仓库

   ```
   git init
   git add .
   git commit -m "First commit"
   [master (root-commit) 9bd81bd] First commit
    3 files changed, 9 insertions(+)
    create mode 100644 segmentfault-prod.properties
    create mode 100644 segmentfault-test.properties
    create mode 100644 segmentfault.properties
   ```



#### 测试配置服务器

通过浏览器测试应用为"segmentfault"，Profile为：“test”的配置内容 : http://localhost:9090/segmentfault/test

```json
{
  "name": "segmentfault",
  "profiles": [
    "test"
  ],
  "label": null,
  "version": "9bd81bdd024832a8bb207a8133ff15e7d2baabb6",
  "state": null,
  "propertySources": [
    {
      "name": "/Users/Mercy/Downloads/spring-cloud-lesson-3-config-server/src/main/resources/configs/segmentfault-test.properties",
      "source": {
        "name": "segumentfault-test"
      }
    },
    {
      "name": "/Users/Mercy/Downloads/spring-cloud-lesson-3-config-server/src/main/resources/configs/segmentfault.properties",
      "source": {
        "name": "segumentfault"
      }
    }
  ]
}
```

请注意：当指定了profile 时，默认的 profile（不指定）配置信息也会输出：

```json
 "propertySources": [
    {
      "name": "/Users/Mercy/Downloads/spring-cloud-lesson-3-config-server/src/main/resources/configs/segmentfault-test.properties",
      "source": {
        "name": "segumentfault-test"
      }
    },
    {
      "name": "/Users/Mercy/Downloads/spring-cloud-lesson-3-config-server/src/main/resources/configs/segmentfault.properties",
      "source": {
        "name": "segumentfault"
      }
    }
  ]
```





## 基于远程 Git 仓库



1. 激活应用配置服务器

在引导类上标注`@EnableConfigServer`

2. 配置远程 Git 仓库地址

   ```properties
   ## 配置服务器远程 Git 仓库（GitHub）
   spring.cloud.config.server.git.uri = https://github.com/mercyblitz/tmp
   ```

   ​

3. 本地 clone 远程Git 仓库

   ```
   git clone https://github.com/mercyblitz/tmp.git
   ```

   ```
   $ ls -als
   total 24
   0 drwxr-xr-x   6 Mercy  staff  192 11  3 21:16 .
   0 drwx------+ 12 Mercy  staff  384 11  3 21:16 ..
   0 drwxr-xr-x  12 Mercy  staff  384 11  3 21:16 .git
   8 -rw-r--r--   1 Mercy  staff   40 11  3 21:16 README.md
   8 -rw-r--r--   1 Mercy  staff   27 11  3 21:16 a.properties
   8 -rw-r--r--   1 Mercy  staff   35 11  3 21:16 tmp.properties
   ```

   ​

4. 给应用"segmentfault"创建三个环境的配置文件

   ```
   -rw-r--r--   1 segmentfault-prod.properties
   -rw-r--r--   1 segmentfault-test.properties
   -rw-r--r--   1 segmentfault.properties
   ```

   ​

5. 提交到 远程 Git 仓库

   ```
   $ git add segmentfault*.properties
   $ git commit -m "Add segmentfault config files"
   [master 297989f] Add segmentfault config files
    3 files changed, 9 insertions(+)
    create mode 100644 segmentfault-prod.properties
    create mode 100644 segmentfault-test.properties
    create mode 100644 segmentfault.properties
   $ git push
   Counting objects: 5, done.
   Delta compression using up to 8 threads.
   Compressing objects: 100% (5/5), done.
   Writing objects: 100% (5/5), 630 bytes | 630.00 KiB/s, done.
   Total 5 (delta 0), reused 0 (delta 0)
   To https://github.com/mercyblitz/tmp.git
      d2b742b..297989f  master -> master
   ```

   ​

6. 配置强制拉去内容

```properties
## 强制拉去 Git 内容
spring.cloud.config.server.git.force-pull = true
```



7. 重启应用



## 配置 Spring Cloud 配置客户端



1. 创建 Spring Cloud Config Client 应用

   创建一个名为 `spring-cloud-lesson-3-config-client` 应用

2. ClassPath 下面创建 bootstrap.properties

3. 配置  bootstrap.properties

   配置 以`spring.cloud.config.` 开头配置信息

   ```properties
   ## 配置客户端应用关联的应用
   ## spring.cloud.config.name 是可选的
   ## 如果没有配置，采用 ${spring.application.name}
   spring.cloud.config.name = segmentfault
   ## 关联 profile
   spring.cloud.config.profile = prod
   ## 关联 label
   spring.cloud.config.label = master
   ## 配置配置服务器URI
   spring.cloud.config.uri = http://127.0.0.1:9090/
   ```

   application.properties 信息

   ```properties
   ## 配置客户端应用名称
   spring.application.name = spring-cloud-config-client

   ## 配置客户端应用服务端口
   server.port = 8080

   ## 关闭管理端actuator 的安全
   ## /env /health 端口完全开放
   management.security.enabled = false
   ```

   ​

4. 启动应用

```
[           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at: http://127.0.0.1:9090/
[           main] c.c.c.ConfigServicePropertySourceLocator : Located environment: name=segmentfault, profiles=[prod], label=master, version=15342a7ecdb59b691a8dd62d6331184cca3754f4, state=null
[           main] b.c.PropertySourceBootstrapConfiguration : Located property source: CompositePropertySource [name='configService', propertySources=[MapPropertySource {name='configClient'}, MapPropertySource {name='https://github.com/mercyblitz/tmp/segmentfault-prod.properties'}, MapPropertySource {name='https://github.com/mercyblitz/tmp/segmentfault.properties'}]]
```



### 测试 Spring Cloud 配置客户端

通过浏览器访问 http://localhost:8080/env



```json
"configService:configClient": {
  "config.client.version": "15342a7ecdb59b691a8dd62d6331184cca3754f4"
},
"configService:https://github.com/mercyblitz/tmp/segmentfault-prod.properties": {
  "name": "segumentfault.com"
},
"configService:https://github.com/mercyblitz/tmp/segmentfault.properties": {
  "name": "segumentfault.com"
}
```



通过具体的配置项`name`: http://localhost:8080/env/name

```json
{
  "name": "segumentfault.com"
}
```



## 动态配置属性Bean



#### 定义配置属性Bean `User`

```java
package com.segmentfault.springcloudlesson3configclient.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用户模型
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "sf.user")
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```



#### 将 `User` 关联配置项

```pr
## 用户的配置信息
## 用户 ID
sf.user.id = 1
## 用户名称
sf.user.name = xiaomage
```



>  通过浏览器访问
>
> * http://localhost:8080/env/sf.user.*
>
> ```json
> {
>   "sf.user.id": "1",
>   "sf.user.name": "xiaomage"
> }
> ```
>
> * http://localhost:8080/user
>
> ```json
> {
>   "id": 1,
>   "name": "xiaomage"
> }
> ```
>
> 



### 通过 Postman 调整配置项

POST 方法提交参数 sf.user.id = 007 、sf.user.name = xiaomage 到 `/env`

```json
sf.user.id = 007
sf.user.name = xiaomage
```



调整后，本地的http://localhost:8080/user 的内容变化:

```json
{
  "id": 7,
  "name": "mercyblitz"
}
```



问题，如果`spring-cloud-config-client`需要调整所有机器的配置如何操作？

> 注意，配置客户端应用的所关联的分布式配置内容，优先于传统 `application.properties`（application.yml）或者 `bootstrap.properties`（bootstrap.yml）



#### 调整配置服务器配置信息：`segmentfault-prod.properties`



