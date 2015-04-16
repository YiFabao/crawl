package com.yifabao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class RegrexTest extends TestCase{
	
	public void testA(){
		//查找以java开头，任意 不包括\n在内的结尾的字符串
		String str = "java c是一门编程语言";
		String regex = "^java.*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean b = matcher.matches();
		//当条件满足时，将返回true,否则返回false
		System.out.println(b);
		
	}
	
	public void testB(){
		//以多个条件分割字符串
		String regex = "[, |]+";//以逗号，空格或|　中的一个或多个
		Pattern pattern = Pattern.compile(regex);
		String[] strs = pattern.split("Java Hello World     Java,Hello,,World|Sun");
		for(int i=0;i<strs.length;i++)
		{
			System.out.println(strs[i]);
		}
	}
	
	public void testC(){
		//文字替换，首次出现
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式　Hello World,正则表达式　Hello World,正则表达式　Hello World");
		//替换第一个符合正则表达式的数据
		System.out.println(matcher.replaceFirst("Java"));
		//替换全部
		System.out.println(matcher.replaceAll("java"));
	}
	
	public void testD(){
		//文字替换，首次出现
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式　Hello World,正则表达式　Hello World,正则表达式　Hello World");
		//文字替换(置换字符)
		StringBuffer sbr = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sbr, "Java");//将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。 
			System.out.println(sbr.toString());
		}
		matcher.appendTail(sbr);//将剩余的字符串添加到一个StringBuffer对象里
		System.out.println(sbr.toString());
	}
	
	public void testE(){
		//验证是否为邮箱
		//String str = "ceponl-ine@yahhoo.com.cn";
		//String regex = "[\\w\\.\\-]+@([\\w]+\\.)+[\\w\\-]+";//邮箱的正则表达式
		
		String str = "d--asdf6\n";
		String regex = "d\\-+[a-z]+\\d{1}[\\n.]*";
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
	}
	
	public void testF(){
		//去除html标记
		String htmlStr="<a href = \"index.html\">主页</a>cc";
		String regex = "<.+?>";//加?代表最少匹配，非贪婪，否则<a  .../a>会被匹配替换为""
		Pattern pattern = Pattern.compile(regex,Pattern.DOTALL);//在 dotall 模式下，表达式.匹配包括行结束符在内的任意字符。默认情况下，表达式不会匹配行结束符。
		Matcher matcher = pattern.matcher(htmlStr);
		System.out.println(matcher.replaceAll(""));
	}
	
	public void testG(){
		//查找html中对应条件的字符串
		String htmlStr="<a href = \"index.html\">主页</a>cc";
		String regex = "href\\s*=\\s*\"(.+?)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlStr);
	
		while (matcher.find()) {
			System.out.println(matcher.group(0));//group就是以（）分出来的子Pattern　group(0) 代表整个表达式
			System.out.println(matcher.group(1));
		}
	}
	
	public void testH(){
		//截取http://地址
		String regex = "(http://|https://){1}([\\w\\.\\-/:]+)";
		String url = "asdfa<http://dsds//askdfjasdk//asdfa>dsfshttps://www.baidu.com?a=df&sd=d";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		List<String> urlList = new ArrayList<String>();
		while(matcher.find()){
			urlList.add(matcher.group(0));
		}
		
		for(String u:urlList)
		{
			System.out.println(u);
		}
	}
	
	public void testI(){
		//替换{}中的文字
		String str = "Java 目前的发展史是由{0}年－{1}年";
		String[][] object ={new String[]{"\\{0\\}","1995"},new String[]{"\\{1\\}","2007"}};
		System.out.println(replace(str, object));
	}
	
	public static String replace(final String sourceString,Object[] object){
		String temp = sourceString;
		for(int i=0;i<object.length;i++)
		{
			String[] result =(String[])object[i];
			Pattern pattern = Pattern.compile(result[0]);
			Matcher matcher = pattern.matcher(temp);
			temp=matcher.replaceAll(result[1]);
		}
		return temp;
	}
	
	public void testJ(){
		//以正则表达式查询指定目录下的文件
		
	}
	
	public void testK(){
		String regex = "(a\\w+)((de\\w+)(ij\\w+))";//分组按从外到里，从左到右的顺序
		String str = "abcdefghijk";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			int c = matcher.groupCount();
			System.out.println(c);
			for(int i = 0;i<=c;i++){
				System.out.println(i+"  "+matcher.group(i));
			}
		
			System.out.println("=====================");
		}
	
		
	}
	
	
	
}

