package pers;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException {
		String command = null;
		String function = null;
		String path = null;
		System.out.println("\t WC.exe 使用说明:\n");
		System.out.println("-c 文件 （返回该文件的字符数）\n");
		System.out.println("-w 文件 （返回该文件的词的数目）\n");
		System.out.println("-l 文件 （返回该文件的行数）\n");
		System.out.println("-a 文件 （返回该文件的代码行 / 空行 / 注释行）\n");
		System.out.println("-all 文件 （返回字符数，词，行等信息）\n");
		System.out.println("-s 文件 （文件递归查找处理）\n");
		System.out.println("-esc   （退出程序）\n");

		System.out.print("请输入合理的命令:\n");
		while (true) {
			Scanner getIn = new Scanner(System.in);
			if (getIn.hasNext())
				command = getIn.nextLine(); // 获取指令
			if (command.equals("-esc")) {
				System.out.println("***WC.exe程序已关闭");
				System.exit(0); // 关闭程序
			} else {
				String[] commands = new String[2]; // 字符串数组用于存放指令
				commands = command.split(" ", 2); // 拆分用户输入的指令
				function = commands[0]; // command前半部分为功能选择
				path = commands[1]; // 后半部分为文件路径
				// Mark:修改代码时出现了数组下标越界的问题*****************
				if (function.equals("-c"))
					WC.charsCount(path);
				else if (function.equals("-w"))
					WC.wordsCount(path);
				else if (function.equals("-l"))
					WC.rowsCount(path);
				else if (function.equals("-a"))
					WC.complexCount(path);
				else if (function.equals("-all"))
					WC.allCount(path);
				else if (function.equals("-s")) {
					System.out.print("请输入文件迭代查找的关键字:\n");
					Scanner getIn2 = new Scanner(System.in);
					// 需要拿到指定字符串，这里的方法需要两个参数
					WC.fileHandle(path, getIn2.nextLine());
				} else
					System.out.println("用户输入不合法，请重新输入！");
			}
		}
	}
}
