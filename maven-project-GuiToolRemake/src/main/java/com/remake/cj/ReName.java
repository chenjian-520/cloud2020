package com.remake.cj;

import org.codehaus.plexus.util.StringUtils;

import java.io.File;
import java.util.regex.Pattern;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/9/9 0:02
 * @Description:
 */
public class ReName {

    public static void main(String[] args) {
        recursiveTraversalFolder("E:\\视频个人文件夹\\毒毒动漫素材\\间谍过家家灬公z号毒毒剪辑丶拥有千G素材哦等多个文件", "间谍过家家", " ", "-", false);
//        recursiveTraversalFolder2("E:\\视频个人文件夹\\毒毒动漫素材\\散华礼弥\\散华礼弥片段", "散华礼弥-补帧素材"," ", "-",false);
    }

    /**
     * 重命名文件名
     *
     * @param path      需要重命名文件路径（文件夹都可以）
     * @param name      需要再原文件最后加的名字
     * @param tihuan    需要被替换的名字
     * @param newString 替换的新名字
     * @param flag      是否去数字除后缀名
     */
    public static void recursiveTraversalFolder(String path, String name, String tihuan, String newString, Boolean flag) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                //文件所在文件夹路径+新文件名
                File newDir = null;
                String newName = "";
                String fileName = null;
                //文件所在父级路径
                File parentPath = new File("");
                for (File file : fileArr) {
                    //是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                    if (file.isDirectory()) {
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder(file.getAbsolutePath(), name, tihuan, newString, flag);
                    } else {
                        //是文件，判断是否需要重命名
                        fileName = file.getName();
                        // 截取.之前的字符串出来 截取.之后出来。截取.之后出来
                        int index = fileName.indexOf(".");
                        String name1 = fileName.substring(0, index);
                        String name2 = fileName.substring(index);

                        if (flag && name1.contains("_")) {
                            String[] split = name1.split("_");
                            //匹配纯数字
                            String regex = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
                            //编译正则表达式
                            Pattern pattern = Pattern.compile(regex);
                            if(split[1] != null && pattern.matcher(split[1]).matches()){
                                name1 = split[0];
                            }
                        }
                        // 替换名字
                        name1 = name1.replace(tihuan, newString);
                        name1 = name1.replace("_", "-");
                        name1 = name1.replace("--", "-");
                        name1 = name1.replace("---", "-");

                        // 修改文件为新名字
                        if (!fileName.contains(name)) {
                            parentPath = file.getParentFile();
                            newDir = new File(parentPath + "/" + name1.concat("-").concat(name).concat(name2));
                            file.renameTo(newDir);
                            System.out.println("修改后：" + newDir);
                        } else {
                            parentPath = file.getParentFile();
                            newDir = new File(parentPath + "/" + name1.concat(name2));
                            file.renameTo(newDir);
                            System.out.println("修改后：" + newDir);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private static Integer indexStatus = 0;

    public static void recursiveTraversalFolder2(String path, String name,String tihuan, String newString, Boolean flag) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                //文件所在文件夹路径+新文件名
                File newDir = null;
                String newName = "";
                String fileName = null;
                //文件所在父级路径
                File parentPath = new File("");
                for (File file : fileArr) {
                    //是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                    if (file.isDirectory()) {
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder2(file.getAbsolutePath(), name, tihuan, newString, flag);
                    } else {
                        //是文件，判断是否需要重命名
                        fileName = file.getName();
                        // 截取.之前的字符串出来 截取.之后出来。截取.之后出来
                        int index = fileName.indexOf(".");
                        String name1 = fileName.substring(0, index);
                        String name2 = fileName.substring(index);

                        // 替换名字
                        name1 = name1.replace(tihuan, newString);
                        name1 = name1.replace("_", "-");
                        name1 = name1.replace("--", "-");
                        name1 = name1.replace("---", "-");

                        // 修改文件为新名字
                        if (!fileName.contains(name)) {
                            parentPath = file.getParentFile();
                            String name3 = file.getParentFile().getName();
                            if(flag){
                                newDir = new File(parentPath + "/" + name3.concat("-").concat(name).concat(String.valueOf(indexStatus++)).concat(name2));
                            }else {
                                newDir = new File(parentPath + "/" + name.concat("-").concat(String.valueOf(indexStatus++)).concat(name2));
                            }
                            file.renameTo(newDir);
                            System.out.println("修改后：" + newDir);
                        } else {
                            parentPath = file.getParentFile();
                            newDir = new File(parentPath + "/" + name1.concat(name2));
                            file.renameTo(newDir);
                            System.out.println("修改后：" + newDir);
                        }

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }


}
