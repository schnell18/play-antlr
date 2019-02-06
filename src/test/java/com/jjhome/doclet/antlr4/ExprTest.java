package com.jjhome.doclet.antlr4;

import com.jjhome.doclet.antlr4.auto.ExprLexer;
import com.jjhome.doclet.antlr4.auto.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExprTest {

    @Test
    public void testSimple() throws IOException {
        String exp = "(prog (stat (expr 193) \\n) (stat a = (expr 5) \\n) (stat b = (expr 6) \\n) (stat (expr (expr a) + (expr (expr b) * (expr 2))) \\n) (stat (expr (expr ( (expr (expr 1) + (expr 2)) )) * (expr 3)) \\n))";
        String str = new String(Files.readAllBytes(Paths.get(ExprTest.class.getResource("/").getPath(), "t.expr")));
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str); // create a lexer that feeds off of input CharStream
        ExprLexer lexer = new ExprLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        assertEquals(exp, tree.toStringTree(parser));
    }

}
