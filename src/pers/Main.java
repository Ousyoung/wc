package pers;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException {
		String command = null;
		String function = null;
		String path = null;
		System.out.println("\t WC.exe ʹ��˵��:\n");
		System.out.println("-c �ļ� �����ظ��ļ����ַ�����\n");
		System.out.println("-w �ļ� �����ظ��ļ��Ĵʵ���Ŀ��\n");
		System.out.println("-l �ļ� �����ظ��ļ���������\n");
		System.out.println("-a �ļ� �����ظ��ļ��Ĵ����� / ���� / ע���У�\n");
		System.out.println("-all �ļ� �������ַ������ʣ��е���Ϣ��\n");
		System.out.println("-s �ļ� ���ļ��ݹ���Ҵ���\n");
		System.out.println("-esc   ���˳�����\n");

		System.out.print("��������������:\n");
		while (true) {
			Scanner getIn = new Scanner(System.in);
			if (getIn.hasNext())
				command = getIn.nextLine(); // ��ȡָ��
			if (command.equals("-esc")) {
				System.out.println("***WC.exe�����ѹر�");
				System.exit(0); // �رճ���
			} else {
				String[] commands = new String[2]; // �ַ����������ڴ��ָ��
				commands = command.split(" ", 2); // ����û������ָ��
				function = commands[0]; // commandǰ�벿��Ϊ����ѡ��
				path = commands[1]; // ��벿��Ϊ�ļ�·��
				// Mark:�޸Ĵ���ʱ�����������±�Խ�������*****************
				if (function.equals("-c"))
					WC.charsCount(path);
				else if (function.equals("-w"))
					WC.wordsCount(path);
				else if (function.equals("-l"))
					WC.rowsCount(path);
				else if (function.equals("-a"))
					WC.complexCount(path);
				else if (function.equals("-all"))
					WC.allCount(path);
				else if (function.equals("-s")) {
					System.out.print("�������ļ��������ҵĹؼ���:\n");
					Scanner getIn2 = new Scanner(System.in);
					// ��Ҫ�õ�ָ���ַ���������ķ�����Ҫ��������
					WC.fileHandle(path, getIn2.nextLine());
				} else
					System.out.println("�û����벻�Ϸ������������룡");
			}
		}
	}
}
