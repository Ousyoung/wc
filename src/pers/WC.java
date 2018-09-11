package pers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WC {

	void charsCount(String fileName) { // 返回该文件的字符数

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
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
		System.out.println("文件路径：" + fileName + "  文件的字符数为： " + charsNum);
	}

	void wordsCount(String fileName) { // 返回该文件的词的数目
		System.out.println("************************************");
	}

	void rowsCount(String fileName) { // 返回该文件的行数
		File file = new File(fileName);
		Reader readFile = null;
		int lineNum = 1;
		try {
			readFile = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = readFile.read()) != -1) {
				if ((char) tempchar == '\n') {
					lineNum++;
				}
			}
			readFile.close();
		} catch (Exception e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
		System.out.println("文件路径：" + fileName + "  文件的行数为： " + lineNum);
	}

	void complexCount(String fileName) throws IOException { // 返回该文件的代码行 / 空行 /
															// 注释行
		File file = new File(fileName);
		BufferedReader bufr = null;
		String line = null;
		int codeLine = 0;
		int blankLine = 0;
		int noteLine = 0;    
		
		Pattern codePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
				Pattern.MULTILINE + Pattern.DOTALL); // 代码行匹配器（以分号结束为一行有效语句,但不包括import和package语句）
                                             /////匹配代码行时出现了错误
	

		Pattern blankPattern = Pattern.compile("^\\s*$"); // 空白行匹配器（匹配回车、tab键、空格）
		
		Pattern notePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
				Pattern.MULTILINE + Pattern.DOTALL); // 注释匹配器(匹配单行、多行、文档注释)

		
		try {
			bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((line = bufr.readLine()) != null) {
				if (codePattern.matcher(line).matches()) {
					codeLine++;
				}
				if (blankPattern.matcher(line).find()) {
					blankLine++;
				}
				if (notePattern.matcher(line).find()) {
					noteLine++;
				}			
			}
			bufr.close();

		} catch (FileNotFoundException e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
		System.out.println("文件路径：" + fileName + "  文件的代码行 / 空行 / 注释行数为： " +codeLine+"/"+blankLine+"/"+noteLine);
	}
}
