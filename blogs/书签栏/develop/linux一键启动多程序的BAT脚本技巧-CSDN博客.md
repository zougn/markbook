<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="markdown_views prism-atom-one-light">
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
  </svg>
  <h2><a id="BAT_0"></a>一键启动多程序BAT脚本</h2>
  <h6><a id="IDEbat_2"></a>自己的电脑的是固态盘的，开机启动还挺快的，但是开机之后每次都要花费很多的时间，在启动开发环境上，以及IDE上，虽然启动速度不慢，但是一个个的点，感觉比较麻烦，于是推荐大家使用bat脚本语言来解决这个小问题（偷懒一下）。只需要一键就可以按照自己设定的打开顺序去启动各个软件。</h6>
  <h4><a id="txt_4"></a>第一步：在桌面新建一个记事本（.txt后缀的文件）</h4>
  <p><img src="https://i-blog.csdnimg.cn/blog_migrate/48e930d2f42c9b18530e8b7b5c454ebd.png" alt="在这里插入图片描述"></p>
  <h4><a id="_10"></a>第二步：复制下面的代码到新建的记事本中</h4>
  <pre><code class="prism language-vbscript">echo start
start "" "()"
exit
</code></pre>
  <h4><a id="exe_18"></a>第三步：找到需要一键启动的所有程序的.exe位置（快捷方式也可以）</h4>
  <p>比如Chrome：找到Chrome的快捷方式，鼠标右键—属性—目标，然后选中，再复制粘贴到记事本中，包括双引号（英文下的双引号)，其他程序，同样的方法复制目标路径到记事本中 。</p>
  <p><img src="https://i-blog.csdnimg.cn/blog_migrate/ca98beec549b4de428fd12d44c8153b1.png" alt="在这里插入图片描述"></p>
  <p>将刚刚复制到的内径 添加到括号中 并且去掉括号，在加上改应用程序的名字包括后缀名。</p>
  <p><img src="https://i-blog.csdnimg.cn/blog_migrate/24e1a74eaaaf194f990cbb858679153f.png" alt="在这里插入图片描述"></p>
  <h4><a id="txtbat_28"></a>第四步：修改记事本的后缀名，将.txt改为.bat</h4>
  <p><img src="https://i-blog.csdnimg.cn/blog_migrate/738943bc7fd7a971a7ba92a1be6fe2dd.png" alt="在这里插入图片描述"><br> 点击 : 是(<u>Y</u>)</p>
  <p><img src="https://i-blog.csdnimg.cn/blog_migrate/0eff29b3aaf99a230250b1d1a450831c.png" alt="在这里插入图片描述"><br> 双击执行即可</p>
  <h4><a id="__38"></a>第五步：多个应用程序 按照上面的代码重复添加即可</h4>
  <p>如图：</p>
  <pre><code class="prism language-vb">echo start

start "" "C:\\Users\\Administrator\\Desktop\\2019-12-6.txt"
start "" "D:\\JetBrains\\IntelliJ IDEA 2018.1.4\\bin\\idea64.exe"
start "" "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe"
start "" "D:\\JavaCool\\Navicat Premium 12\\navicat.exe"

exit
</code></pre>

  <p>程序会按照顺序依次执行</p>
  <h4><a id="_55"></a>注：为保证程序执行不会卡死，增加时间延迟启动</h4>
  <pre><code>&amp;ping localhost -n 数字
</code></pre>
  <p><code>&amp;</code>可以加也可以不加， 表示程序顺序执行，<code>ping localhost -n 1</code>表示1秒时间延迟启动</p>
  <p>如下：</p>
  <pre><code class="prism language-vb">echo start

start "" "C:\\Users\\Administrator\\Desktop\\2019-12-6.txt"\&ping localhost -n 1
start "" "D:\\JetBrains\\IntelliJ IDEA 2018.1.4\\bin\\idea64.exe"\&ping localhost -n 1
start "" "C:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe"\&ping localhost -n 2
start "" "D:\\JavaCool\\Navicat Premium 12\\navicat.exe"\&ping localhost -n 3
start "" "D:\\JavaCool\\Navicat Premium 12\\navicat.exe"\&ping localhost -n 3

exit
</code></pre>

 </div>
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-a5d25dd831.css" rel="stylesheet">
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-e504d6a974.css" rel="stylesheet">
</div>
