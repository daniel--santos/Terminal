package terminal.calculadoras;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calculador {

    public static BigDecimal calculo1(BigDecimal valor, double taxa, int meses) {
        BigDecimal total = valor;

        BigDecimal taxaBigDecimal = new BigDecimal(taxa);

        while(meses > 0) {
            total = total.multiply(taxaBigDecimal);
            meses--;
        }

        return total;
    }

    public static void calcularValorAnos(BigDecimal valorInicial, BigDecimal valorMensal, double taxaPoupanca, double taxaCDB, int anos) {
        //Ano 1
        BigDecimal total = calcularPoupancaPorMes(valorInicial, valorMensal, taxaPoupanca, 12);

        for(int ano = 2; ano <= anos; ano++) {
            total = total.multiply(BigDecimal.valueOf(taxaCDB));
            BigDecimal valorPoupancaAno = calcularPoupancaPorMes(BigDecimal.ZERO, valorMensal, taxaPoupanca, 12);
            total = total.add(valorPoupancaAno);
        }

        DecimalFormat df = new DecimalFormat("#,###,###.##");
        System.out.println("Valor total investido: R$" + df.format(valorInicial.add(valorMensal.multiply(BigDecimal.valueOf(anos * 12)))));
        System.out.println("Valor obtido: R$" + df.format(total.setScale(2, BigDecimal.ROUND_CEILING)));
    }

    public static BigDecimal calcularValorAnual(BigDecimal valor, double taxa) {
        return valor.multiply(BigDecimal.valueOf(taxa));
    }

    public static BigDecimal calcularPoupancaPorMes(BigDecimal valorInicial, BigDecimal valorMensal, double taxa, int meses) {
        BigDecimal total = valorInicial;

        BigDecimal taxaBigDecimal = new BigDecimal(taxa);

        while(meses > 0) {
            total = total.multiply(taxaBigDecimal);
            total = total.add(valorMensal);
            meses--;
        }

        total = total.multiply(taxaBigDecimal);

        return total;
    }

    public static void calculoCompleto(BigDecimal valorInicial, BigDecimal valorMensal, double taxaPoupanca, double taxaCDB, int anos) {
        List<Investimento> investimentos = new ArrayList<>();
        Integer id = 0;
        Integer mesesCDB = 36;
        BigDecimal parcelaCDB = BigDecimal.valueOf(5000);
        BigDecimal total = valorInicial;
        BigDecimal valorMeses = BigDecimal.ZERO;

        int meses = anos * 12;

        while(meses > 0) {
            //Atulizando poupança
            valorMeses = valorMeses.multiply(BigDecimal.valueOf(taxaPoupanca));
            valorMeses = valorMeses.add(valorMensal);

            //Atualiza os investimentos
            investimentos.forEach(i -> i.diminuirMenos());
            List<Investimento> investimentosProntos = investimentos.stream().filter(i -> i.getMeses().equals(0)).collect(Collectors.toList());
            for (Investimento investimentosPronto : investimentosProntos) {
                total = total.add(investimentosPronto.getValor().multiply(BigDecimal.valueOf(taxaCDB)));
                investimentos.remove(investimentosPronto);
            }

            //Junta investimentos e poupança
            total = total.add(valorMeses);

            BigDecimal parcelas = total.divideToIntegralValue(parcelaCDB);

            //Se existe pelo menos uma parcela de CDB para investir
            if(parcelas.compareTo(BigDecimal.ZERO) == 1) {
                Investimento inv = new Investimento(id++, parcelas.multiply(parcelaCDB), mesesCDB);
                investimentos.add(inv);
                valorMeses = total.subtract(parcelas.multiply(parcelaCDB));
            }

            total = BigDecimal.ZERO;
            meses--;
        }

        int mesesAdicionais = 0;

        while(!investimentos.isEmpty()) {
            mesesAdicionais++;

            //Atulizando poupança
            valorMeses = valorMeses.multiply(BigDecimal.valueOf(taxaPoupanca));
            valorMeses = valorMeses.add(valorMensal);

            //Atualiza os investimentos
            investimentos.forEach(i -> i.diminuirMenos());
            List<Investimento> investimentosProntos = investimentos.stream().filter(i -> i.getMeses().equals(0)).collect(Collectors.toList());
            for (Investimento investimentosPronto : investimentosProntos) {
                total = total.add(investimentosPronto.getValor().multiply(BigDecimal.valueOf(taxaCDB)));
                investimentos.remove(investimentosPronto);
            }

            //Junta investimentos e poupança
            valorMeses = total.add(valorMeses);
            total = BigDecimal.ZERO;
        }

        DecimalFormat df = new DecimalFormat("#,###,###.##");
        System.out.println("Meses adicionais: " + mesesAdicionais);
        System.out.println("Valor total investido: R$" + df.format(valorInicial.add(valorMensal.multiply(BigDecimal.valueOf((anos) * 12 + mesesAdicionais)))));
        System.out.println("Valor obtido: R$" + df.format(valorMeses.setScale(2, BigDecimal.ROUND_CEILING)));
    }

}
