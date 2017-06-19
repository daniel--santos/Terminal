package terminal.calculadoras;

import java.math.BigDecimal;

public class Calcular {

    public static void main(String[] args) {
        BigDecimal valorInicial = BigDecimal.valueOf(0);
        BigDecimal valorMensal = BigDecimal.valueOf(500);
        double taxaPoupanca = 1.0052;
        double taxaCDB = 1.116762;
        double taxaCDB36 = 1.42914;
//        double taxaCDB = 1.0948;
        int meses = 3;
//        System.out.println(Calculador.calculo1(valor, taxa, meses));
//        System.out.println(Calculador.calcularPoupancaPorMes(valor, valorMensal, taxa, meses));

        Calculador.calcularValorAnos(valorInicial, valorMensal, taxaPoupanca, taxaCDB, 30);
//        Calculador.calculoCompleto(valorInicial, valorMensal, taxaPoupanca, taxaCDB36, 30);
    }

}
