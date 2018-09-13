# 软件工程—WC功能实现（JAVA）



## 项目要求
​     wc.exe 是一个常见的工具，它能统计文本文件的字符数、单词数和行数。这个项目要求写一个命令行程序，模仿已有wc.exe 的功能，并加以扩充，给出某程序设计语言源文件的字符数、单词数和行数。实现一个统计程序，它能正确统计程序文件中的字符数、单词数、行数，以及还具备其他扩展功能，并能够快速地处理多个文件。

 基本要求

​    -c   [filename]统计文件字符数 
    -w  [filename]统计文件词数 
    -l    [filename]统计文件行数
 扩展功能
    -s    [filename]递归处理目录下符合条件得文件
    -a    [filename]返回文件代码行 / 空行 / 注释行
​           支持各种文件的通配符（*,?）  
 高级功能
    -x    图形化界面（**未实现**）

**Github项目地址：**https://github.com/Ousyoung/wc



## PSP
| **PSP2.1**                              | **Personal Software Process Stages**    | **预估耗时（分钟）** | **实际耗时（分钟）** |
| --------------------------------------- | --------------------------------------- | -------------------- | -------------------- |
| Planning                                | 计划                                    | 40                   | 60                   |
| · Estimate                              | · 估计这个任务需要多少时间              | 40                   | 60                   |
| Development                             | 开发                                    | 850                  | 1400                 |
| · Analysis                              | · 需求分析 (包括学习新技术)             | 60                   | 120                  |
| · Design Spec                           | · 生成设计文档                          | 40                   | 120                  |
| · Design Review                         | · 设计复审 (和同事审核设计文档)         | 40                   | 60                   |
| · Coding Standard                       | · 代码规范 (为目前的开发制定合适的规范) | 20                   | 60                   |
| · Design                                | · 具体设计                              | 80                   | 120                  |
| · Coding                                | · 具体编码                              | 500                  | 800                  |
| · Code Review                           | · 代码复审                              | 40                   | 30                   |
| · Test                                  | · 测试（自我测试，修改代码，提交修改）  | 70                   | 90                   |
| Reporting                               | 报告                                    | 80                   | 90                   |
| · Test Report                           | · 测试报告                              | 40                   | 50                   |
| · Size Measurement                      | · 计算工作量                            | 10                   | 20                   |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划          | 30                   | 20                   |
| 合计                                    |                                         | 970                  | 1550                 |

## 解题思路

​    此次项目是模拟WC统计文件字，词，行信息，很容易想到用JAVA中的I/O流来读取文件信息，对于读取文件字符数以及行数，这些都比较简单，字符数只需读出字符匹配不是换行符即可，行数只需没读到换行符就加一，基本功能当中就属词的统计较为不易，词的界定相对来说要模糊的多，所以想到需要用正则表达式来匹配字符串，替换掉一些分隔符号，继而使词的匹配简单化。

​    扩展功能方面，无非是在以上基础上再多加细分和应用。代码行/空行/注释行同样需要使用正则表达式来进行匹配，其界定一开始自己也很苦恼，但上网查阅资料，弄明白后也就迎刃而解了；至于文件的递归查找，只需要设计一个递归方法，递归查找指定目录下所有文件名，再通过用户键入的关键字作为方法参数，用contains(string)方法检索出符合条件的文件即可。

