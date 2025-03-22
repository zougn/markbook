<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="markdown_views prism-atom-one-light">
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
  </svg>
  <p>pom文件引入依赖</p>
  <pre><code class="prism language-xml"><span class="token comment">&lt;!--引入thymeleaf的依赖--&gt;</span>
<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependency</span><span class="token punctuation">&gt;</span></span>
    <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>groupId</span><span class="token punctuation">&gt;</span></span>org.springframework.boot<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>groupId</span><span class="token punctuation">&gt;</span></span>
    <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>artifactId</span><span class="token punctuation">&gt;</span></span>spring-boot-starter-thymeleaf<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>artifactId</span><span class="token punctuation">&gt;</span></span>
<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependency</span><span class="token punctuation">&gt;</span></span>
</code></pre>
  <p>.properties</p>
  <pre><code class="prism language-xml"># 默认路径，前后缀
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
# 模板格式
spring.thymeleaf.mode=HTML5
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.content-type=text/html
spring.thymeleaf.cache=false
spring.thymeleaf.enabled=true
</code></pre>
  <p><strong>如果出现：</strong></p>
  <pre><code>org.xml.sax.SAXParseException: The element type "meta" must be terminated by the matching end-tag "&lt;/meta&gt;".
</code></pre>
  <p><strong>是因为spring-boot-starter-thymeleaf对html5默认校验要求高。一言不合就报错</strong><br> 解决方案：<br> 1、在pom.xml中添加依赖</p>
  <pre><code class="prism language-xml"><span class="token comment">&lt;!--启用不严格检查html--&gt;</span>
<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependency</span><span class="token punctuation">&gt;</span></span>
   <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>groupId</span><span class="token punctuation">&gt;</span></span>net.sourceforge.nekohtml<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>groupId</span><span class="token punctuation">&gt;</span></span>
   <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>artifactId</span><span class="token punctuation">&gt;</span></span>nekohtml<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>artifactId</span><span class="token punctuation">&gt;</span></span>
   <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>version</span><span class="token punctuation">&gt;</span></span>1.9.22<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>version</span><span class="token punctuation">&gt;</span></span>
<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependency</span><span class="token punctuation">&gt;</span></span>
</code></pre>
  <p>2、在 application.properties<br> 2.1、关闭缓存</p>
  <pre><code class="prism language-xml"> spring.thymeleaf.cache=false
</code></pre>
  <p>2.2、修改</p>
  <pre><code class="prism language-yaml">spring.thymeleaf.mode=LEGACYHTML5
</code></pre>
 </div>
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-a5d25dd831.css" rel="stylesheet">
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-e504d6a974.css" rel="stylesheet">
</div>
