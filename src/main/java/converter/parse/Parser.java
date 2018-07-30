package converter.parse;

import converter.classTree.ParseTree;

public interface Parser {

    public ParseTree parse(Object source);

}
