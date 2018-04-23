# LogMonitor项目简介：
## 项目功能：
日志监控告警系统的主要功能： 帮助于开发者，针对于开发者开发的app或者客户端软件等各种应用的运行近况进行监控。可以查看应用的情况，了解详情，对于出发了开发者所设置的规则，需要将信息发送给维护这些应用的人，保持应用对客户的用户体验。 在对于开发者没有预想的情况下，保存在数据库中，管理员可以及时查看，告知应用开发人员。
### 项目目录
	/src ------源代码
    	/main -----主要的文件和配置文件
        	/java -----主要的java
        		/bean -----主要的持久类
        		/bolt ------storm的bolt
        		/dao  ------数据库操作类
        		/excption ------异常处理类
        		/mail	------邮件发送功能
        		/utils ------工具类
        		LogMonitor -----storm的主要驱动类
        	/resources -----一些资源文件
        		app-sql.properties -----sql语句配置文件
        		c3p0-config.xml -----数据库连接池配置文件
        		KafkaAndZk.properties ---Kafka和邮件配置文件
    	/test -----测试所用到的一些类
    		/java
    			/Dao
    			/mail
    			/utils
	/target-----作者运行生成的文件
	createDB.sql-----生产数据库表的sql文件
	pom.xml------本工程的依赖文件 
	README.md-----README文件
### 项目运行
	如果要使用本工程，你需要将结合kafka、storm，和flume
	flume主要用来采集日志文件
	kafka主要是消息的持久化
	storm进行实时流处理
	在这些配置工具结合使用后，需要使用本工程提供的sql创建数据库。
	最后，打包上传到storm，然后运行。
