package thread.deadlock;



class Resource {
    Object r1 = new A();
    Object r2 = new A();
}
class A {
    String r1 = "";
    String r2 = "";
}

class WorkThread implements Runnable {
    Resource r;
    private String name;
    

    public WorkThread(Resource r, String name) {
	super();
	this.r = r;
	this.name = name;
    }


    public void run() {
	    System.out.println(name + " request r2");
	synchronized (r.r2) {
	    System.out.println(name + " get r2");
	    try {
	        Thread.sleep(5000L);
            } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
            }
	    System.out.println(name + " request r1");
	    synchronized (r.r1) {
		    System.out.println( name + " get r1");
            }
	    
        }
	System.out.println( name + " end");
	
    }
    
}


class WorkThread2 implements Runnable {
    Resource r;
    String name;

    public WorkThread2(Resource r, String name) {
	super();
	this.r = r;
	this.name = name;
    }


    public void run() {
	    System.out.println(name + " request r1");
	synchronized (r.r1) {
	    System.out.println(name + " get r1");
	    try {
	        Thread.sleep(5000L);
            } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
            }
	    System.out.println( name + " request r2");
	    synchronized (r.r2) {
		    System.out.println( name + "  get r2");
            }
	    
        }
	System.out.println( name + " end");
	
    }
    
}

public class Main {
    public static void main(String[] args) {
	Resource r = new Resource();
	Thread wt = new Thread(new WorkThread(r, "thread1"));
	Thread wt2 = new Thread(new WorkThread2(r, "thread2"));
	wt.start();
	wt2.start();
    }

}
