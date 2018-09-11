package pers;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException {
		String command = null;
		String function = null;
		String path = null;
		WC wc = new WC();
		System.out.println("\t WC.exe ʹ��˵��:\n");
		System.out.println("-c �ļ� �����ظ��ļ����ַ�����\n");
		System.out.println("-w �ļ� �����ظ��ļ��Ĵʵ���Ŀ��\n");
		System.out.println("-l �ļ� �����ظ��ļ���������\n");
		System.out.println("-a �ļ� �����ظ��ļ��Ĵ����� / ���� / ע���У�\n");
		System.out.println("-s �ļ� ���ݹ鴦��\n"); // ***************************
		System.out.print("���������Ĳ�ѯ����:\n");
		while(true){
		
		Scanner getIn = new Scanner(System.in);
		if (getIn.hasNext()) {
			command = getIn.nextLine(); // ��ȡָ��
		}
		String[] commands = new String[2];
		commands = command.split(" ", 2); // ����û������ָ��
		function = commands[0]; // commandǰ�벿��Ϊ����ѡ��
		path = commands[1]; //// command��벿��Ϊ�ļ�·�� ///�����������±�Խ�������

		if (function.equals("-c"))
			wc.charsCount(path);
		else if (function.equals("-w"))
			wc.wordsCount(path);
		else if (function.equals("-l"))
			wc.rowsCount(path);
		else if (function.equals("-a"))
			wc.complexCount(path);
		// else if(){}
		else
			System.out.println("�û����벻�Ϸ������������룡");

	}
}
	}
