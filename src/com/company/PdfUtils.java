package com.company;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class PdfUtils {

  public static File mergePdf(String destPath, List<File> pdf ){


    // pdf合并工具类
    PDFMergerUtility mergePdf = new PDFMergerUtility();
    // 合成文件
    for (File file : pdf) {
      try {
        mergePdf.addSource(file);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    System.out.println("读取完毕，开始生成文件：" + destPath);
    System.out.println();
    // 设置合并生成pdf文件
    mergePdf.setDestinationFileName(destPath);
    // 合并pdf
    try {
      mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    } catch (Exception e) {
      System.out.println("pdf文件合并失败：请确保每个pdf文件都能正常打开，或者将以下代码错误消息截图发给制作者。。。");
      e.printStackTrace();
    }
    return new File(destPath);
  }

}
