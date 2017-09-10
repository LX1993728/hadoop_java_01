package liuxun.test.hadoop.rpc;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer implements Bizable {
	public String sayHello(String name) {
		return "hello " + name;
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Server server = new RPC.Builder(conf).setProtocol(Bizable.class).setInstance(new RPCServer()).setBindAddress("192.168.0.3")
				.setPort(9988).build();
		server.start();
	}
}
