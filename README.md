# pickles 
今晚吃老妈腌制的新咸菜，有点青辣，但是味道不错。    张孟志  2016-01-22。

**闲话**
坚持使用JDK8，Maven3.3，Dynamic Web Module 3.1。
另外Bootstrap 3 （包括时间拾取器datatimepicker）对浏览器的影响 需要注意。
参考使用了多个开源项目，包括且不限于MyBatis-Spring-Boot（https://github.com/abel533/MyBatis-Spring-Boot）、
springside4（https://github.com/springside/springside4）。已尽量根据各项目要求保留版权信息，如有不妥，请联系我。
  
**数据库说明**
项目使用的mysql数据库，请在generatorConfig.xml、resources/application.yml中配置数据库信息。
如果使用其他数据库，还需要在pom.xml中配置数据库JDBC连接依赖。其中generatorConfig.xml，只为开发设计，
可以利用MyBatisGenerator插件在eclipse中自动生成mybatis model文件，在*.model包下。
注意自动生成的model没有主键的注解，在selectByPrimaryKey 查找时会将所有字段作为查询条件，会出现null转换错误。
为了完成分页查询，还需要给model添加page、raws变量，及get/set方法。
示例使用的脚本为resources/mysql_demo.sql。建库、建用户、授权相关语句已注释，请根据实际参考。

**效率辅助说明**
在*.modules下包含大量的辅助类，可以提高开发效率。
其中constants下有超媒体文本传输常量定义、字符集定义；convert包含JSON/XML/Java Object格式转换、
日期格式转换；utils下涵盖大部分常用的工具类，包括日期获取（Clock）、List操作（Collection3）、
ConcurrenHashSet、支持HMAC-SHA1消息签名 及 DES/AES对称加密的工具类（Cryptos，支持Hex与Base64两种编码方式）、
封装各种格式的编码解码工具类（Encodes）、异常处理工具类（Exctptions）、生成唯一性ID算法的工具类（Ids）、
数字的工具类（Numbers）、反射工具类（Reflections）、线程相关工具类等。vo下是辅助类使用的数据对象。
例如对密码加密后产生了加密salt、hashPassword，封装在HashPasswordResult。

作者：张孟志
E-mail：104446930@qq.com
日期：2016-01-23