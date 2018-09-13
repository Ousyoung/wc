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
		// ���ظ��ļ����ַ���
		int charsNum = 0;
		File file = new File(fileName);
		Reader readFile = null;
		try {
			int tempchar;
			readFile = new InputStreamReader(new FileInputStream(file));
			while ((tempchar = readFile.read()) != -1) {
				if ((char) tempchar != '\r' && (char) tempchar != '\n') {
					// ֻҪƥ��Ĳ��ǻ��з����ַ����ͼ�һ
					charsNum++;
				}
			}
			readFile.close();
			System.out.println("�ļ�·����" + fileName + "  �ļ����ַ���Ϊ�� " + charsNum);
		} catch (Exception e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
	}

	static void wordsCount(String fileName) throws IOException {
		// ���ظ��ļ��Ĵʵ���Ŀ
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
				// �����޸ı��,�˴������Ż��ռ�*****************************************
				// �õ����ַ���ȫ�ǿո���ı�
				string = string + s + " ";
				// ����Ҫ�ӿո񣬷���stringÿ����������ĩ�Ĵʻ����һ���״�����
			}
			words = string.split(" ");
			// ȥ���ո񣬵õ��ʵ��ַ�������
			wordNum = words.length;
			System.out.println("�ļ�·����" + fileName + "  �ļ��Ĵ���Ϊ�� " + wordNum);
			bur.close();

		} catch (FileNotFoundException e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
	}

	static void rowsCount(String fileName) {
		// ���ظ��ļ�������
		File file = new File(fileName);
		Reader readFile = null;
		int lineNum = 1;
		try {
			readFile = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = readFile.read()) != -1) {
				if ((char) tempchar == '\n') {
					// ƥ�䵽���з��������ͼ�һ
					lineNum++;
				}
			}
			System.out.println("�ļ�·����" + fileName + "  �ļ�������Ϊ�� " + lineNum);
			readFile.close();
		} catch (Exception e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}

	}

	static void fileHandle(String fileName, String str) throws IOException {
		// �ļ���������
		List<File> fileList = new ArrayList<File>();
		File file = new File(fileName);
		File[] files = file.listFiles();
		// ��ȡĿ¼�µ������ļ����ļ���
		if (files == null) {
			// ���Ŀ¼Ϊ�գ�ֱ���˳�
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ��·�������������룺");
		}
		// ������Ŀ¼�µ������ļ�
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
				// �ļ�������ָ���ַ�����contains()����ֵΪtrue
				WC.allCount(fileName + "/" + f1.getName());
				// f1.getName()ֻ���ļ���������Ҫ��f1.getName()ǰ������Ϊ���������fileName·����������Ҳ����ļ�
			}
		}
		// System.out.println("���Դ���"); ���Ϊ�����ô���
	}

	static void allCount(String fileName) throws IOException {
		// ������ϸ��Ϣ
		WC.charsCount(fileName);
		WC.wordsCount(fileName);
		WC.rowsCount(fileName);
		WC.complexCount(fileName);
		System.out.println("\n");
	}

	static void complexCount(String fileName) throws IOException {
		// ���ظ��ļ��Ĵ����� / ����/ע����
		// �����޸ı��,�˴������Ż��ռ�*****************************
		File file = new File(fileName);
		BufferedReader bufr = null;
		String line = null;
		int codeLine = 0;
		int blankLine = 0;
		int noteLine = 0;

		Pattern codePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
				Pattern.MULTILINE + Pattern.DOTALL);
		// ƥ������� // Mark:ƥ���������ʱ�����˴��󣬻����Ż��ռ�*********
		Pattern blankPattern = Pattern.compile("^\\s*$");
		// ƥ��հ���
		Pattern notePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
				Pattern.MULTILINE + Pattern.DOTALL);
		// ƥ��ע����
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
					"�ļ�·����" + fileName + "  �ļ��Ĵ����� / ���� / ע������Ϊ�� " + codeLine + "/" + blankLine + "/" + noteLine);
			bufr.close();
		} catch (FileNotFoundException e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
	}
}
