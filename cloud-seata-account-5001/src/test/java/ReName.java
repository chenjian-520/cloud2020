import java.io.File;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/9/90:02
 * @Description:
 */
public class ReName {

    public static void main(String[] args) {
        String path = "F:\\视频个人文件夹\\毒毒动漫素材\\野良神";
        String file = "野良神";
        String tihuan = "s1e6";
        recursiveTraversalFolder(path, file, tihuan);
    }

    public static void recursiveTraversalFolder(String path, String name, String tihuan) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder(file.getAbsolutePath(), name, tihuan);
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        // 截取.之前的字符串出来
                        int index = fileName.indexOf(".");
                        String name1 = fileName.substring(0, index);
                        if (name1.contains(tihuan)) {
                            name1 = name1.replace(tihuan, "");
                        }
                        // 截取.Mp4出来
                        String name2 = fileName.substring(index);
                        if (!fileName.contains(name)) {
                            parentPath = file.getParentFile();
                            newDir = new File(parentPath + "/" + name1.concat("-").concat(name).concat(name2));
                            file.renameTo(newDir);//重命名
                            System.out.println("修改后：" + newDir);
                        } else {
                            parentPath = file.getParentFile();
                            newDir = new File(parentPath + "/" + name1.concat(name2));
                            file.renameTo(newDir);//重命名
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
