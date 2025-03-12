import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.commonmark.renderer.markdown.MarkdownRenderer;
import org.commonmark.parser.Parser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogConverter {
    
    // 配置参数
    private static final String SAVE_PATH = "./blogs/";
    private static final String IMAGE_PATH = SAVE_PATH + "assets/";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";

    public static void main(String[] args) {
        convertCsdnToMd("https://blog.csdn.net/xxx/article/details/123");
        convertCnblogsToMd("https://www.cnblogs.com/xxx/p/123.html");
    }

    // 转换CSDN文章
    public static void convertCsdnToMd(String url) {
        try {
            // 1. 获取HTML
            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .cookie("login_token", "your_cookie") // 需登录时添加
                    .get();

            // 2. 提取内容
            String title = doc.selectFirst(".title-article").text();
            String content = doc.selectFirst("article").html();

            // 3. 转换Markdown
            String mdContent = htmlToMd(content);
            
            // 4. 处理图片
            mdContent = downloadImages(mdContent);

            // 5. 保存文件
            saveToFile(title, mdContent);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 转换博客园文章
    public static void convertCnblogsToMd(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.selectFirst("#post_detail").selectFirst("a").text();
            String content = doc.selectFirst("#post_body").html();
            
            String mdContent = htmlToMd(content);
            mdContent = downloadImages(mdContent);
            
            saveToFile(title, mdContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HTML转Markdown
    private static String htmlToMd(String html) {
        Parser parser = Parser.builder().build();
        MarkdownRenderer renderer = MarkdownRenderer.builder().build();
        return renderer.render(parser.parse(html));
    }

    // 下载图片并替换路径
    private static String downloadImages(String mdContent) {
        Pattern pattern = Pattern.compile("!\\[.*?\\]\\((.*?)\\)");
        Matcher matcher = pattern.matcher(mdContent);
        
        while(matcher.find()) {
            String imgUrl = matcher.group(1);
            try {
                // 下载图片到本地
                String fileName = "img_" + System.currentTimeMillis() + ".png";
                InputStream in = new URL(imgUrl).openStream();
                Files.copy(in, Paths.get(IMAGE_PATH + fileName));
                
                // 替换路径
                mdContent = mdContent.replace(imgUrl, "assets/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mdContent;
    }

    private static void saveToFile(String title, String content) {
        try {
            Files.createDirectories(Paths.get(SAVE_PATH));
            String filename = title.replaceAll("[\\\\/:*?\"<>|]", "_") + ".md";
            Files.write(Paths.get(SAVE_PATH + filename), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

