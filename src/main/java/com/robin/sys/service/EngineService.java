package com.robin.sys.service;

import com.aspose.words.Document;
import com.aspose.words.ExportHeadersFootersMode;
import com.aspose.words.HtmlSaveOptions;
import com.robin.sys.controller.EngineController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;

@Service
public class EngineService {
    private static Logger logger = LoggerFactory.getLogger(EngineController.class);

    public String engineIdentify(String path) {
        File file = new File(path);
        Document doc = null;
        try {
            doc = new Document(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HtmlSaveOptions saveOptions = new HtmlSaveOptions();
        saveOptions.setExportHeadersFootersMode(ExportHeadersFootersMode.NONE); // HtmlSaveOptions的其他设置信息请参考相关API
        ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();
        String htmlText = "";
        try {
            doc.save(htmlStream, saveOptions);
            htmlText = new String(htmlStream.toByteArray(),"UTF-8");
            htmlStream.close();
        } catch (Exception e) {
            logger.error("word文件转换失败，详细错误信息：{}",e.getMessage());
        }
        return htmlText;
    }
}
