# installIuap


1.需要修改InstallIuap.bat中jdk路径，需要1.8的jdk版本

2.config目录文件说明
            datasource------配置数据库信息，dev是需要导出数据脚本的数据源信息，test是需要导入的数据源信息
                         propname-------名称dev为导出库test为导入库
                         ip-------------数据库IP
                         username-------数据库用户名
                         psw------------数据库密码
                         dbname---------数据库实例
                         dbtype---------数据库类型，目前只支持oralce
                         port-----------数据库端口

            items-----------数据库预置的脚本，根据配置的条件导出sql文件
                         itemTable------需要预置数据的表名
                         fixedWhere-----预置数据的where条件

            tablenameitems--配置需要导出指定服务的建表语句
                         serverid-------微服务编码
                         tablelike------数据库表相同的前缀，如导出当前服务下所有表前缀为alm_打头的表的建表语句，可用逗号分隔
                         tablevalue-----数据库表全面，可用逗号分隔

3.执行InstallIuap.bat，弹出界面，测试dev数据库连接信息，目前只支持oracle19c以下的版本，不支持db2及mysql

4.导出开发库微服务脚本，可以根据服务列表选择需要导出的微服务中的脚本

5.升级测试库微服务脚本，可以根据从dev库导出的脚本一键升级到test库中

6.升级过程中有log文件，里面会记录执行错误的日志

