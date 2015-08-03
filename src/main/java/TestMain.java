import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;


public class TestMain {

    private static String getYangPath(final String path) {
            String yangpath = path.substring(path.indexOf(IDETIFIER_MOUNT) + IDETIFIER_MOUNT.length());
            return yangpath;
    }

    private static String getDeviceName(final String path) {
            String devname = path.substring(path.indexOf(IDETIFIER_PREFIX) + IDETIFIER_PREFIX.length(), path.indexOf(IDETIFIER_MOUNT));
            return devname;
    }

    
    private static final String IDETIFIER_PREFIX = "opendaylight-inventory:nodes/node/";
    private static final String IDETIFIER_MOUNT = "/yang-ext:mount/";
	
	public static void main(String[] args) {
	    String path = "opendaylight-inventory:nodes/node/exa-9353/yang-ext:mount/exa-base:config/profile/bandwidth-profile/kkk/meter-mef/";
            System.out.println(TestMain.getDeviceName(path));
            System.out.println(TestMain.getYangPath(path)
                    );
            System.out.println(System.currentTimeMillis());
            


            ConcurrentHashMap<String, Semaphore> signalMap = new ConcurrentHashMap<>();
            Semaphore sem = signalMap.putIfAbsent("asdf", new Semaphore(1));
            
            System.out.println(sem.tryAcquire());
	}

}
