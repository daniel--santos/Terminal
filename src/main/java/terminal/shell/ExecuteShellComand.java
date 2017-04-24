package terminal.shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ExecuteShellComand {

	public String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(System.getProperty("user.home") + File.separator));
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}

}