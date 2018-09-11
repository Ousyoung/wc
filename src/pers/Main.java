package pers;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException {
		String command = null;
		String function = null;
		String path = null;
		WC wc = new WC();
		System.out.println("\t WC.exe 使用说明:\n");
		System.out.println("-c 文件 （返回该文件的字符数）\n");
		System.out.println("-w 文件 （返回该文件的词的数目）\n");
		System.out.println("-l 文件 （返回该文件的行数）\n");
		System.out.println("-a 文件 （返回该文件的代码行 / 空行 / 注释行）\n");
		System.out.println("-s 文件 （递归处理）\n"); // ***************************
		System.out.print("请输入合理的查询命令:\n");
		while(true){
		
		Scanner getIn = new Scanner(System.in);
		if (getIn.hasNext()) {
			command = getIn.nextLine(); // 获取指令
		}
		String[] commands = new String[2];
		commands = command.split(" ", 2); // 拆分用户输入的指令
		function = commands[0]; // command前半部分为功能选择
		path = commands[1]; //// command后半部分为文件路径 ///出现了数组下标越界的问题

		if (function.equals("-c"))
			wc.charsCount(path);
		else if (function.equals("-w"))
			wc.wordsCount(path);
		else if (function.equals("-l"))
			wc.rowsCount(path);
		else if (function.equals("-a"))
			wc.complexCount(path);
		// else if(){}
		else
			System.out.println("用户输入不合法，请重新输入！");

	}
}
	}
