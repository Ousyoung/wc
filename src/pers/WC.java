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
		// System.out.println("\t WC.exe ʹ��˵��:\n");
		// System.out.println("-c �ļ� �����ظ��ļ����ַ�����\n");
		// System.out.println("-w �ļ� �����ظ��ļ��Ĵʵ���Ŀ��\n");
		// System.out.println("-l �ļ� �����ظ��ļ���������\n");
		// System.out.println("-a �ļ� �����ظ��ļ��Ĵ����� / ���� / ע���У�\n");
		// System.out.println("-s �ļ� ���ݹ鴦��\n"); // ***************************
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
		// System.out.println("�����������������룡\n");
		// }
		// }
		Scanner b = new Scanner(System.in);
		String a = String.valueOf(b);
		charsCount(a); // ������
		wordsCount(a); // ������
		rowsCount(a); // ������
		complexCount(a); // ������
	}
          
             
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
