package converter.classTree;

import com.jd.virtual.gateway.converter.abstraction.Converter;

public class NodeElement {

    Class<?> clazz;

    Converter converter;



    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}
