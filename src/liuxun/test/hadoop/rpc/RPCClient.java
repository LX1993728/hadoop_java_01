package liuxun.test.hadoop.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {
	public static void main(String[] args) throws Exception {
		Bizable proxy = RPC.getProxy(Bizable.class, 1112222L, new InetSocketAddress("192.168.0.3", 9988),
				new Configuration());
		String result = proxy.sayHello("hadoop"); //$NON-NLS-1$
		System.out.println(result);
		RPC.stopProxy(proxy);
	}
}
