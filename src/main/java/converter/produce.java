package converter;

import converter.classTree.ParseTree;

public interface produce {

    public Object convertTo(ParseTree dom,Regular regular);

}
