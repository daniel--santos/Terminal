package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntidadeUtil {

    public static <T> T instanciarObjeto(Class<T> clazz) {
        try {
            T entidade = clazz.newInstance();
            return entidade;
        } catch (InstantiationException|IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * Recupera do mapeamento Hibernate de uma classe, os nomes das colunas presentes nas anotacoes @JoinColumn e @Column.
//     *
//     * @param clazz Classe alvo.
//     * @return Lista de Strings com o nome das colunas.
//     * */
//    public static List<String> getNomesColunasCampos(Class<?> clazz) {
//        Map<String, Field> fieldsMap = new LinkedHashMap<>();
//        Arrays.stream(clazz.getDeclaredFields()).forEach(f -> fieldsMap.put(f.getName(), f));
//
//        List<String> listaNomeCampos = fieldsMap.entrySet().stream()
//                .filter(f -> f.getValue().getModifiers() == Modifier.PRIVATE)
//                .map(f -> {
//                    try {
//                        Field field = f.getValue();
//                        return getNomeColumaPorAtributo(clazz, field.getName());
//                    } catch (NoSuchFieldException e) {
//                        e.printStackTrace();
//                        return "";
//                    }
//                })
//                .filter(f -> !f.equals(""))
//                .collect(Collectors.toList());
//
//        return listaNomeCampos;
//    }
//
//    /**
//     * Recupera de uma classe o nome da coluna presente nas anotacoes @JoinColumn e @Column.
//     *
//     * @param clazz Classe alvo.
//     * @return Strings com o nome da coluna, no caso de ser um campo com uma anotacao diferente retorna uma string vazia.
//     * */
//    private static String getNomeColumaPorAtributo(Class<?> clazz, String atributoNome) throws NoSuchFieldException {
//        Field property = clazz.getDeclaredField(atributoNome);
//
//        final Column columnAnnotation = property.getAnnotation(Column.class);
//        final JoinColumn joinColumnAnnotation = property.getAnnotation(JoinColumn.class);
//        if (columnAnnotation != null) {
//            return columnAnnotation.name();
//        } else if(joinColumnAnnotation != null) {
//            return joinColumnAnnotation.name();
//        }
//
//        return "";
//    }
//
//    /**
//     * Recupera o nome da sequence mapeada nas classes das entidades.
//     *
//     * @param clazz Classe da BaseEntity desejada.
//     * @return String com o nome da sequence caso existe, caso contrario retorna null.
//     * */
//    public static String getSequenceFromClass(Class<?> clazz) throws NoSuchFieldException {
//        Field property = clazz.getDeclaredField("id");
//
//        final Sequence sequenceGenerator = property.getAnnotation(Sequence.class);
//        if(sequenceGenerator != null) {
//            return sequenceGenerator.name();
//        }
//
//        return null;
//    }
//
//    /**
//     * Recupera o nome da tabela de acordo com a classe da entidade.
//     *
//     * @param clazz Classe da entidade.
//     * @return Retorna o nome da tabela caso a entidade tenha, caso contrário retorna null.
//     * */
//    public static String getNomeTabelaFromClass(Class<?> clazz) {
//        Optional<Annotation> opTable = Stream.of(clazz.getDeclaredAnnotations()).filter(a -> a instanceof Table).findFirst();
//
//        if(opTable.isPresent()) {
//            return ((Table) opTable.get()).name();
//        }
//
//        return null;
//    }
//
//    /**
//     * Recupera o nome do schema de acordo com a classe da entidade.
//     *
//     * @param clazz Classe da entidade.
//     * @return Retorna o nome do schema caso a entidade tenha, caso contrário retorna null.
//     * */
//    public static String getNomeSchemaFromClass(Class<?> clazz) {
//        Optional<Annotation> opTable = Stream.of(clazz.getDeclaredAnnotations()).filter(a -> a instanceof Table).findFirst();
//
//        if(opTable.isPresent()) {
//            return ((Table) opTable.get()).schema();
//        }
//
//        return null;
//    }
//
//    /**
//     * Recupera o nome da tabela e schema de acordo com a classe da entidade.
//     *
//     * @param clazz Classe da entidade.
//     * @return Retorna o nome da tabela e schem caso a entidade tenha, caso contrário retorna null.
//     * */
//    public static String getNomeSchemaTabelaFromClass(Class<?> clazz) {
//        String nomeSchema = getNomeSchemaFromClass(clazz);
//        String nomeTabela = getNomeTabelaFromClass(clazz);
//
//        if(nomeSchema != null && nomeTabela != null) {
//            return nomeSchema + "." + nomeTabela;
//        }
//
//        return null;
//    }
//
//    /**
//     * Preenche os valores de um objeto passando a classe e um mapa com os valores mapeados pelo nome das colunas na tabela.
//     * (Somente será associado os atributos que contenham as anotações Column e JoinColumn)
//     *
//     * @param clazz Classe do objeto.
//     * @param valores Mapa com os valores mapeados.
//     *
//     * @return Uma nova instância da classe passada com os valores presentes no mapa preenchidos.
//     * */
//    public static <T> T preencherValoresObjeto(Class<?> clazz, Map<String, Object> valores) {
//        //Cria uma instancia do objeto desejado
//        T obj = null;
//        try {
//            obj = (T) clazz.newInstance();
//        } catch (InstantiationException|IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        //Passa por cada campo do objeto e verifica se possui uma das Anotações que esta sendo procurado
//        //Nesse caso só Column
//        for(Field f : clazz.getDeclaredFields()) {
//            final Column columnAnnotation = f.getAnnotation(Column.class);
//            String nomeColuna = null;
//
//            if (columnAnnotation != null) {
//                nomeColuna = columnAnnotation.name();
//            }
//
//            //Caso tenha encontrado o nome da coluna da tabela pelas anotações
//            if(nomeColuna != null) {
//                //Procura pelo valor no mapa
//                final String nomeChave = nomeColuna;
//                Object valor = valores.get(nomeChave);
//
//                //Caso exista o valor
//                if(valor != null) {
//                    //Procura pelo método de Set do atributo
//                    Optional<Method> method = Stream.of(clazz.getMethods()).filter(m -> m.getName().toLowerCase().startsWith("set") &&
//                            m.getName().toLowerCase().endsWith(f.getName().toLowerCase()))
//                            .map(m -> m).findFirst();
//
//                    //Caso exista invoca o método com o valor
//                    if(method.isPresent()) {
//                        try {
//                            method.get().invoke(obj, valor);
//                        } catch (IllegalAccessException|InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//
//        return obj;
//    }
//
//    public static <E> Class getTipoCampoPorNomeColuna(String nomeColuna, Class<E> clazz) {
//        //Passa por cada campo do objeto e verifica se possui uma das Anotações que esta sendo procurado
//        //Nesse caso só Column
//        for(Field f : clazz.getDeclaredFields()) {
//            final Column columnAnnotation = f.getAnnotation(Column.class);
//            final JoinColumn joinColumn = f.getAnnotation(JoinColumn.class);
//
//            if (columnAnnotation != null && columnAnnotation.name().equals(nomeColuna)) {
//                return f.getType();
//            } else if (joinColumn != null && joinColumn.name().equals(nomeColuna)) {
//                return Long.class;
//            }
//        }
//
//        return null;
//    }
//
//    public static <E> Object getValorFieldPorNomeColuna(String nomeColuna, E entidade) {
//        //Passa por cada campo do objeto e verifica se possui uma das Anotações que esta sendo procurado
//        //Nesse caso só Column
//        for(Field f : entidade.getClass().getDeclaredFields()) {
//            final Column columnAnnotation = f.getAnnotation(Column.class);
//            final JoinColumn joinColumn = f.getAnnotation(JoinColumn.class);
//
//            if (columnAnnotation != null && columnAnnotation.name().equals(nomeColuna)) {
//                return getValorField(entidade, f.getName());
//            } else if (joinColumn != null && joinColumn.name().equals(nomeColuna)) {
//                Object joinColumnObject = getValorField(entidade, f.getName());
//                if(joinColumnObject != null) {
//                    String nomeField = getNomeFieldPorColuna(joinColumn.referencedColumnName(), joinColumnObject.getClass());
//                    return getValorField(joinColumnObject, nomeField);
//                }
//            }
//        }
//
//        return null;
//    }
//
//    public static <E> Object getValorField(E entidade, String nameField) {
//        //Procura pelo método de Get do atributo
//        Optional<Method> method = Stream.of(entidade.getClass().getMethods()).filter(m -> m.getName().toLowerCase().startsWith("get") &&
//                m.getName().toLowerCase().endsWith(nameField.toLowerCase()))
//                .map(m -> m).findFirst();
//
//        //Caso exista invoca o método Get do atributo
//        if(method.isPresent()) {
//            try {
//                Optional valor = Optional.ofNullable(method.get().invoke(entidade));
//                return valor.isPresent() ? valor.get() : null;
//            } catch (IllegalAccessException|InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    public static <E> int getMaxLengthPorNomeColuna(String nomeColuna, Class<E> clazz) {
//        //Passa por cada campo do objeto e verifica se possui uma das Anotações que esta sendo procurado
//        //Nesse caso só Column
//        for(Field f : clazz.getDeclaredFields()) {
//            final Column columnAnnotation = f.getAnnotation(Column.class);
//
//            if (columnAnnotation != null && columnAnnotation.name().equals(nomeColuna)) {
//                return columnAnnotation.length();
//            }
//        }
//
//        return -1;
//    }
//
//    public static <E> int getMaxLengthPorNomeColuna(String nomeColuna, E entidade) {
//        return getMaxLengthPorNomeColuna(nomeColuna, entidade.getClass());
//    }
//
//    public static <E> String getNomeFieldPorColuna(String nomeColuna, Class<E> clazz) {
//        //Passa por cada campo da classe e verifica se possui uma das Anotações que esta sendo procurado
//        //Nesse caso só Column
//        for(Field f : clazz.getDeclaredFields()) {
//            final Column columnAnnotation = f.getAnnotation(Column.class);
//
//            if (columnAnnotation != null && columnAnnotation.name().equals(nomeColuna)) {
//                return f.getName();
//            }
//        }
//
//        return null;
//    }
}
