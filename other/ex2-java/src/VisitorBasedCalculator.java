import gen.zad2Lexer;
import gen.zad2Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;

public class VisitorBasedCalculator implements Calculator
{
    @Override
    public Integer calculate(String input) {
        CharStream chars = CharStreams.fromString(input);

        Lexer lexer = new zad2Lexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        zad2Parser parser = new zad2Parser(tokens);
        ParseTree tree = parser.start();

        CalcVisitor calculator = new CalcVisitor();
        return calculator.visit(tree);
    }
}