enjoyhisfy readme
1、config.properties
	mysql_bin 服务器mysql安装路径
	mysql_user mysql用户名
	mysql_pw 密码
	mysql_dbname 数据库名
	call_no_ip 分院叫号IP
	sms_username 发送短信用户名
	sms_password 发送短信密码
	sms_productid 发送短信的id
	Hessian 调用地址改为enjoyhisjt域名
2、spring-appctx-dao.xml
	配置数据源
3、若无历史数据，则需要在report_process表中添加一条数据，骗过系统的扎帐拦截。
	规则为status=6，stat_date比昨天大