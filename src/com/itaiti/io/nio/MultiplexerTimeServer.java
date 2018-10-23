package com.itaiti.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

//import io.netty.channel.socket.ServerSocketChannel;

/**
 * 多路复用类，一个独立的现成，负责轮询多路复用器Selector,可以处理多个客户端的并发接入
 * 
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017年12月4日 下午3:03:18
 * @version V1.0
 */
public class MultiplexerTimeServer implements Runnable {

	private Selector selector;

	private ServerSocketChannel serverChannel;

	private volatile boolean stop;

	public MultiplexerTimeServer(int port) {
		try {
			selector = Selector.open();
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port: " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			// 处理新接入的请求消息
			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if (key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				/**
				 * 返回值大于0：读取到了字节，对字节进行编码 返回值等于0：没有读取到字节，属于正常场景，忽略
				 * 返回值为-1：链路已经关闭，需要关闭SocketChannel,释放资源
				 */
				System.out.println("readBytes:" + readBytes);
				if (readBytes > 0) {
					// flip的作用是将缓冲区当前的limit设置为position,position设置为0，用于后续对缓冲区的操作
					readBuffer.flip();
					// ByteBuffer.remaining()，此方法最给力，返回剩余的可用长度，此长度为实际读取的数据长度，最大自然是底层数组的长度
					byte[] bytes = new byte[readBuffer.remaining()];
					// ByteBuffer.get(byte[])，从ByteBuffer中读取byte[]
					readBuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("The time server receive order:" + body);
					String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
							? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

					doWrite(sc, currentTime);

				} else if (readBytes < 0) {
					key.cancel();
					sc.close();
				} else {

				}
			}
		}
	}

	private void doWrite(SocketChannel channel, String response) throws IOException {
		if (response != null && response.trim().length() > 0) {
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
		}
	}

}
