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
		// ���ظ��ļ����ַ���
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
			System.out.println("�ļ�·����" + fileName + "  �ļ����ַ���Ϊ�� " + charsNum);
		} catch (Exception e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
		
	}

	static void wordsCount(String fileName) throws IOException {
		// ���ظ��ļ��Ĵʵ���Ŀ**********************************************************************************************************
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
				string = string + s; // �õ����ַ���ȫ�ǿո���ı�
			}
			words = string.split(" "); // ȥ���ո�
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
					lineNum++;
				}
			}
			System.out.println("�ļ�·����" + fileName + "  �ļ�������Ϊ�� " + lineNum);
			readFile.close();
		} catch (Exception e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
		
	}

	static void complexCount(String fileName) throws IOException {
		// ���ظ��ļ��Ĵ����� / ����
		// /ע����*******************************************************************************************************8
		File file = new File(fileName);
		BufferedReader bufr = null;
		String line = null;
		int codeLine = 0;
		int blankLine = 0;
		int noteLine = 0;

		Pattern codePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
				Pattern.MULTILINE + Pattern.DOTALL);
		// ƥ�������// ƥ�������ʱ�����˴���

		Pattern blankPattern = Pattern.compile("^\\s*$");
		// ƥ��հ���

		Pattern notePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
				Pattern.MULTILINE + Pattern.DOTALL);
		// ƥ��ע����
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
			
			System.out.println("�ļ�·����" + fileName + "  �ļ��Ĵ����� / ���� / ע������Ϊ�� " + codeLine + "/" + blankLine + "/" + noteLine);
			bufr.close();
		} catch (FileNotFoundException e) {
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
		
	}
}
