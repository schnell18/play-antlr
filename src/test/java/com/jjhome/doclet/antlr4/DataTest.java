package com.jjhome.doclet.antlr4;

import com.jjhome.doclet.antlr4.auto.DataLexer;
import com.jjhome.doclet.antlr4.auto.DataParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class DataTest {

    @Test
    public void testSimple23() throws IOException {
        String exp = "(file (group 2 (sequence 9 10)) (group 3 (sequence 1 2 3)))";
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(getClass().getResourceAsStream("/t.data"));
        DataLexer lexer = new DataLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        DataParser parser = new DataParser(tokens);
        ParseTree tree = parser.file();
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        assertEquals(exp, tree.toStringTree(parser));
    }

    @Test
    public void testSimple37() throws IOException {
        String exp = "(file (group 3 (sequence 9 10 3)) (group 7 (sequence 1 2 3 13 344 3109 13)))";
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(getClass().getResourceAsStream("/t2.data"));
        DataLexer lexer = new DataLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        DataParser parser = new DataParser(tokens);
        ParseTree tree = parser.file();
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        assertEquals(exp, tree.toStringTree(parser));
    }

}
