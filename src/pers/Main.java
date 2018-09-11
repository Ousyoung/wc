package pers;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException {
		String command = null;
		String function = null;
		String path = null;
//		WC wc = new WC();
		System.out.println("\t WC.exe ʹ��˵��:\n");
		System.out.println("-c �ļ� �����ظ��ļ����ַ�����\n");
		System.out.println("-w �ļ� �����ظ��ļ��Ĵʵ���Ŀ��\n");
		System.out.println("-l �ļ� �����ظ��ļ���������\n");
		System.out.println("-a �ļ� �����ظ��ļ��Ĵ����� / ���� / ע���У�\n");
		System.out.println("-esc    ���˳�����\n");
		// System.out.println("-s �ļ� ���ݹ鴦��\n"); // ***************************
		System.out.print("��������������:\n");
		while (true) {

			Scanner getIn = new Scanner(System.in);
			if (getIn.hasNext())
				command = getIn.nextLine(); // ��ȡָ��

			if (command.equals("-esc")){
				
				System.out.println("***WC.exe�����ѹر�");
				System.exit(0);
				}
			else {
				String[] commands = new String[2];
				commands = command.split(" ", 2); // ����û������ָ��
				function = commands[0]; // commandǰ�벿��Ϊ����ѡ��
				path = commands[1]; //// command��벿��Ϊ�ļ�·�� ///�����������±�Խ�������

				if (function.equals("-c"))
					WC.charsCount(path);
				else if (function.equals("-w"))
					WC.wordsCount(path);
				else if (function.equals("-l"))
					WC.rowsCount(path);
				else if (function.equals("-a"))
					WC.complexCount(path);

				// else if(){}
				else
					System.out.println("�û����벻�Ϸ������������룡");

			}
		}
	}
}
