package com.jjhome.doclet.antlr4;

import com.jjhome.doclet.antlr4.auto.ArrayInitBaseListener;
import com.jjhome.doclet.antlr4.auto.ArrayInitLexer;
import com.jjhome.doclet.antlr4.auto.ArrayInitParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class SimpleArrayInitWalkTest {

    @Test
    public void testSimple() {
        String exp = "\"\\u007b\\u053f\\u020d\\u0cab\"";
        String str = "{123, 1343, 525, 3243}";
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString(str); // create a lexer that feeds off of input CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input); // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a parser that feeds off the tokens buffer
        ArrayInitParser parser = new ArrayInitParser(tokens);
        ParseTree tree = parser.init(); // begin parsing at init rule
        ParseTreeWalker walker = new ParseTreeWalker();
        StringBuffer buf = new StringBuffer(100);
        walker.walk(
            new ArrayInitBaseListener() {
                @Override
                public void enterInit(ArrayInitParser.InitContext ctx) {
                    buf.append('"');
                }

                @Override
                public void exitInit(ArrayInitParser.InitContext ctx) {
                    buf.append('"');
                }

                @Override
                public void enterValue(ArrayInitParser.ValueContext ctx) {
                    buf.append(String.format("\\u%04x", Integer.valueOf(ctx.INT().getText())));
                }
            },
            tree
        );

        assertEquals(exp, buf.toString());
    }

}
