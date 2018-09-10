package pers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class WC {
                    
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
