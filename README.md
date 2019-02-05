# 概览

本项目实现了一个简单的LaTeX文档生成工具。

## 配置

本工具支持在Java 8及以上版本。之前版本无法运行。
为了能产生表的说明，链接MySQL的JDBC URL上需要加上以下参数：

- useInformationSchema=true
- useUnicode=true
- characterEncoding=utf-8
- zeroDateTimeBehavior=convertToNull

第一个参数是为了避免MySQL JDBC
驱动程序Connector/J自身的bug导致表注释没有在DatabaseMetaData的结果集中返回。
具体的bug详情请参考[Connector/J does not retrieve the table comment in a
InnoDB table][1]。 第二、三个参数是为了保证中文注释不出现乱码问题。
如果不指定前三个参数中的任何一个，本工具会自动添加。

## 使用

本工具为命令行工具。支持一次生成多个项目的LaTeX文档。使用之前请先编辑profile文件。格式采用Java中常见的properties文件。示例如下：

    jarFile = /Users/zhangfeng775/dingo-api-1.5.1-SNAPSHOT.jar
    jdbcUrl = jdbc:mysql://localhost:3307/dingo?zeroDateTimeBehavior=convertToNull
    jdbcUserName = pajk
    jdbcPassword = abc
    basedir = dingo

basedir这个参数如果和profile的名称一样的话可以不指定。然后将上述内容存放到一个名为dingo.properties的文件里，并放在当前工作目录中。
接下来就可以执行：

    java -jar doc-generator.jar dingo

生成的文档放在新生成的dingo子目录下。
对多个项目进行文档生成则可以使用以下命令：

    java -jar doc-generator.jar dingo octopus buffalo


[1]: https://bugs.mysql.com/bug.php?id=65213
