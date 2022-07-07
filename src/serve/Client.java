package serve;

import java.util.ArrayList;

public class Client {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ClientThread clientthread = new ClientThread();
        //声明一个Arraylist用于测试
        //传入的参数数组
        ArrayList<String> list = new ArrayList<String>();
        //传入的登录数组
//        ArrayList<String> login = new ArrayList<String>();
        //list数组为需要传入的参数
//        list.add("901");
        
        //教师端测试
//        list.add("49");
//        list.add("7"); //学期
//        list.add("5"); //最近五次的最高分最低分平均分
//        list.add("3"); //学科
        
        list.add("14454");
        list.add("3");
        
//        list.add("9");
        
        //教务端测试
        //学生信息录入
//        list.add("2020");
//        list.add("张燕波");
//        list.add("男");
//        list.add("汉族");
//        list.add("2019");
//        list.add("2000/1/1");
//        list.add("广东省揭阳市燕波县");
//        list.add("城镇");
//        list.add("党员");
//        list.add("1");
//        list.add("202");
//        list.add("0");
        //教师信息录入
//        list.add("10");
//        list.add("张燕波");
//        list.add("男");
//        list.add("汉族");
//        list.add("共产党员");
//        list.add("大学本科");
//        list.add("1990/1/1");
//        list.add("18901020304");
//        list.add("19ybzhang@stu.edu.cn");
//        list.add("901");
        
        
//        list.add("1");
//        list.add("5");
        
        
//        查看考勤
//        list.add("2017/9/21 16:48");
//        list.add("2018/5/28 6:32");

//        list.add("2017-9-21");
//        list.add("2018-5-29");
        //下面传入登录数组元素
//        login.add("123456");
//        login.add("1234");
        
        //调用类中的方法，传入参数为接口序号，输入参数
//        clientthread.Client(Integer.toString(19), list);
        
        clientthread.Client(Integer.toString(1), list);
//        Object object = clientthread.client(26,list,login);
//        System.out.println(object);
	}

}
