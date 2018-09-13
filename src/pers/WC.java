package pers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
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
					// 只要匹配的不是换行符，字符数就加一
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
		// 返回该文件的词的数目
		File file = new File(fileName);
		BufferedReader bur = null;
		String string = "";
		String line;
		int wordNum = 0;
		String words[] = null;
		try {
			bur = new BufferedReader(new FileReader(file));
			while ((line = bur.readLine()) != null) {
				String s = line.replaceAll("[\\p{Punct}\\s\\p{Nd}\\uffe5\\u4e00-]", " ");
				// 代码修改标记,此处仍有优化空间*****************************************
				// 得到除字符外全是空格的文本
				string = string + s + " ";
				// 这里要加空格，否则string每次增长，行末的词会和下一行首词相连
			}
			words = string.split(" ");
			// 去除空格，得到词的字符串数组
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
					// 匹配到换行符，行数就加一
					lineNum++;
				}
			}
			System.out.println("文件路径：" + fileName + "  文件的行数为： " + lineNum);
			readFile.close();
		} catch (Exception e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}

	}

	static void fileHandle(String fileName, String str) throws IOException {
		// 文件迭代处理
		List<File> fileList = new ArrayList<File>();
		File file = new File(fileName);
		File[] files = file.listFiles();
		// 获取目录下的所有文件或文件夹
		if (files == null) {
			// 如果目录为空，直接退出
			System.out.println("***系统提示：找不到指定路径！请重新输入：");
		}
		// 遍历，目录下的所有文件
		for (File f : files) {
			if (f.isFile()) {
				fileList.add(f);
			} else if (f.isDirectory()) {
				System.out.println(f.getAbsolutePath());
				fileHandle(f.getAbsolutePath(), str);
			}
		}
		for (File f1 : fileList) {
			if (f1.getName().contains(str) == true) {
				// 文件名包含指定字符串，contains()方法值为true
				WC.allCount(fileName + "/" + f1.getName());
				// f1.getName()只是文件名，这里要在f1.getName()前加入作为参数传入的fileName路径，否则会找不到文件
			}
		}
		// System.out.println("测试代码"); 左侧为测试用代码
	}

	static void allCount(String fileName) throws IOException {
		// 返回详细信息
		WC.charsCount(fileName);
		WC.wordsCount(fileName);
		WC.rowsCount(fileName);
		WC.complexCount(fileName);
		System.out.println("\n");
	}

	static void complexCount(String fileName) throws IOException {
		// 返回该文件的代码行 / 空行/注释行
		// 代码修改标记,此处仍有优化空间*****************************
		File file = new File(fileName);
		BufferedReader bufr = null;
		String line = null;
		int codeLine = 0;
		int blankLine = 0;
		int noteLine = 0;

		Pattern codePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
				Pattern.MULTILINE + Pattern.DOTALL);
		// 匹配代码行 // Mark:匹配代码曾行时出现了错误，还有优化空间*********
		Pattern blankPattern = Pattern.compile("^\\s*$");
		// 匹配空白行
		Pattern notePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
				Pattern.MULTILINE + Pattern.DOTALL);
		// 匹配注释行
		try {
			bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((line = bufr.readLine()) != null) {

				if (codePattern.matcher(line).matches())
					codeLine++;
				if (blankPattern.matcher(line).find())
					blankLine++;
				if (notePattern.matcher(line).find())
					noteLine++;
			}
			System.out.println(
					"文件路径：" + fileName + "  文件的代码行 / 空行 / 注释行数为： " + codeLine + "/" + blankLine + "/" + noteLine);
			bufr.close();
		} catch (FileNotFoundException e) {
			System.out.println("***系统提示：找不到指定的文件！请重新输入：");
		}
	}
}
