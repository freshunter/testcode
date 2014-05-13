package cs.datastructure.sort;

public class TimeConsume
{
    long time;
    
    public void start(){
        time = System.currentTimeMillis();
        System.out.println("strat check time========");
    }
    
    public void check(String taskname){
        long now = System.currentTimeMillis();
        System.out.println(taskname + " time consume:" + (now - time) + " ms=========");
        time = now;
    }
    
    void end(){
        
    }

}
