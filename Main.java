public class Main {
    public static void main(String args[]) {
        String entrada = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";

        int resultado = calculate(entrada);

        System.out.println(entrada + " = " + resultado);
    }

    private static int calculate(String entrada) {
        int resultado = 0;
        String[] expression = entrada.replaceAll(" ", "").split("");
        System.out.print("Expressão inteira: ");
        printArray(expression);

        return Integer.parseInt(doCalc(expression));
    }

    private static String doCalc(String[] expression) {
        if (!expression[0].equals("(")) {
            return expression[0];
        }

        int idx     = lastIndexOf("(", expression); // index do último abre parêntesis
        int left    = getNextIndexOperandFrom(idx, expression);
        int op   = getNextIndexOperandFrom(left, expression);
        int right   = getNextIndexOperandFrom(op, expression);

        int result = resolveBinaryOperation(
            Integer.parseInt(expression[left]),
            expression[op],
            Integer.parseInt(expression[right])
        );

        expression[idx] = Integer.toString(result);
        expression[idx + 1] = "x";
        expression[idx + 2] = "x";
        expression[idx + 3] = "x";
        expression[idx + 4] = "x";

        System.out.print("Fator atual: ");
        System.out.println(left + " " + op + " " + right + " = " + result);
        printArray(expression);

        return doCalc(expression);
    }

    private static int getNextIndexOperandFrom(int lastKnownIndex, String[] expression) {
        for (int i = lastKnownIndex+1; i < expression.length; i++ ) {
            if (!expression[i].equals("x"))
                return i;
        }

        return -1;
    }

    private static void printArray(String[] arr) {
        for (String c: arr) {
            System.out.print(c);
        }
        System.out.println("");
    }

    private static int lastIndexOf(String charNeeded, String[] charArr) {
        int found = -1;

        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i].equals(charNeeded)) {
              found = i;
            }
        }

        return found;
    }

    private static int resolveBinaryOperation(int a, String op, int b) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return Math.round(a / b);
            default:
                System.out.println("Entrada inválida");
                System.exit(0);
        }

        return 0;
    }
}