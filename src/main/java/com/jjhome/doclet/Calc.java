package com.jjhome.doclet;

import com.jjhome.doclet.antlr4.auto.ExprLexer;
import com.jjhome.doclet.antlr4.auto.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Calc {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromStream(System.in);
        ExprLexer lexer = new ExprLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog(); // begin parsing at init rule
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }

}