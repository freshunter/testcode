import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;



public class GetMac {
	
	public static void main(String[] args) {
		  try {
		    InetAddress ip = InetAddress.getLocalHost();
		    System.out.println("Current IP address : " + ip.getHostAddress());
		    
		    NetworkInterface ni = NetworkInterface.getByInetAddress(ip);
		    byte[] macni = ni.getHardwareAddress();
		    StringBuffer sb1= new StringBuffer();
	        for (int i = 0; i < macni.length; i++) {
		          sb1.append(String.format("%02X%s", macni[i], (i < macni.length - 1) ? "-" : ""));
		        }
	        System.out.println(sb1);
	        System.out.println();
	        System.out.println();
	        System.out.println();
		    

		    Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
		    while(networks.hasMoreElements()) {
		      NetworkInterface network = networks.nextElement();
		      byte[] mac = network.getHardwareAddress();

		      if(mac != null) {
		    	  System.out.println(network.getDisplayName());
		        System.out.print("Current MAC address : ");

		        StringBuilder sb = new StringBuilder();
		        for (int i = 0; i < mac.length; i++) {
		          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		        }
		        System.out.println(sb.toString());
		      }
		    }
		  } catch (UnknownHostException e) {
		    e.printStackTrace();
		  } catch (SocketException e){
		    e.printStackTrace();
		  }
		}

}
