package com.jjhome.doclet.antlr4;

import com.jjhome.doclet.antlr4.auto.RowsLexer;
import com.jjhome.doclet.antlr4.auto.RowsParser;
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

@RunWith(BlockJUnit4ClassRunner.class)
public class RowsTest {

    @Test
    public void testSimple() throws IOException {
        String str = new String(Files.readAllBytes(Paths.get(RowsTest.class.getResource("/").getPath(), "t.rows")));
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str); // create a lexer that feeds off of input CharStream

        RowsLexer lexer = new RowsLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        RowsParser parser = new RowsParser(tokens, 2);
        parser.setBuildParseTree(false);
        ParseTree tree = parser.file(); // begin parsing at init rule
    }

}
