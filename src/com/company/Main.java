package com.company;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    private static String sp = File.separator;

    public static void main(String[] args) {
        try{
            System.out.println();
            String projectPath = Main.class.getResource("/").getFile();
            String basePath = new File(projectPath).getParentFile().getParent();
            File baseDir = new File(basePath);
            sout("检测到当前路径为：" + baseDir.getAbsolutePath());
            File inDir = new File(basePath + sp + "in");
            createDir(inDir);
            String outPath = basePath  + sp + "out";
            File outDir = new File(outPath);
            createDir(outDir);
            String destPath = outPath + sp + dateToStr(new Date(),"yyyy-MM-dd-HH-mm-ss") + ".pdf";
            sout("即将读取当前目录下的pdf文件：" + inDir.getAbsolutePath());
            List<File> list = new ArrayList<>();
            sout("正在按顺序加载pdf文件.....");
            for (int i = 0; i <= 100; i++) {
                File file = new File(inDir + sp + i + ".pdf");
                if(file.exists() && file.isFile()){
                    System.out.println("已读取文件：" + file.getName());
                    list.add(file);
                }
            }
            if(list.size() > 0){
                PdfUtils.mergePdf(destPath, list);
                System.out.println("生成完毕，请手动关闭。");
            }else{
                sout("未读取到文件。");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //
        try {
            Thread.sleep(30L * 24 * 60 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void sout(String str) {
        System.out.println(str);
        System.out.println();
    }

    public static void createDir(File dir) {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                sout("删除文件：" + dir.getName());
                dir.delete();
                sout("创建文件夹：" + dir.getName());
                dir.mkdirs();
            }
        } else {
            sout("创建文件夹：" + dir.getName());
            dir.mkdirs();
        }
    }


    public static String dateToStr(Date date, String formart) {
        try {
            if (date == null) {
                return "";
            }
            return new SimpleDateFormat(formart).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
