package pers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class WC {
                    
	int charsCount(String fileName) { // ���ظ��ļ����ַ���

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
			System.out.println("������ļ�������");
		}
		return charsNum;
	}

	int wordsCount(String fileName) { // ���ظ��ļ��Ĵʵ���Ŀ
		return 0;
	}

	int rowsCount(String fileName) { // ���ظ��ļ�������
		return 0;
	}

	int complexCount(String fileName) { // ���ظ��ļ��Ĵ����� / ���� / ע����
		return 0;
	}
}
