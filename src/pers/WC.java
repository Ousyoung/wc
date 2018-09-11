package pers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.ws.util.StringUtils;

public class WC {

	static void charsCount(String fileName) {
		// 返回该文件的字符数
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
			System.out.println("文件路径：" + fileName + "  文件的字符数为： " + charsNum);
		} catch (Exception e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
		
	}

	static void wordsCount(String fileName) throws IOException {
		// 返回该文件的词的数目**********************************************************************************************************
		File file = new File(fileName);
		BufferedReader bur = null;
		String string = "";
		Pattern pattern = Pattern.compile("\\d.\\d+|\\w+");
		String line;
		int wordNum = 0;
		String words[] = null;
		try {
			bur = new BufferedReader(new FileReader(file));
			while ((line = bur.readLine()) != null) {
				String s = line.replaceAll("[\\p{Punct}\\s\\p{Nd}\\uffe5\\u4e00-]", " ");//*******************************************
				string = string + s; // 得到除字符外全是空格的文本
			}
			words = string.split(" "); // 去除空格
			wordNum = words.length;
			System.out.println("文件路径：" + fileName + "  文件的词数为： " + wordNum);
			bur.close();

		} catch (FileNotFoundException e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}

	}

	static void rowsCount(String fileName) {
		// 返回该文件的行数
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
			System.out.println("文件路径：" + fileName + "  文件的行数为： " + lineNum);
			readFile.close();
		} catch (Exception e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
		
	}

	static void complexCount(String fileName) throws IOException {
		// 返回该文件的代码行 / 空行
		// /注释行*******************************************************************************************************8
		File file = new File(fileName);
		BufferedReader bufr = null;
		String line = null;
		int codeLine = 0;
		int blankLine = 0;
		int noteLine = 0;

		Pattern codePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
				Pattern.MULTILINE + Pattern.DOTALL);
		// 匹配代码行// 匹配代码行时出现了错误

		Pattern blankPattern = Pattern.compile("^\\s*$");
		// 匹配空白行

		Pattern notePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
				Pattern.MULTILINE + Pattern.DOTALL);
		// 匹配注释行
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
			
			System.out.println("文件路径：" + fileName + "  文件的代码行 / 空行 / 注释行数为： " + codeLine + "/" + blankLine + "/" + noteLine);
			bufr.close();
		} catch (FileNotFoundException e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
		
	}
}
