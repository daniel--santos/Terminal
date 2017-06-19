package terminal.bytes;

public class ByteArrayBuffer {

    private byte[] dados;
    private int posicao;
    private int tamanhoTotal;
    private int tamanhoBufferInvidiual;
    private int qtdBuffers;

    public ByteArrayBuffer(int tamanhoBufferInvidiual, int qtdBuffers) {
        tamanhoTotal = tamanhoBufferInvidiual * qtdBuffers;
        this.tamanhoBufferInvidiual = tamanhoBufferInvidiual;
        this.qtdBuffers = qtdBuffers;
        dados = new byte[tamanhoTotal];
        posicao = 0;
    }

    public void concat(byte[] novosDados) {
        for(int i = 0; i < novosDados.length; i++) {
            dados[posicao * tamanhoBufferInvidiual + i] = novosDados[i];
        }
        posicao++;
    }

    public void limpar() {
        for(int i = 0; i < tamanhoTotal; i++) {
            dados[i] = 0;
        }
        posicao = 0;
    }

    public byte[] getDados() {
        int tamanho = posicao * tamanhoBufferInvidiual;
        if(dados.length == tamanho) {
            return dados;
        } else {
            byte[] dadosRestante = new byte[tamanho];
            for(int i = 0; i < tamanho; i++) {
                dadosRestante[i] = dados[i];
            }
            return dadosRestante;
        }
    }
}
