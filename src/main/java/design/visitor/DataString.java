package design.visitor;

public class DataString implements VisitorObj{
    String value;
    DataString(String value) {
	this.value = value;
    }

    public void accept(Visitor v) {
	v.visitSContent(this);
    }

}

interface VisitorObj {
    void accept(Visitor v);
}


class DataLong implements VisitorObj{
    Long value;
    
    DataLong(Long value) {
	this.value = value;
    }

    public void accept(Visitor v) {
	v.visitLContent(this);
    }

}
