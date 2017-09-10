package liuxun.test.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	@Override
	protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context)
			throws IOException, InterruptedException {
		// 接收数据
		Text k3 = k2;  // 这里仍将接收的key作为输出的key
		// 定义一个计数器
		long counter = 0;
		// 循环迭代v2s
		for (LongWritable i : v2s) {
			counter+= i.get();
		}
		// 输出
		context.write(k3, new LongWritable(counter));
	}
}
