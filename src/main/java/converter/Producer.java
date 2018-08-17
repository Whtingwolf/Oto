package converter;

import converter.classTree.ParsedTree;

public interface Producer {

    public Object convertTo(ParsedTree dom, Regular regular);



}
