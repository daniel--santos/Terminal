package terminal.bytes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class QuebrarArquivo {

    public static final String ARQUIVO = "F:\\Quatro.Vidas.De.Um.Cachorro.2017.720p.BluRay-AMIABLE.DUAL-GUR.mkv";
//    public static final String ARQUIVO = "E:\\teste.exe";
    public static final String ARQUIVO2 = "E:\\processado-teste.exe";
    public static Integer TAMANHO_BYTES = 2097152;
//    public static final Integer TAMANHO_BYTES = 1048576;
//    public static final Integer TAMANHO_BYTES = 524288;
    public static final Integer QUANTIDADE_BUFFERS = 10;

    public static void main(String[] args) throws IOException {
        process(new File(ARQUIVO));
//        process(new File(ARQUIVO2));
    }

    private static void process(File file) throws IOException {
        try (RandomAccessFile data = new RandomAccessFile(file, "r")) {
            long tamanhoArquivo = data.length();
            byte[] buffer = new byte[TAMANHO_BYTES];
            ByteArrayBuffer mReadBuffer = new ByteArrayBuffer(TAMANHO_BYTES, QUANTIDADE_BUFFERS);
            long quantidadeLeituras = tamanhoArquivo / TAMANHO_BYTES.longValue();
            double resto = tamanhoArquivo % TAMANHO_BYTES.longValue();
            int gravar = QUANTIDADE_BUFFERS;

            FileOutputStream output = new FileOutputStream(file.getParentFile() + "processado-" + file.getName(), true);

            while(quantidadeLeituras > 0) {
                gravar--;
                data.readFully(buffer);
                reverter(buffer);
                mReadBuffer.concat(buffer);

                if(gravar == 0) {
                    output.write(mReadBuffer.getDados());
                    mReadBuffer.limpar();
                    gravar = QUANTIDADE_BUFFERS;
                }

                quantidadeLeituras--;
                System.out.println(quantidadeLeituras);
            }

            if(gravar > 0 && gravar != QUANTIDADE_BUFFERS) {
                output.write(mReadBuffer.getDados());
            }

            if(resto != 0) {
                quantidadeLeituras = tamanhoArquivo / TAMANHO_BYTES.longValue();
                Long bytesRestantes = tamanhoArquivo - (quantidadeLeituras * TAMANHO_BYTES.longValue());
                buffer = new byte[bytesRestantes.intValue()];
                data.readFully(buffer);
                output.write(buffer);
            }
        }
    }

    private static void reverter(byte[] vetor) {
        if(vetor != null) {
            byte b;
            int inicio = 0, fim = vetor.length - 1;

            while(inicio < fim) {
                b = vetor[inicio];
                vetor[inicio] = vetor[fim];
                vetor[fim] = b;

                inicio++;
                fim--;
            }
        }
    }

}
