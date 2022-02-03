import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class Repl
{
    private final Scanner scanner;
    private final Calculator calculator;
    private final Writer output;

    public Repl(Reader input, Calculator calculator, Writer output) {
        this.scanner = new Scanner(input);
        this.calculator = calculator;
        this.output = output;
    }

    public void start() throws IOException
    {
        while (true) {
            // Read
            String line = this.scanner.nextLine();

            // Evaluate
            if (line.equalsIgnoreCase("exit") || line.isEmpty()) {
                this.writeLine("\tGoodbye");
                break;
            }
            Integer result = this.calculator.calculate(line+"\n");

        }
    }

    private void write(String message) throws IOException {
        this.output.write(message);
        this.output.flush();
    }

    private void writeLine(String line) throws IOException {
        this.write(line + "\n");
    }
}
