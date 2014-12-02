package design.visitor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
	List ls = new ArrayList();
	ls.add("string");
	ls.add(Long.valueOf(123L));
	ls.add(Long.valueOf(3L));
	ls.add("kkk");
	
	for (Object o : ls) {
	    if(o instanceof String ) {
		System.out.println("string:" + o);
	    } else if(o instanceof Long) {
		System.out.println("Long:" + o);
	    }
        }
	
	Visitor v = new ConcreteVisitor();
	List<VisitorObj> lss = new ArrayList<VisitorObj>();
	lss.add(new DataString("string"));
	lss.add(new DataLong(321L));
	lss.add(new DataLong(32L));
	lss.add(new DataString("string"));
	for (VisitorObj o : lss) {
	    o.accept(v);
        }
    }

}
