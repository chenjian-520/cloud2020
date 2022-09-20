package com.remake.cj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author LSJianChen1
 * 主窗口
 */
public class DialogDemo extends JFrame {
    public DialogDemo() {
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
                new MyDialogDemo();
            }
        });
        container.add(button);      //将按钮放进容器中
    }

    public static void main(String[] args) {
        new DialogDemo();
    }

}

//弹窗的窗口
class MyDialogDemo extends JDialog {
    public void MyDialogDemo() {
        this.setVisible(true);
        this.setBounds(100, 100, 500, 500);
        // this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    弹窗可以被关掉，不需要额外添加事件
        Container container = this.getContentPane();
        container.setLayout(null);

        container.add(new Label("Java弹窗"));
    }
}
