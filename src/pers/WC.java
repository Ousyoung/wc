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

	void charsCount(String fileName) { // ���ظ��ļ����ַ���

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
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
		System.out.println("�ļ�·����" + fileName + "  �ļ����ַ���Ϊ�� " + charsNum);
	}

	void wordsCount(String fileName) { // ���ظ��ļ��Ĵʵ���Ŀ
		System.out.println("************************************");
	}

	void rowsCount(String fileName) { // ���ظ��ļ�������
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
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
		System.out.println("�ļ�·����" + fileName + "  �ļ�������Ϊ�� " + lineNum);
	}

	void complexCount(String fileName) throws IOException { // ���ظ��ļ��Ĵ����� / ���� /
															// ע����
		File file = new File(fileName);
		BufferedReader bufr = null;
		String line = null;
		int codeLine = 0;
		int blankLine = 0;
		int noteLine = 0;    
		
		Pattern codePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
				Pattern.MULTILINE + Pattern.DOTALL); // ������ƥ�������ԷֺŽ���Ϊһ����Ч���,��������import��package��䣩
                                             /////ƥ�������ʱ�����˴���
	

		Pattern blankPattern = Pattern.compile("^\\s*$"); // �հ���ƥ������ƥ��س���tab�����ո�
		
		Pattern notePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
				Pattern.MULTILINE + Pattern.DOTALL); // ע��ƥ����(ƥ�䵥�С����С��ĵ�ע��)

		
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
			System.out.println("***ϵͳ��ʾ���Ҳ���ָ�����ļ������������룺");
		}
		System.out.println("�ļ�·����" + fileName + "  �ļ��Ĵ����� / ���� / ע������Ϊ�� " +codeLine+"/"+blankLine+"/"+noteLine);
	}
}
