1.首先mysql运行脚本properties.sql

2.mysql运行脚本后，生成表properties,mail.path字段为存放邮件的物理路径,在该路径下创建test文件夹，test文件夹下创建test.txt,test.csv两个文件.
test为约定的邮件主题，test.txt为邮件内容,test.csv每行写好邮件发送地址。

3.先起配置中心，依次启动send,handle模块

4.到达指定时间,将会发送test.txt中的内容到test.csv中的所有地址。

