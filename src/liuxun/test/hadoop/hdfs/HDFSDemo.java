package liuxun.test.hadoop.hdfs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HDFSDemo {
	FileSystem fs  = null;
	@Before
	public void init() throws Exception {
		// 首先创建FileSystem的实现类(工具类)
		fs = FileSystem.get(new URI("hdfs://hadoop0:9000"), new Configuration(),"root"); 
	}
	
	@Test
	public void testUpload() throws  Exception {
		// 读取本地系统的文件，返回输入流
		InputStream in = new FileInputStream("/Users/liuxun/Desktop/Eclipse.zip");
		// 在HDFS上创建一个文件，返回输出流
		OutputStream out  = fs.create(new Path("/testEclipse"));
		// 输入——>输出
		IOUtils.copyBytes(in, out, 4096, true);
	}
	
	@Test
	public void testDownload() throws Exception { //最简洁的下载方式
		fs.copyToLocalFile(new Path("/log.log"), new Path("/Users/liuxun/Downloads/log.txt"));
	}
	@Test
	public void testMkdir() throws Exception {
		boolean flag = fs.mkdirs(new Path("/aaa/bbb/ccc"));
		System.out.println(flag);
	}
	
	@Test
	public void testDelete() throws Exception {
		// 第二个参数recursive boolean 表示是否递归删除所有子文件/文件夹
		boolean flag = fs.delete(new Path("/aaa/bbb"), true); 
		System.out.println(flag);
	}
	
	
	public static void main(String[] args) throws Exception {
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop0:9000"), new Configuration()); 
		InputStream in = fs.open(new Path("/words"));
		OutputStream out = new FileOutputStream("/Users/liuxun/Downloads/t.txt");
		IOUtils.copyBytes(in, out , 4096,true);
		
	}
}
