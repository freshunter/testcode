package jms.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class MessageProc implements MessageListener  {

    public void onMessage(Message message) {
	if(message instanceof ObjectMessage){

	    ObjectMessage om = (ObjectMessage)message;
		try {
		    System.out.println(om.getObject().toString());
		    System.out.println(om.getJMSType().toString());
		    System.out.println(om.getJMSMessageID().toString());
		    System.out.println(om.getJMSReplyTo().toString());
//		    System.out.println(om.getjms.toString());
	            
                } catch (JMSException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
                }   
	}
	
    }

}
