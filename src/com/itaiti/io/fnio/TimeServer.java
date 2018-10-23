package com.itaiti.io.fnio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.itaiti.io.bio.TimeServerHandler;

/**
 * α�첽����IO �����
 * @author itaiti
 * @email itaiti@163.com
 * @date 2017��9��15�� ����5:04:23
 * @version V1.0
 */
public class TimeServer {
	
	public static void main(String[] args) throws IOException {
		int port = 8080;
		if(args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch(NumberFormatException e) {
				// ����Ĭ��ֵ
			}
		}
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port: "+port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
			while(true) {
				socket = server.accept();
				System.out.println("server socket: "+socket);
				//new Thread(new TimeServerHandler(socket)).start();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		}finally {
			if(server != null) {
				System.out.println("The time server close");
				server.close();
				server = null;
			}
		}
	}
}
