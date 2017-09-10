package liuxun.test.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * 
 * @author liuxun
 * 1.分析具体的业务逻辑，确定输入和输出数据的样式
 * 2.自定义一个类继承自org.apache.hadoop.mapreduce.Mapper类，重写map方法，实现具体业务逻辑，将新的key-Value输出
 * 3.自定义一个类继承自org.apache.hadoop.mapreduce.Reducer类，重写reduce方法，实现具体业务逻辑，将新的key-Value输出
 * 4.将自定义的mapper和reducer通过job对象组装起来
 */
public class WordCountCombiner {

	public static void main(String[] args) throws Exception {
		// 构建一个Job对象
		Job job = Job.getInstance(new Configuration());
		
		// 注意：一定要将main方法所在的类设置进去
		job.setJarByClass(WordCountCombiner.class);
		
		// 设置Mapper相关属性
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class); //设置Map的输出参数key的类型
		job.setMapOutputValueClass(LongWritable.class); //设置Map输出参数Value的类型
		FileInputFormat.setInputPaths(job, new Path(args[0])); //设置输入的路径
		
		// 设置Reducer相关属性
		job.setReducerClass(WCReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setCombinerClass(WCReducer.class);
		
		// 提交任务
		// 参数 表示在执行任务的过程中是否打印进程信息
		job.waitForCompletion(true);
	}
}
