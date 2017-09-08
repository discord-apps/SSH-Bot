package org.shanerx.discord.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHClient {
	
	private SSHInfo sshInfo;
	private final JSch jsch = new JSch();
	private Session sess;
	
	public SSHClient(SSHInfo sshInfo) throws JSchException {
		this.sshInfo = sshInfo;
		this.sess = jsch.getSession(sshInfo.getUser(), sshInfo.getHostname(), 22);
		sess.connect();
	}
	
	public SSHInfo getSshInfo() {
		return sshInfo;
	}
	
	public JSch getJsch() {
		return jsch;
	}
	
	public void exec(String x) throws JSchException {
		Channel chan = sess.openChannel("shell");
		chan.setInputStream(System.in);	// TODO
		chan.setOutputStream(System.out); // TODO
		chan.connect();
	}
}