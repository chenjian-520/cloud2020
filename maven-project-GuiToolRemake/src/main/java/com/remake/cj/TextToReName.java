package com.remake.cj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TextToReName {

    public static void main(String[] args) throws IOException {
        recursiveTraversalFolder("F:\\视频个人文件夹\\原片素材\\死神千年血战篇\\10", "F:\\视频个人文件夹\\原片素材\\名字.txt", "死神千年血战篇", "，", "-");
    }

    /**
     * 重命名文件名
     *
     * @param path     需要重命名文件路径（文件夹都可以）
     * @param pathFile 命名文件路径（文件内容文件名逗号隔开）
     * @param name     需要再原文件最后加的名字
     * @param split1   文件内容分隔符
     * @param split2   文件名分隔符
     */
    public static void recursiveTraversalFolder(String path, String pathFile, String name, String split1, String split2) throws IOException {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();

            // 读取文件排序
            List fileList = Arrays.asList(fileArr);
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (o1.isDirectory() && o2.isFile()) {
                        return -1;
                    }
                    if (o1.isFile() && o2.isDirectory()) {
                        return 1;
                    }
                    String name1 = o1.getName().substring(0, o1.getName().indexOf("."));
                    String name2 = o2.getName().substring(0, o2.getName().indexOf("."));
                    return Integer.valueOf(name1.split(split2)[1]).compareTo(Integer.valueOf(name2.split(split2)[1]));
                }
            });

            // 名字下标
            int fileIndex = 0;
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                // 读取文件txt
                Path pathfile = Paths.get(pathFile);
                byte[] data = Files.readAllBytes(pathfile);
                String result = new String(data, "utf-8");
                String[] split = result.replaceAll("[\t\n\r]", "").replaceAll(" ", "").split(split1);
                assert split != null : "读取文件为空";

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
                        recursiveTraversalFolder(file.getAbsolutePath(), pathFile, name, split1, split2);
                    } else {
                        if (fileIndex >= split.length) {
                            System.out.println("文件里面的名字已经用完!");
                            return;
                        }
                        //是文件，判断是否需要重命名
                        fileName = file.getName();
                        // 截取.之前的字符串出来 截取.之后出来。截取.之后出来
                        int index = fileName.indexOf(".");
                        String name1 = fileName.substring(0, index);
                        String name2 = fileName.substring(index);
                        System.out.println(fileName);
                        // 修改文件为新名字
                        if (".mp4".equals(name2) || ".mkv".equals(name2)) {
                            parentPath = file.getParentFile();
                            newDir = new File(parentPath + "/" + split[fileIndex].concat("-").concat(name).concat(name2));
                            file.renameTo(newDir);
                            System.out.println("修改后：" + newDir);
                            fileIndex++;
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

}
