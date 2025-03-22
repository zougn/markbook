package com.zougn.markbook.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.commonmark.renderer.markdown.MarkdownRenderer;
import org.commonmark.parser.Parser;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service; 
import lombok.AllArgsConstructor;
import com.zougn.markbook.conf.GitConfig;

@Service
@AllArgsConstructor
public class BlogConverter {

    // 配置参数
    private final GitConfig gitConfig;
    private static final String SAVE_PATH = "blogs/";
    private static final String IMAGE_PATH = SAVE_PATH + "assets/";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";

    public static void main(String[] args) {
	    GitConfig gitConfig = new GitConfig();
	    gitConfig.setPath("/root/github/");
	BlogConverter blogConverter=    new BlogConverter(gitConfig);
       blogConverter.convertCsdnToMd("https://blog.csdn.net/qq_36449972/article/details/136644928","云计算服务之SaaS、Paas和Iaas","java/");
       blogConverter.convertCnblogsToMd("https://www.cnblogs.com/stars-one/p/16975863.html","Jgit的使用笔记","java/");
    }

    // 转换CSDN文章
    public  void convertCsdnToMd(String url,String title,String folder) {
        try {
            // 1. 获取HTML
            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .cookie("login_token", "your_cookie") // 需登录时添加
                    .get();

            // 2. 提取内容
            //String title = doc.selectFirst(".title-article").text();
            String content = doc.selectFirst("article").html();

            // 3. 转换Markdown
            String mdContent = htmlToMd(content);

            // 4. 处理图片
            mdContent = downloadImages(mdContent);

            // 5. 保存文件

	     saveToFile(title, mdContent,folder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 转换博客园文章
     private void convertCnblogsToMd(String url,String title,String folder) {
        try {
            Document doc = Jsoup.connect(url).get();
            //String title = doc.selectFirst("#post_detail").selectFirst("a").text();
            String content = doc.selectFirst("#post_body").html();

            String mdContent = htmlToMd(content);
            mdContent = downloadImages(mdContent);

            saveToFile(title, mdContent,folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // HTML转Markdown
    private String htmlToMd(String html) {
        Parser parser = Parser.builder().build();
        MarkdownRenderer renderer = MarkdownRenderer.builder().build();
        return renderer.render(parser.parse(html));
    }

    // 下载图片并替换路径
    private String downloadImages(String mdContent) {
        Pattern pattern = Pattern.compile("!\\[.*?\\]\\((.*?)\\)");
        Matcher matcher = pattern.matcher(mdContent);

        while(matcher.find()) {
            String imgUrl = matcher.group(1);
            try {
                // 下载图片到本地
                String fileName = "img_" + System.currentTimeMillis() + ".png";
                InputStream in = new URL(imgUrl).openStream();
                Files.copy(in, Paths.get(gitConfig.getPath()+IMAGE_PATH + fileName));

                // 替换路径
                mdContent = mdContent.replace(imgUrl, "assets/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mdContent;
    }

    private void saveToFile(String title, String content,String folder) {
        try {
	    String path = gitConfig.getPath()+SAVE_PATH+folder;
            Files.createDirectories(Paths.get(path));
            String filename = title.replaceAll("[\\\\/:*?\"<>|]", "_") + ".md";
            Files.write(Paths.get(path + filename), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
