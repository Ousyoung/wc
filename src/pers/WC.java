package pers;

import java.util.Scanner;

public class WC {
	Scanner getIn = null;
	String command = null;

	public WC() {
		// while (true) {
		System.out.println("\t WC.exe ʹ��˵��:\n");
		System.out.println("-c �ļ�      �����ظ��ļ����ַ�����\n");
		System.out.println("-w �ļ�      �����ظ��ļ��Ĵʵ���Ŀ��\n");
		System.out.println("-l �ļ�      �����ظ��ļ���������\n");
		System.out.println("-a �ļ�      �����ظ��ļ��Ĵ����� / ���� / ע���У�\n");
		System.out.println("-s �ļ�      ���ݹ鴦��\n"); // ***************************
		getIn = new Scanner(System.in);
		if (getIn.hasNext()) {
			command = getIn.nextLine();
		}
		String[] key = command.split(" |-");
		String function = key[0];
		String fileName = key[1];
		switch (function) {
		case "c":
			charsCount(fileName);
		case "w":
			wordsCount(fileName);
		case "l":
			rowsCount(fileName);
		case "a":
			complexCount(fileName);
		default:
			System.out.println("�����������������룡\n");
		}
	}
	// }

	int charsCount(String fileName) { // ���ظ��ļ����ַ���
		return 555;
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
	// int charCount(){
	// return 0;}
}
