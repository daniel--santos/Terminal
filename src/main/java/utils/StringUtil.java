package utils;

public class StringUtil {

    public static String limitarTamanho(String value, int tamanho) {
        if (value.length() > tamanho) {
            return value.substring(0, tamanho);
        }
        return value;
    }
    
    public static String extrairIdentificador(String value) {
        return value.split("(?>[a-zA-Z])")[0].replaceAll("[\\s\u00a0]", "");
    }
    
    public static String normalizarEspacos(String value) {
        return value.replaceAll("\u00a0", " ");
    }
    
    public static String proximaAposEspaco(String value, int aPartirDe) {
        String[] values = value.split("\\s");
        
        for (int i = aPartirDe; i < values.length; i++) {
            if (values[i].trim().length() > 0) {
                return values[i];
            }
            
            continue;
        }
        
        return null;
    }
    
    public static String removerVirgula(String value) {
        return value.replaceAll(",", "");
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
