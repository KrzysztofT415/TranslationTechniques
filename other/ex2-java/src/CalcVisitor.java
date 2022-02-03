import gen.zad2BaseVisitor;
import gen.zad2Parser;

import java.util.ArrayList;

public class CalcVisitor extends zad2BaseVisitor<Integer>
{
    ArrayList<String> reversPolishNotation = new ArrayList<>();

    @Override
    public Integer visitPrint(zad2Parser.PrintContext ctx) {
        Integer value = visit(ctx.expression());

        for(int i = 0; i < reversPolishNotation.size(); i++)
        {
            System.out.print(reversPolishNotation.get(reversPolishNotation.size() - i - 1));
        }

        System.out.println("\n" + value);
        return value;
    }


    @Override
    public Integer visitNumer(zad2Parser.NumerContext ctx) {
        int res = Integer.parseInt(ctx.NUMBER().getText());
        if(ctx.operator != null)
        {
            res = -res;
        }
        reversPolishNotation.add(" " + res + " ");
        return res;
    }

    @Override
    public Integer visitParentheses(zad2Parser.ParenthesesContext ctx) {
        return this.visit(ctx.inner);
    }



    @Override
    public Integer visitAddSub(zad2Parser.AddSubContext ctx)
    {
        if(ctx.operator == null)
        {
            return this.visit(ctx.left);
        }
        if (ctx.operator.getText().equals("+"))
        {
            reversPolishNotation.add(" + ");
            return this.visit(ctx.left) + this.visit(ctx.right);
        }
        reversPolishNotation.add(" - ");
        return this.visit(ctx.left) - this.visit(ctx.right);
    }

    @Override
    public Integer visitMulDivMod(zad2Parser.MulDivModContext ctx)
    {
        if(ctx.operator == null)
        {
            return this.visit(ctx.left);
        }
        if (ctx.operator.getText().equals("*"))
        {
            reversPolishNotation.add(" * ");
            return this.visit(ctx.left) * this.visit(ctx.right);
        }

        if(this.visit(ctx.right) == 0)
        {
            System.exit(0);
        }
        if (ctx.operator.getText().equals("/"))
        {
            reversPolishNotation.add(" / ");
            return this.visit(ctx.left) / this.visit(ctx.right);
        }
        reversPolishNotation.add(" % ");
        return this.visit(ctx.left) % this.visit(ctx.right);
    }

    @Override
    public Integer visitPwr(zad2Parser.PwrContext ctx)
    {
        if(ctx.operator == null)
        {
            return this.visit(ctx.left);
        }
        reversPolishNotation.add    (" ^ ");
        return (int) Math.pow(this.visit(ctx.left), this.visit(ctx.right));
    }

}
