<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="markdown_views prism-atom-one-light">
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
  </svg>
  <p>Pom导入依赖</p>
  <pre><code class="prism language-xml">		<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependency</span><span class="token punctuation">&gt;</span></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>groupId</span><span class="token punctuation">&gt;</span></span>org.mybatis.spring.boot<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>groupId</span><span class="token punctuation">&gt;</span></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>artifactId</span><span class="token punctuation">&gt;</span></span>mybatis-spring-boot-starter<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>artifactId</span><span class="token punctuation">&gt;</span></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>version</span><span class="token punctuation">&gt;</span></span>2.0.0<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>version</span><span class="token punctuation">&gt;</span></span>
        <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependency</span><span class="token punctuation">&gt;</span></span>
</code></pre>
  <p>application.yml</p>
  <pre><code class="prism language-yml"><span class="token comment">#配置数据源,yml格式</span>
<span class="token key atrule">spring</span><span class="token punctuation">:</span>
  <span class="token key atrule">datasource</span><span class="token punctuation">:</span>
     <span class="token key atrule">url</span><span class="token punctuation">:</span> jdbc<span class="token punctuation">:</span>mysql<span class="token punctuation">:</span>//127.0.0.1<span class="token punctuation">:</span>3306/dianping<span class="token punctuation">?</span>useUnicode=true<span class="token important">&amp;characterEncoding</span>=utf8
     <span class="token key atrule">username</span><span class="token punctuation">:</span> root
     <span class="token key atrule">password</span><span class="token punctuation">:</span> <span class="token number">123</span>
     <span class="token key atrule">driver-class-name</span><span class="token punctuation">:</span> com.mysql.jdbc.Driver
<span class="token comment">#指定mybatis映射文件的地址</span>
<span class="token key atrule">mybatis</span><span class="token punctuation">:</span>
  <span class="token key atrule">mapper-locations</span><span class="token punctuation">:</span> classpath<span class="token punctuation">:</span>mapper/*.xml
</code></pre>
  <p>项目结构<br> <img src="https://i-blog.csdnimg.cn/blog_migrate/0603a70a7ecb1f9824b6ceccbf2c312f.png" alt="在这里插入图片描述"><br> mybatis默认是属性名和数据库字段名一一对应的，即<br> 数据库表列：user_name<br> 实体类属性：user_name</p>
  <p>但是java中一般使用驼峰命名<br> 数据库表列：user_name<br> 实体类属性：userName</p>
  <p>在Springboot中，可以通过设置map-underscore-to-camel-case属性为true来开启驼峰功能。<br> application.properties中：</p>
  <pre><code class="prism language-yaml"><span class="token key atrule">mybatis</span><span class="token punctuation">:</span>
  <span class="token key atrule">configuration</span><span class="token punctuation">:</span>
    <span class="token key atrule">map-underscore-to-camel-case</span><span class="token punctuation">:</span> <span class="token boolean important">true</span>

</code></pre>

 </div>
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-a5d25dd831.css" rel="stylesheet">
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-e504d6a974.css" rel="stylesheet">
</div>
