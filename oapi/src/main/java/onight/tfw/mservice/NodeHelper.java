package onight.tfw.mservice;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import jnr.posix.POSIXFactory;
import onight.tfw.outils.conf.PropHelper;

public class NodeHelper {

	private static PropHelper prop;

	public static PropHelper getPropInstance() {
		if (prop == null) {
			synchronized (NodeHelper.class) {
				if (prop == null) {
					prop = new PropHelper(null);
				}
			}
		}
		return prop;
	}

	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
		}
		return "localhost";
	}

	public static String getCurrNodeID() {
		String def = getPropInstance().get("otrans.node.id", getCurrNodeListenOutAddr()+"."+getCurrNodeListenOutPort());
		String envid = System.getProperty("otrans.node.id", def);
		return envid;
	}

	public static String getCurrNodeListenInAddr() {
		String def = getPropInstance().get("otrans.addr.in", "0.0.0.0");
		String envid = System.getProperty("otrans.addr.in", def);
		return envid;
	}

	public static String getCurrNodeListenOutAddr() {
		String def;
		try {
			def = getPropInstance().get("otrans.addr.out", InetAddress.getLocalHost().getHostAddress());
			String envid = System.getProperty("otrans.addr.out", def);
			return envid;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "127.0.0.1";
	}

	static int listeninport = -1;

	public synchronized static int getCurrNodeListenInPort() {
		if (listeninport > 0)
			return listeninport;
		String def = getPropInstance().get("otrans.port.in", "[5100,5200]");
		String envid = System.getProperty("otrans.port.in", def);

		try {
			return Integer.parseInt(envid);
		} catch (NumberFormatException e) {
			String range[] = def.split("\\[|,|\\]");
			if (range.length == 3)
				try {
					for (int pf = Integer.parseInt(range[1]); pf <= Integer.parseInt(range[2]); pf++) {
						try (ServerSocket serverSocket = new ServerSocket(pf);) {
							listeninport = pf;
							return listeninport;
						} catch (Exception ee) {
						}
					}
				} catch (Exception ee) {
				}

		}
		listeninport = 5100;
		return listeninport;

	}

	public static void main(String[] args) {
		String range[] = "[5100,5200]".split("\\[|,|\\]");
		try {
			System.out.println(Integer.parseInt("[5100,5200]"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("from:" + Integer.parseInt(range[1].trim()) + ",to==>" + Integer.parseInt(range[2].trim()));
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static int getCurrNodeListenOutPort() {
		String def = getPropInstance().get("otrans.port.out", null);
		String envid = System.getProperty("otrans.port.out", def);
		if (envid == null)
			return getCurrNodeListenInPort();
		try {
			return Integer.parseInt(envid);
		} catch (NumberFormatException e) {
			return 5100;
		}
	}
}
