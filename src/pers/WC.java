package pers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class WC {
	Scanner getIn = null;
	String command = null;

	public WC() {
		// while (true) {
		// System.out.println("\t WC.exe 使用说明:\n");
		// System.out.println("-c 文件 （返回该文件的字符数）\n");
		// System.out.println("-w 文件 （返回该文件的词的数目）\n");
		// System.out.println("-l 文件 （返回该文件的行数）\n");
		// System.out.println("-a 文件 （返回该文件的代码行 / 空行 / 注释行）\n");
		// System.out.println("-s 文件 （递归处理）\n"); // ***************************
		// getIn = new Scanner(System.in);
		// if (getIn.hasNext()) {
		// command = getIn.nextLine();
		// }
		// String[] key = command.split(" ");
		// String function = key[0];
		// String fileName = key[1];
		// switch (function) {
		// case "c":
		// charsCount(fileName);
		// case "w":
		// wordsCount(fileName);
		// case "l":
		// rowsCount(fileName);
		// case "a":
		// complexCount(fileName);
		// default:
		// System.out.println("输入有误请重新输入！\n");
		// }
		// }
		Scanner b = new Scanner(System.in);
		String a = String.valueOf(b);
		charsCount(a); // 测试用
		wordsCount(a); // 测试用
		rowsCount(a); // 测试用
		complexCount(a); // 测试用
	}
          
             
	int charsCount(String fileName) { // 返回该文件的字符数

		int charsNum = 0;
		File file = new File(fileName);
		Reader readFile = null;
		try {
			int tempchar;
			readFile = new InputStreamReader(new FileInputStream(file));
			while ((tempchar = readFile.read()) != -1) {
				if ((char) tempchar != '\r' && (char) tempchar != '\n') {
					charsNum++;
				}
			}
			readFile.close();
		} catch (Exception e) {
			System.out.println("输入的文件不存在");
		}
		return charsNum;
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
}
