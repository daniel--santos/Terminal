package terminal.calculadoras;

import java.math.BigDecimal;

public class Investimento {

    private Integer id;
    private BigDecimal valor;
    private Integer meses;

    public Investimento(Integer id, BigDecimal valor, Integer meses) {
        this.id = id;
        this.valor = valor;
        this.meses = meses;
    }

    public void diminuirMenos() {
        meses--;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getMeses() {
        return meses;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investimento that = (Investimento) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
