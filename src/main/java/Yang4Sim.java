import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Yang4Sim {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	if (args != null && args.length > 0 && args[0] != null && !"".equals(args[0])) {
	    System.out.println("Dir : " + args[0]);
	    File dir = new File(args[0]);
	    File dst = null;
	    if (args[1] != null && !"".equals(args[1])) {
		dst = new File(args[1]);
	    }
	    if (dir.isDirectory()) {
		File[] yangs = dir.listFiles();
		for (File f : yangs) {
		    process(f, dst);
		}
	    } else {
		System.out.println(args[0] + " is not a directory.");
	    }
	} else {
	    System.out.println("Parameter needed.");
	}
    }

    private static void process(File f, File dst) {
	if (!f.isFile() || !f.getName().endsWith(".yang")) {
	    return;
	}
	File ff;
	if (dst == null) {
	    ff = new File(f.getAbsoluteFile() + ".140114");
	} else {
	    ff = new File(dst.getAbsoluteFile() + "/" + f.getName());
	}
	System.out.println("Processing... " + f.getAbsolutePath());
	PrintWriter pw = null;
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new FileReader(f));
	    pw = new PrintWriter(new FileWriter(ff));
	    String line = br.readLine();
	    while (null != line) {
		if(line.contains("\"tailf:callpoint\"")) {
		    line = br.readLine();
		    continue;
		}
		if (line.matches(".*tailf\\s*:\\s*callpoint.*[{].*")
		        || line.matches(".*tailf\\s*:\\s*callpoint.*[^;][^;]*")) {
		    System.out.println("==========================>>" + line);
		    while (line.indexOf("}") < 0) {

			line = "//" + line;
			System.out.println(f.getName() + " : " + line);
			pw.println(line);
			line = br.readLine();
		    }
		    line = "//" + line;
		    System.out.println(f.getName() + " : " + line);
		    pw.println(line);
		    line = br.readLine();
		} else if (line.matches(".*tailf\\s*:\\s*callpoint.*;.*")) {
		    line = "//" + line;
		    System.out.println("*****************************"+f.getName() + " : " + line);
		} 
//		else if (line.contains("callpoint")) {
//		    System.out.println("****************** " + f.getName() + " : " + line);
//		}
		pw.println(line);
		line = br.readLine();
	    }

	    if (dst == null) {
		String name = f.getAbsolutePath();
		f.renameTo(new File(name + ".before"));
		ff.renameTo(new File(name));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	    try {
		br.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    try {
		pw.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }
}
