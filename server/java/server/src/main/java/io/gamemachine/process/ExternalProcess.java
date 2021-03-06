package io.gamemachine.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.InvalidExitValueException;
import org.zeroturnaround.exec.ProcessExecutor;

import com.google.common.base.Strings;


public class ExternalProcess implements Runnable {
	
	public enum OS {
		LINUX,
		WIN
	}
	
	public enum Status {
		NONE,
		DOWN,
		UP,
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ExternalProcess.class);
	
	public String startScript;
	public String executable;
	public Status status;
	
	public ExternalProcess(String startScript, String executable) {
		this.startScript = startScript;
		this.executable = executable;
		this.status = Status.DOWN;
	}
	
	public String name() {
		return startScript + executable;
	}
	public OS os() {
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
			return OS.WIN;
		} else {
			return OS.LINUX;
		}
	}
		
	public boolean stop() {
		Runtime rt = Runtime.getRuntime();
		if (Strings.isNullOrEmpty(executable)) {
			logger.warn("Invalid executable name "+executable);
			return false;
		}
		
		try {
			String command;
			if (os() == OS.WIN) {
				command = "taskkill /F /IM "+executable;
			} else {
				command = "pkill -f \""+executable+"\"";
			}
			
			logger.debug("Killing "+executable+" with "+command);
			rt.exec(command);
			return true;
		} catch (IOException e) {
			logger.info(e.getMessage(),e);
			return false;
		}
	}
	
	@Override
	public void run() {
		try {
			logger.warn("Process " + executable + " starting at " + startScript);
			ProcessExecutor pex = new ProcessExecutor();
			int rvalue = pex.command(startScript).execute().getExitValue();
			status = Status.DOWN;
			logger.warn("Process "+ startScript+" exit value was " + rvalue);
		} catch (InvalidExitValueException | IOException | InterruptedException | TimeoutException e) {
			logger.debug(e.getMessage(),e);
		}
	}
	
	public boolean isRunning() {
		try {
			Runtime rt = Runtime.getRuntime();
			String pattern = "(.*)"+executable+"(.*)";
			Pattern r = Pattern.compile(pattern);
			
	        String line;
	        String command;
	        if (os() == OS.WIN) {
	        	command = "tasklist.exe";
	        } else {
	        	command = "ps -e";
	        }
	        
	        Process p = rt.exec(command);
	        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while ((line = input.readLine()) != null) {
	        	Matcher m = r.matcher(line);
	        	if (m.find()) {
	        		input.close();
	        		return true;
	        	}
	        }
	        input.close();
	        return false;
	    } catch (Exception err) {
	        logger.info(err.getMessage(),err);
	        return false;
	    }
	}
}
