import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GenJSData {
	
	public static void main(String[] args) {
//		process(new File("c:/tmp/data.txt"));
		getUnits(new File("c:/tmp/datau.txt"));
		
	}
/**
 * 
 *     {
        "usa":  [']
        } 
 * 
 * 
 * 
 * 
 * @param f
 */
	private static void process(File f) {
		System.out.println("Processing... " + f.getAbsolutePath());
		BufferedReader br = null;
		String[] label = {"number","start-time","end-time","current-time","time-elapsed","validity","cause","is-current","two-way-delay","maximum-two-way-delay","minimum-two-way-delay","average-two-way-delay","two-way-delay-variation","maximum-two-way-delay-variation","minimum-two-way-delay-variation","average-two-way-delay-variation","forward-delay","maximum-forward-delay","minimum-forward-delay","average-forward-delay","forward-delay-variation","maximum-forward-delay-variation","minimum-forward-delay-variation","average-forward-delay-variation","backward-delay","maximum-backward-delay","minimum-backward-delay","average-backward-delay","backward-delay-variation","maximum-backward-delay-variation","minimum-backward-delay-variation","average-backward-delay-variation"};
		HashMap hm = new HashMap();
		for (int i = 0; i < label.length; i++) {
			hm.put(label[i], "");
		}
		try {
			String result="{";
			br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			while (null != line) {
				line = line.trim();
				if (hm.containsKey(line)) {
					if(result.endsWith(",'")) {
						result = result.substring(0, result.length()-2);
					}
					if(result.endsWith("{")) {
						result += "\n'" + line + "':['";						
					} else {
						result += "],\n'" + line + "':['";
					}
				} else if(!line.isEmpty()) {
					result += line + "','";
				}
				line = br.readLine();
			}
			result = result.substring(0, result.length()-2) +"]}";
			System.out.println(result);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void getUnits(File f) {
		System.out.println("Processing... " + f.getAbsolutePath());
		BufferedReader br = null;
		String[] label = {"leaf","units"};
		HashMap hm = new HashMap();
		try {
			String result="{'";
			br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			while (null != line) {
				line = line.trim();
				if (line.startsWith(label[0])) {
					String tmp = line.replaceAll(label[0], "").replaceAll("\\{", "").trim();
					result += tmp +"':'";
				} else if(line.startsWith(label[1])) {
					String tmp = line.replaceAll(label[1], "").replaceAll(";", "").trim();
					tmp = tmp.substring(1,tmp.length()-1);
					result += tmp +"',\n'";
				}
				line = br.readLine();
			}
//			System.out.println(result);
			result = result.substring(0, result.length()-3) +"}";
			System.out.println(result);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
