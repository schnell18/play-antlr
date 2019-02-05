package com.jjhome.doclet.antlr4;

import com.jjhome.doclet.antlr4.auto.ArrayInitLexer;
import com.jjhome.doclet.antlr4.auto.ArrayInitParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class ArrayInitTest {

    @Test
    public void testSimple() {
        String exp = "(init { (value 123) , (value 1343) , (value 525) , (value (init { (value 3423) , (value 23423) , (value (init { (value 3424) })) , (value 23434) })) , (value 3243) })";
        String str = "{123, 1343, 525, {3423, 23423, {3424}, 23434}, 3243}";
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str); // create a lexer that feeds off of input CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        ArrayInitParser parser = new ArrayInitParser(tokens);
        ParseTree tree = parser.init(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        assertEquals(exp, tree.toStringTree(parser));
    }

    @Test
    public void testBadSyntax() {
        String exp = "(init { (value 123) , (value 1343) , (value 525) , (value (init { (value 3423) , (value 23423) , (value (init { (value 3424) })) , (value 23434) })) , (value 3243) <missing '}'>)";
        String str = "{123, 1343, 525, {3423, 23423, {3424}, 23434}, 3243";
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str); // create a lexer that feeds off of input CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        ArrayInitParser parser = new ArrayInitParser(tokens);
        ParseTree tree = parser.init(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        assertEquals(exp, tree.toStringTree(parser));
    }

}
