package pers;

import java.util.Scanner;

public class WC {
	Scanner getIn = null;
	String command = null;

	public WC() {
		// while (true) {
		System.out.println("\t WC.exe 使用说明:\n");
		System.out.println("-c 文件      （返回该文件的字符数）\n");
		System.out.println("-w 文件      （返回该文件的词的数目）\n");
		System.out.println("-l 文件      （返回该文件的行数）\n");
		System.out.println("-a 文件      （返回该文件的代码行 / 空行 / 注释行）\n");
		System.out.println("-s 文件      （递归处理）\n"); // ***************************
		getIn = new Scanner(System.in);
		if (getIn.hasNext()) {
			command = getIn.nextLine();
		}
		String[] key = command.split(" |-");
		String function = key[0];
		String fileName = key[1];
		switch (function) {
		case "c":
			charsCount(fileName);
		case "w":
			wordsCount(fileName);
		case "l":
			rowsCount(fileName);
		case "a":
			complexCount(fileName);
		default:
			System.out.println("输入有误请重新输入！\n");
		}
	}
	// }

	int charsCount(String fileName) { // 返回该文件的字符数
		return 555;
	}

	int wordsCount(String fileName) { // 返回该文件的词的数目
		return 0;
	}

	int rowsCount(String fileName) { // 返回该文件的行数
		return 0;
	}

	int complexCount(String fileName) { // 返回该文件的代码行 / 空行 / 注释行
		return 0;
	}
	// int charCount(){
	// return 0;}
}
