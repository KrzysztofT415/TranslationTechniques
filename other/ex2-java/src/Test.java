import gen.zad2Lexer;
import gen.zad2Parser;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Test
{
    public static void main(String[] args) throws Exception {
        Repl repl = new Repl(
                new InputStreamReader(System.in),
                new VisitorBasedCalculator(),
                new PrintWriter(System.out)
        );

        repl.start();
    }
}
