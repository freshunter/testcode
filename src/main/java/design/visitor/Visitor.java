package design.visitor;

public interface Visitor {

    void visitSContent(VisitorObj v);
    void visitLContent(VisitorObj v);
}

class VisitorAdaptor implements Visitor {

    public void visitSContent(VisitorObj v) {
	// TODO Auto-generated method stub
	
    }

    public void visitLContent(VisitorObj v) {
	// TODO Auto-generated method stub
	
    }
    
}

class ConcreteVisitor implements Visitor {

    public void visitSContent(VisitorObj v) {
	System.out.println("string:" + v);
    }

    public void visitLContent(VisitorObj v) {
	System.out.println("Long:" + v);
    }
}