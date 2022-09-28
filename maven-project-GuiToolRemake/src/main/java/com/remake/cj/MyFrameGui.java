package com.remake.cj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author cj
 * @Title: Gui图形化
 * @JdkVersion JDK1.8
 * @ClassName MyFrameGui
 * @Version 1.0.0
 * @date 2022/9/1110:43
 * @Description: Gui图形化
 */
public class MyFrameGui extends JFrame {

    private Container container = this.getContentPane();

    public static void main(String[] args) {
        new MyFrameGui();
    }

    public MyFrameGui() throws HeadlessException {
        initMyFrameGui();
    }

    private void initMyFrameGui() {
        this.setVisible(true);      //可见
        this.setSize(700, 500);          //尺寸
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //关闭事件
        //JFrame 放东西，容器
        Container container = this.getContentPane();
        //绝对布局，会相对容器自动定位
        container.setLayout(null);
        //按钮
        JButton button = new JButton("点击弹出一个对话框");      //创建
        button.setBounds(30, 30, 200, 50);
        //点击这个按钮的时候，弹出一个弹窗
        button.addActionListener(new ActionListener() {           //监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                //监听弹窗
                ReName.recursiveTraversalFolder("E:\\视频个人文件夹\\视频素材\\05-鬼畜视频制作\\鬼畜素材", "鬼畜", "s1e6", "",false);
            }
        });
        container.add(button);      //将按钮放进容器中
    }
}

