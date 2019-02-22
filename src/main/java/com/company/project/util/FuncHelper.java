package com.company.project.util;

import com.company.project.core.ServiceException;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class FuncHelper {

    /**
     * 获取resoueces路径
     * @return
     */
    public static String  getResourcesPath() {
        try {
            return ResourceUtils.getURL("classpath:").getPath();

        } catch (FileNotFoundException e) {
            throw new ServiceException("resource路径错误");
        }
    }

    /**
     * 格式化章节
     * @param chapter
     * @param chapterNode
     * @return
     */
    public static Integer formatChapter(Integer chapter, Integer chapterNode) {
        return Integer.parseInt(String.format("1%02d%02d", chapter, chapterNode));
    }
}