## 设计实现

   此次项目中只写了两个类，一个是存放各种统计方法的**WC**类，另一个是运行程序的主类**Main**类，用于启动程序，里面还包括了命令获取，命令匹配的代码。

   运行程序，main方法启动，系统显使用说明，再通过用户键入得到命令，考虑到项目要求的命令显示，需要把命令拆分成两部分：功能选择**function**以及文件路径**path**，继而需要调用split(" "）方法，用空格分割命令得到子命令，再用多个**if**语句的嵌套，匹配调用**WC**类中不同的静态方法，将**path**作为参数输入，找到指定文件。

  设计主类**Main**类中的静态方法：词统计**wordsCount**，代码行，空行统计**complexCount**，文件递归处理**fileHandle**  等（**只列举了部分，详见代码说明**）

**类，方法调用关系图如下**

![](D:\git-hub\wc\img\g.PNG)

## 代码说明

#### main方法

数据的输入输出处理，命令的提取，匹配都在这一部分，下面主要展示命令的提取，匹配部分。这里使用nextLIne方法获取命令，再用split分割命令，得到子命令，再进行匹配;外层使用whlie循环访问，直至用户键入退出命令 -esc 退出

```
while (true) {
			Scanner getIn = new Scanner(System.in);
			if (getIn.hasNext())
				command = getIn.nextLine(); // 获取指令
			if (command.equals("-esc")) {
				System.out.println("***WC.exe程序已关闭");
				System.exit(0); // 关闭程序
			} else {
				String[] commands = new String[2]; // 字符串数组用于存放指令
				commands = command.split(" ", 2); // 拆分用户输入的指令
				function = commands[0]; // command前半部分为功能选择
				path = commands[1]; // 后半部分为文件路径
				// Mark:修改代码时出现了数组下标越界的问题*****************
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
					System.out.print("请输入文件迭代查找的关键字:\n");
					Scanner getIn2 = new Scanner(System.in);
					// 需要拿到指定字符串，这里的方法需要两个参数
					WC.fileHandle(path, getIn2.nextLine());
				} else
					System.out.println("用户输入不合法，请重新输入！");
			}
		}
```

#### WC类

##### 统计字符 charsCount

比较简单，所以只展示关键代码

```
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
		}
```

##### 统计行 rowsCount

同样比较简单，所以只展示关键代码

```
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
		}
```

##### 统计词 wordsCount

使用正则表达式，匹配字符，将文件字符串中的 . * " " / 等符号用reaplaceAll方法替换成空格，再用split方法用空格分割字符串，得到词

```
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
```

##### 统计代码行，注释行等 complexCount

使用正则表达式匹配相应行：“((//)|(/\+)|((^\s)\)|((^\s)\+/))+” 注释行，"^\s$"空白行，"(?!import|package).+;\s(((//)|(/\+)).)" 代码行 

	static void complexCount(String fileName) throws IOException {
			// 返回该文件的代码行 / 空行/注释行
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


##### 文件递归处理 fileHandle 及 allCount

递归返回目录下所有文件名，再调用String类的contains()方法模糊匹配所有文件名含指定字符串str的文件，再调用allCount方法返回各个文件的词，字符，行信息

```
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
```

	static void allCount(String fileName) throws IOException {
		// 返回详细信息
		WC.charsCount(fileName);
		WC.wordsCount(fileName);
		WC.rowsCount(fileName);
		WC.complexCount(fileName);
		System.out.println("\n");
	}

## 运行测试

##### -c -w -l -a 测试  readme.txt文件

由于我写了一个allCount方法(命令 -all ），相当于一次执行-w -c -l -a ,所以对于以上命令只做部分测试

![](D:\git-hub\wc\img\a.PNG)

##### -all 测试   readme.txt文件

![](D:\git-hub\wc\img\b.png)

##### -s 测试  检索test目录下文件名含one的文件（onechar, oneword ,oneline,one）

![](D:\git-hub\wc\img\c.PNG)

##### 递归处理  .java源代码测试

![](D:\git-hub\wc\img\d.PNG)

*注：（上面截图第二行文字中“**迭代查找**”应为“**递归查找**”，截图的时候没注意，写错了......代码中已更改）*

##### 代码覆盖率

![](D:\git-hub\wc\img\f.PNG)

## 项目小结

​    此次项目是软件工程的第一次项目，总的来说感觉自己做的有些仓促，许多细节的地方还有优化的空间，比如正则表达式匹配词那里，对于一些中文词没有考虑在内；还有main方法里完全可以写一个匹配方法来处理用户命令，是代码结构看起来更加合理；部分代码（I/O流的创建）有些繁琐，可以再简洁化一点，达到更低的时间复杂度。不过，这次项目的收获也不少，首先是对正则表达式的掌握有了提升，以前自己都没怎么仔细了解正则表达式，做项目的时候，边做边学，现在自己也能写一些正则表达式了。再者，通过软件工程理论的学习，开始了解编程的设计步骤，从无到有，一步一步来，效率有所提高，正如老师所说的，前面的准备工作做足，考虑更全面，后面的代码编写就会轻松高效很多。