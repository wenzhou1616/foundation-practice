package com.herry.code.practice.week07.chatSystem.client.start;


import com.herry.code.practice.week07.chatSystem.client.service.FileClientService;
import com.herry.code.practice.week07.chatSystem.client.service.MessageClientService;
import com.herry.code.practice.week07.chatSystem.client.service.UserClientService;
import java.io.IOException;
import java.util.Scanner;
 
/**
 * 客户端菜单界面
 *
 * @author herry
 * @date 2023/12/26
 */
public class View {
    /**
     * 控制是否继续循环
     */
    private boolean loop = true;

    /**
     * 输入
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * 用于执行登录
     */
    private UserClientService userClientService = new UserClientService();

    /**
     * 用于消息管理
     */
    private MessageClientService messageClientService = new MessageClientService();

    /**
     * 用于发送文件
     */
    private FileClientService fileClientService = new FileClientService();

    /**
     * 启动
     */
    public static void main(String[] args) throws IOException {
        new View().mainMenu();
        System.out.println("客户端退出...");
        sc.close();
    }
    /**
     * 客户端菜单
     */
    private void mainMenu() throws IOException {
        while (loop) {
            System.out.println("===========Welcome to the system of chat:===========");
            System.out.println("\t\t1.登录系统");
            System.out.println("\t\t9.退出系统");


            System.out.print("请输入你的选择：");
            String key = sc.nextLine();

            switch (key) {
                case "1" :
                    // 登录操作
                    System.out.print("请输入用户名：");
                    String userId = sc.nextLine();
                    System.out.print("请输入密  码：");
                    String password = sc.nextLine();

                    // 验证登录的用户是否合法
                    if (userClientService.check(userId, password)) {
                        // 验证成功
                        System.out.println("\n===========Welcome user " + userId + "===========");
                        // 向用户显示二级菜单
                        while (loop) {
                            System.out.println("\n===========聊天室（user:" + userId + ")===========");
                            System.out.println("\t\t1.在线列表：");
                            System.out.println("\t\t2.群发消息：");
                            System.out.println("\t\t3.私发消息：");
                            System.out.println("\t\t4.文件发送：");
                            System.out.println("\t\t9.退出系统：");

                            System.out.print("请输入你的选择：");
                            key = sc.nextLine();

                            switch (key) {
                                case "1" :
                                    // 获取在线用户列表
                                    userClientService.onlineList();
                                    break;
                                case "2" :
                                    // 群发消息
                                    System.out.println("请输入你要对大家说的话：");
                                    String announcement = sc.nextLine();
                                    messageClientService.sendMessageToAll(announcement, userId);
                                    break;
                                case "3" :
                                    // 私发消息
                                    System.out.print("请输入你想聊天的对象（在线）,receiver = ");
                                    String receiver = sc.nextLine();
                                    System.out.print("请输入你要说的话: ");
                                    String content = sc.nextLine();
                                    messageClientService.sendMessageToOne(receiver, content, userId);
                                    break;
                                case "4" :
                                    // 发送文件
                                    System.out.print("请输入你想发送文件的对象（在线），receiver = ");
                                    String fileReceiver = sc.nextLine();
                                    System.out.print("请输入数据源文件的路径, souPath = ");
                                    String souPath = sc.nextLine();
                                    System.out.print("请输入目的地文件的路径, desPath = ");
                                    String desPath = sc.nextLine();
                                    fileClientService.setFileToOne(souPath, desPath, userId, fileReceiver);
                                    break;
                                case "9" :
                                    // 退出系统
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        // 验证失败
                        System.out.println("登录失败！请重新尝试！");
                    }

                    break;
                case "9" :
                    // 退出系统
                    loop = false;
                    break;
            }
        }

    }

}