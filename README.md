# 概览

本项目是antlr4的简单语法应用学习示例程序集。

## 示例目录

| name       | description            |
| ---------- | ---------------------- |
| ArrayInit  | 数组初始化语法规则示例 |
| CSV        | CSV文件语法            |
| Data       | 变长数据               |
| Expr       | 简单加减乘除表达式语法 |
| Hello      | Hello world            |
| Java       | Java语法，不支持泛型   |
| Rows       | 语法中嵌入行为的示例   |
| XMLLexer   | 语法中使用模式的示例   |
| JSON       | JSON语法示例           |
| DOT        | graphviz dot语法示例   |
| R          | R program语法示例      |


## 运行示例

运行示例之前最好生成一下Lexer和Parser程序，并进行编译。
可以用以下命令简化这个操作：

    mvn clean compile

所有示例均放在com.jjhome.doclet.antlr4.auto包下面，调用相应的语法的Parser
请加上这个包作为前缀。

    cd target/classes
    grun com.jjhome.doclet.antlr4.auto.CSV file -tokens < ../test-classes/data.csv

命令grun是[antlr4][1]发行版中命令，内容如下：

    #!/bin/sh

    java -cp ".:/path/to/antlr-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig $*

[1]: https://www.antlr.org/download.html
