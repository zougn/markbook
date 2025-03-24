<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="htmledit_views">
  <div id="js_content">
   <h3>前言</h3>
   <p>Chrome 扩展（通常也叫插件）也是软件程序，使用 Web（HTML, CSS, and JavaScript）技术栈开发。允许用户自定义 Chrome 浏览体验。开发者可以通过增加特效或功能来优化体验。例如：效率工具、信息聚合等等。你可以在 Chrome 应用商店 查看各种各样的扩展。比如一些翻译扩展、JSON 格式化扩展等等都极大提高了我们开发或工作效率。本文旨在帮助大家了解 Chrome 扩展开发的基本概念、开发流程。并最终开发一个背景颜色提取的扩展来加深 Chrome 扩展的印象。本文基于 Manifest V3，大部分内容翻译自https://developer.chrome.com/docs/extensions/mv3/。最终我们要实现的扩展功能如下：</p>
   <p style="text-align:center;"><img src="https://i-blog.csdnimg.cn/blog_migrate/146de8dc29bc5d6837167e8a50eff8ab.gif" alt="e52e122cbd307a4e19b512814d88359b.gif"></p>
   <h3>基本概念</h3>
   <h4>Manifest</h4>
   <p>扩展的 manifest 是唯一必须的文件，且文件名必须是 <code>manifest.json</code>。manifest 必须放在扩展程序的根目录，它记录了重要的 metadata、资源定义、权限声明并标识了哪些文件运行在后台和页面上。</p>
   <h4>Service Worker</h4>
   <p>扩展的 Service Worker 用户处理和监听 <strong>浏览器</strong> 事件。比如跳转到一个新页面、书签移除、tab 关闭等。它可以使用所有的 Chrome API，但是不能直接和网页交互（和网页交互需要 Content Scripts）。</p>
   <h4>Content Scripts</h4>
   <p>Content Script 可以在网页上下文中执行 JavaScript。也可以读取和修改它们注入页面的 DOM。Content Script只能使用部分 Chrome API。其余的可以通过 Service Worker 间接访问。</p>
   <h4>Popup 和 Pages</h4>
   <p>扩展可以包含多种 HTML 文件，比如弹窗、页面选项等，这些页面都可以访问 Chrome API。</p>
   <h3>开发一个简单扩展</h3>
   <p>下面我们来开发一个 <strong>Hello Extensions <strong>的简单扩展</strong>。</strong></p>
   <h4>创建 manifest.json 文件</h4>
   <p>创建一个新的目录，在目录下创建一个名为 <code>manifest.json</code> 的文件。</p>
   <pre class="has"><code class="language-go">mkdir&nbsp;hello_extension
cd&nbsp;hello_extension
touch&nbsp;manifest.json</code></pre>
   <p>在 <code>manifest.json</code> 中加入以下代码：</p>
   <pre class="has"><code class="language-go">{
&nbsp;&nbsp;"manifest_version":&nbsp;3,
&nbsp;&nbsp;"name":&nbsp;"Hello&nbsp;Extensions",
&nbsp;&nbsp;"description":&nbsp;"Base&nbsp;Level&nbsp;Extension",
&nbsp;&nbsp;"version":&nbsp;"1.0",
&nbsp;&nbsp;"action":&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;"default_popup":&nbsp;"hello.html",
&nbsp;&nbsp;&nbsp;&nbsp;"default_icon":&nbsp;"hello_extensions.png"
&nbsp;&nbsp;}
}</code></pre>
   <p>上面的 JSON 文件描述了扩展的功能和配置，比如 action 中描述了在 Chrome 中需要显示的扩展的图标以及点击图标后弹出的页面。可以在这里 下载 图标到你的目录下，然后把名字改为 <code>manifest.json</code> 中配置的 <code>default_icon</code>。</p>
   <h4>创建 html 文件</h4>
   <p>上面的 action 中还配置了一个 <code>default_popup</code>，接下来我们创建一个名为 <code>hello.html</code> 的文件并加入如下代码。</p>
   <pre class="has"><code class="language-go">&lt;html&gt;
&nbsp;&nbsp;&lt;body&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;h1&gt;Hello&nbsp;Extensions&lt;/h1&gt;
&nbsp;&nbsp;&lt;/body&gt;
&lt;/html&gt;</code></pre>
   <h4>加载扩展</h4>
   <p>在开发模式下加载未打包的扩展。</p>
   <ul>
    <li><p>在 Chrome 浏览器地址栏中输入 <code>chrome://extensions</code>。也可以通过下图的两种方式打开。</p></li>
   </ul>
   <p><img src="https://i-blog.csdnimg.cn/blog_migrate/686aeefc419d19896d0d8d35b23970ec.png" alt="5e78ba9d1a6d4f424a635068afb3a33a.png"><img src="https://i-blog.csdnimg.cn/blog_migrate/1429210a3ef4facd510be4b9bb03140b.png" alt="4b388033e666fe557345777b8c3a9b12.png"></p>
   <ul>
    <li><p>打开开发者模式</p></li>
   </ul> <img src="https://i-blog.csdnimg.cn/blog_migrate/a381e84d3c12534af8b33f33755e8cf6.png" alt="9851d0aed6f8998ec94ebd07167149c9.png">
   <figcaption>
    image.png
   </figcaption>
   <ul>
    <li><p>点击加载已解压的扩展程序</p></li>
   </ul>
   <p><img src="https://i-blog.csdnimg.cn/blog_migrate/fd60264bc99218d2e8ea280486ad505a.png" alt="222759159553cfb17720cd22cec66e2d.png">选择我们的文件夹<img src="https://i-blog.csdnimg.cn/blog_migrate/8db2fbc692a14c4f0fcbdec4bcad009c.png" alt="93a4f576337f532c2818bb6a786fea4f.png">完成之后即可在扩展页面显示出来<img src="https://i-blog.csdnimg.cn/blog_migrate/ba760ff03aa4628305d46387cb42f703.png" alt="78cd3d39123914c732866e741e623565.png"></p>
   <h4>固定扩展</h4>
   <p><img src="https://i-blog.csdnimg.cn/blog_migrate/0c40a92d5dbcfb5ff08aea09138ec8d5.png" alt="655cfa9fb9e847b4f8121300b0b12ee8.png">点击固定，我们的扩展就可以在工具栏显示了。然后我们点击 icon，就可以弹出对应的页面了。<img src="https://i-blog.csdnimg.cn/blog_migrate/1da1464d7d89b5249eb9e01286ca03c3.png" alt="69a5e8698eae9fcaae39365fa8798641.png"></p>
   <h4>重新加载扩展</h4>
   <p>我们回到代码，修改扩展的名字为 “Hello Extensions of the world!”</p>
   <pre class="has"><code class="language-go">{
&nbsp;&nbsp;"manifest_version":&nbsp;3,
&nbsp;&nbsp;"name":&nbsp;"Hello&nbsp;Extensions&nbsp;of&nbsp;the&nbsp;world!",
&nbsp;&nbsp;...
}</code></pre>
   <p>保存之后，回到扩展页面，点击刷新，可以看到，名字已经改变。<img src="https://i-blog.csdnimg.cn/blog_migrate/7c7e47e445727f3493e7b830eac683e2.png" alt="9217124e6c6e983451a9dc9bf2d16432.png">那么每次改动都需要重新加载扩展吗？可以参考下表。</p>
   <table>
    <thead>
     <tr>
      <th><strong>扩展组件</strong></th>
      <th><strong>是否需要重新加载</strong></th>
     </tr>
    </thead>
    <tbody>
     <tr>
      <td>The manifest</td>
      <td>Yes</td>
     </tr>
     <tr>
      <td>Service worker</td>
      <td>Yes</td>
     </tr>
     <tr>
      <td>Content Scripts</td>
      <td>Yes (plus the host page)</td>
     </tr>
     <tr>
      <td>The popup</td>
      <td>No</td>
     </tr>
     <tr>
      <td>Options page</td>
      <td>No</td>
     </tr>
     <tr>
      <td>Other extension HTML pages</td>
      <td>No</td>
     </tr>
    </tbody>
   </table>
   <h4>查看控制台日志以及错误</h4>
   <h5>Console logs</h5>
   <p>在开发过程中，你可以通过浏览器控制台日志调试代码。在之前的代码中加入 <code>&lt;script src="popup.js"&gt;&lt;/script&gt;</code></p>
   <pre class="has"><code class="language-go">&lt;html&gt;
&nbsp;&nbsp;&lt;body&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;h1&gt;Hello&nbsp;Extensions&lt;/h1&gt;
&nbsp;&nbsp;&nbsp;&nbsp;&lt;script&nbsp;src="popup.js"&gt;&lt;/script&gt;
&nbsp;&nbsp;&lt;/body&gt;
&lt;/html&gt;</code></pre>
   <p>创建一个名为 <code>popup.js</code> 的文件并加入如下代码：</p>
   <pre class="has"><code class="language-go">console.log("This&nbsp;is&nbsp;a&nbsp;popup!")</code></pre>
   <p>接下来：</p>
   <ol>
    <li><p>刷新扩展</p></li>
    <li><p>打开popup</p></li>
    <li><p>右键popup</p></li>
    <li><p>选择 Inspect（检查）</p></li>
   </ol> <img src="https://i-blog.csdnimg.cn/blog_migrate/38246553a599e6d3049844767a4ccbb7.png" alt="988da774a9e0968cdf3a844522525bb0.png">
   <figcaption>
    image.png
   </figcaption>
   <ol>
    <li><p>在开发者工具中切换到Console tab。</p></li>
   </ol> <img src="https://i-blog.csdnimg.cn/blog_migrate/4585ee6bb3171af8b4b663c1152b89e6.png" alt="7297ac2088add83fdc145da65976be0a.png">
   <figcaption>
    image.png
   </figcaption>
   <h5>Error logs</h5>
   <p>下面我们修改 <code>popup.js</code>，去掉一个引号</p>
   <pre class="has"><code class="language-go">console.log("This&nbsp;is&nbsp;a&nbsp;popup!)&nbsp;//&nbsp;❌&nbsp;错误代码</code></pre>
   <p>刷新之后，然后再点击打开扩展，可以看到错误按钮出现。<img src="https://i-blog.csdnimg.cn/blog_migrate/f800afcb381da730bfbcce080753af12.png" alt="2d459dd597d0ec515042631ad8d91c1b.png">点击错误按钮，可以查看具体错误信息。<img src="https://i-blog.csdnimg.cn/blog_migrate/487d9532e1b423e215acb8133ae9a21c.png" alt="a926df117ee6e0029cc142f4ff071185.png"></p>
   <h4>扩展工程结构</h4>
   <p>扩展工程结构可以有多种方式，但是 <code>manifest.json</code> 文件必须位于根目录，下面是一种结构实例。<img src="https://i-blog.csdnimg.cn/blog_migrate/01145a8becda0366e4b1b546fff1b385.png" alt="900ba30c8215ef8c04f5634e55e2eb91.png"></p>
   <h3>Content Script</h3>
   <p>Content Script 运行在网页的上下文环境中，通过标准的 DOM，Content Script 可以读取浏览器访问页面的细节，也可以对其进行修改，还可以把这些信息传递给父扩展。</p>
   <h4>理解Content Script的能力</h4>
   <p>Content Script可以直接使用一部分 chrome API，比如：</p>
   <ul>
    <li><p>i18n</p></li>
    <li><p>storage</p></li>
    <li><p>runtime:</p>
     <ul>
      <li><p>connect</p></li>
      <li><p>getManifest</p></li>
      <li><p>getURL</p></li>
      <li><p>id</p></li>
      <li><p>onConnect</p></li>
      <li><p>onMessage</p></li>
      <li><p>sendMessage</p></li>
     </ul></li>
   </ul>
   <p>使用其他的可以通过发送消息的方式实现。Content Script可以在将扩展里的文件声明为 Web Accessible Resources 后访问这些文件。 Content Script 运行在一个独立的环境里，它可以对其所在的 JavaScript 环境进行修改，而不与页面或其他扩展的 Content Script 发生冲突。</p>
   <pre class="has"><code class="language-go">&lt;html&gt;
&nbsp;&nbsp;&lt;button&nbsp;id="mybutton"&gt;click&nbsp;me&lt;/button&gt;
&nbsp;&nbsp;&lt;script&gt;
&nbsp;&nbsp;&nbsp;&nbsp;var&nbsp;greeting&nbsp;=&nbsp;"hello,&nbsp;";
&nbsp;&nbsp;&nbsp;&nbsp;var&nbsp;button&nbsp;=&nbsp;document.getElementById("mybutton");
&nbsp;&nbsp;&nbsp;&nbsp;button.person_name&nbsp;=&nbsp;"Bob";
&nbsp;&nbsp;&nbsp;&nbsp;button.addEventListener(
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"click",
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;()&nbsp;=&gt;&nbsp;alert(greeting&nbsp;+&nbsp;button.person_name&nbsp;+&nbsp;"."),
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;false
&nbsp;&nbsp;&nbsp;&nbsp;);
&nbsp;&nbsp;&lt;/script&gt;
&lt;/html&gt;</code></pre>
   <p><img src="https://i-blog.csdnimg.cn/blog_migrate/9cd503792a89be256e8b861aedca97e2.png" alt="32520f6ac1707a8a879dce8b442df4c7.png"><img src="https://i-blog.csdnimg.cn/blog_migrate/ee089c19e057a6466a600f25c300954d.png" alt="577f6c65a52a55b26c03dd24142bd701.png"></p>
   <h4>Content Script 注入</h4>
   <p>Content Script 注入有三种方式，分别是静态声明、动态声明、编程注入。 静态声明比较简单，只需要在 manifest.json 文件的 <code>content_scripts</code>进行配置即可。注意需要指定 <code>matches</code> 字段以指明需要再哪些页面运行 Content Script 脚本。</p>
   <pre class="has"><code class="language-go">"content_scripts":&nbsp;[
&nbsp;&nbsp;&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"matches":&nbsp;["https://*.google.com/*"],
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"css":&nbsp;["styles.css"],
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"js":&nbsp;["content-script.js"]
&nbsp;&nbsp;&nbsp;}
&nbsp;],</code></pre>
   <p>动态声明主要使用场景是 <code>matches</code> 不明确时使用。编程注入是需要响应事件或一些特定场合使用，我们最终的例子使用的是静态声明。</p>
   <h3>Service Worker</h3>
   <p>Chrome 扩展是基于事件的程序，用于修改或增强 Chrome 浏览器的浏览体验。事件是由浏览器触发的，比如导航到一个新页面，删除一个书签或关闭一个标签（tab）。扩展通过 Service Worker 中的脚本监听这些事件。然后执行特定的指令。 Service Worker 需要时被加载，休眠时被卸载。比如：</p>
   <ul>
    <li><p>扩展第一次安装或更新到一个新的版本</p></li>
    <li><p>一个扩展事件被触发</p></li>
    <li><p>Content Script 或其他扩展发送了消息</p></li>
   </ul>
   <p>Service Worker 被加载后，只要有事件 Service Worker 就会保持在运行状态。一旦空闲了30秒，浏览器就会停止它。 Service Worker 保持休眠状态，直到有它监听的事件发生，这时它会执行相应的事件监听器，然后空闲、卸载。</p>
   <p>Service Worker 的日志在这里查看<img src="https://i-blog.csdnimg.cn/blog_migrate/add15ad3116778aa421a75436aa49570.png" alt="71a1462d1476434f76dba89cdf4a3460.png"></p>
   <h4>注册Service Worker</h4>
   <pre class="has"><code class="language-go">"background":&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;"service_worker":&nbsp;"background.js"
&nbsp;&nbsp;},</code></pre>
   <h4>初始化扩展</h4>
   <pre class="has"><code class="language-go">chrome.runtime.onInstalled.addListener(function&nbsp;(details)&nbsp;{
&nbsp;&nbsp;console.log("onInstalled&nbsp;event&nbsp;has&nbsp;been&nbsp;triggered&nbsp;with&nbsp;details:&nbsp;",&nbsp;details);
&nbsp;&nbsp;//&nbsp;检查安装、更新或卸载的原因
&nbsp;&nbsp;if&nbsp;(details.reason&nbsp;==&nbsp;"install")&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;//&nbsp;在安装扩展时执行的代码
&nbsp;&nbsp;}&nbsp;else&nbsp;if&nbsp;(details.reason&nbsp;==&nbsp;"update")&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;//&nbsp;在更新扩展时执行的代码
&nbsp;&nbsp;}&nbsp;else&nbsp;if&nbsp;(details.reason&nbsp;==&nbsp;"uninstall")&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;//&nbsp;在卸载扩展时执行的代码
&nbsp;&nbsp;}
});</code></pre> <img src="https://i-blog.csdnimg.cn/blog_migrate/a677b3209ef885d489d1bce04e3e9651.png" alt="2590c1d1fdb74b75b843cdc245013caa.png">
   <figcaption>
    image.png
   </figcaption>
   <h4>设置监听</h4>
   <pre class="has"><code class="language-go">/**
&nbsp;*&nbsp;注意需要声明权限
&nbsp;*/
chrome.bookmarks.onCreated.addListener(function&nbsp;(id,&nbsp;bookmark)&nbsp;{
&nbsp;&nbsp;console.log(
&nbsp;&nbsp;&nbsp;&nbsp;"Bookmark&nbsp;created&nbsp;with&nbsp;title:&nbsp;"&nbsp;+
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bookmark.title&nbsp;+
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"&nbsp;and&nbsp;url:&nbsp;"&nbsp;+
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bookmark.url
&nbsp;&nbsp;);
});</code></pre> <img src="https://i-blog.csdnimg.cn/blog_migrate/1c66106144d457768e4bd88f9dcfb05e.png" alt="ef2bedda4343b8d85ae656c14933f2ef.png">
   <figcaption>
    image.png
   </figcaption>
   <h4>过滤事件</h4>
   <pre class="has"><code class="language-go">/**
&nbsp;*&nbsp;注意需要声明&nbsp;webNavigation&nbsp;权限
&nbsp;*/
chrome.webNavigation.onCompleted.addListener(()&nbsp;=&gt;&nbsp;{
&nbsp;&nbsp;console.info("The&nbsp;user&nbsp;has&nbsp;loaded&nbsp;my&nbsp;favorite&nbsp;website!");
});</code></pre>
   <p><img src="https://i-blog.csdnimg.cn/blog_migrate/b1690459da3bf4f9231424749cb0e6c4.png" alt="f4171343208966d1b4fcfd5b0c8fd01f.png">未过滤时所有的事件都会打印。不同的事件对应不同的功能，需要选择合适的事件来进行监听。</p>
   <h3>Service Worker 与 Content Script 通信</h3>
   <p>点击扩展 tab 的事件我们可以通过 <code>chrome.tabs.sendMessage</code>，并在Content Script中注册 <code>chrome.runtime.onMessage.addListener</code> 接收。</p>
   <h3>颜色提取扩展开发</h3>
   <p>基于上面的知识点，我们要实现如下功能： 1、导航栏点击扩展按钮弹出一个显示详情的面板 2、在当前页面鼠标点击页面的元素，在弹出的面板上显示对应的背景色 3、点击复制按钮可以复制当前元素背景色</p>
   <p>这里我们只列出核心代码如下：</p>
   <h4>manifet 配置</h4>
   <pre class="has"><code class="language-go">{
&nbsp;&nbsp;"manifest_version":&nbsp;3,
&nbsp;&nbsp;"name":&nbsp;"EasyColor",
&nbsp;&nbsp;"description":&nbsp;"Chrome&nbsp;extension&nbsp;for&nbsp;obtaining&nbsp;color&nbsp;in&nbsp;an&nbsp;easy&nbsp;way",
&nbsp;&nbsp;"version":&nbsp;"0.0.1",
&nbsp;&nbsp;"action":&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;"default_icon":&nbsp;"images/icon-48.png"
&nbsp;&nbsp;},
&nbsp;&nbsp;"icons":&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;"16":&nbsp;"images/icon-16.png",
&nbsp;&nbsp;&nbsp;&nbsp;"32":&nbsp;"images/icon-32.png",
&nbsp;&nbsp;&nbsp;&nbsp;"48":&nbsp;"images/icon-48.png",
&nbsp;&nbsp;&nbsp;&nbsp;"128":&nbsp;"images/icon-128.png"
&nbsp;&nbsp;},
&nbsp;&nbsp;"background":&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;"service_worker":&nbsp;"background.js"
&nbsp;&nbsp;},
&nbsp;&nbsp;"content_scripts":&nbsp;[
&nbsp;&nbsp;&nbsp;&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"matches":&nbsp;["http://*/*",&nbsp;"https://*/*"],
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"css":&nbsp;["css/styles.css"],
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"js":&nbsp;["scripts/content.js"],
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"run_at":&nbsp;"document_start"
&nbsp;&nbsp;&nbsp;&nbsp;}
&nbsp;&nbsp;],
&nbsp;&nbsp;"web_accessible_resources":&nbsp;[
&nbsp;&nbsp;&nbsp;&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"resources":&nbsp;["/pages/panel.html"],
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"matches":&nbsp;["http://*/*",&nbsp;"https://*/*"]
&nbsp;&nbsp;&nbsp;&nbsp;}
&nbsp;&nbsp;]
}</code></pre>
   <h4>导航栏点击扩展按钮弹出一个显示面板</h4>
   <p>首先在 <code>service_worker</code> 中，我们定义一个 background.js，用于监听扩展点击事件，并发送给 content_scripts。</p>
   <pre class="has"><code class="language-go">chrome.action.onClicked.addListener(function&nbsp;(tab)&nbsp;{
&nbsp;&nbsp;//open&nbsp;pages
&nbsp;&nbsp;chrome.tabs.sendMessage(tab.id,&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;action:&nbsp;"EVENT_PANEL_OPEN",
&nbsp;&nbsp;});
});</code></pre>
   <p>在 content_scripts 中监听 <code>EVENT_PANEL_OPEN</code> 事件并在当前页面增加一个iframe用来显示面板。</p>
   <pre class="has"><code class="language-go">chrome.runtime.onMessage.addListener((req,&nbsp;sender,&nbsp;sendResp)&nbsp;=&gt;&nbsp;{
&nbsp;&nbsp;const&nbsp;data&nbsp;=&nbsp;req;
&nbsp;&nbsp;if&nbsp;(data.action&nbsp;===&nbsp;"EVENT_PANEL_OPEN")&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;let&nbsp;easyPanel&nbsp;=&nbsp;document.getElementById(easyPanelId);
&nbsp;&nbsp;&nbsp;&nbsp;if&nbsp;(easyPanel&nbsp;==&nbsp;null)&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel&nbsp;=&nbsp;document.createElement("iframe");
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel.id&nbsp;=&nbsp;easyPanelId;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel.src&nbsp;=&nbsp;chrome.runtime.getURL("../pages/panel.html");
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel.style.width&nbsp;=&nbsp;"100%";
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel.style.height&nbsp;=&nbsp;"100%";
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel.style.borderRadius&nbsp;=&nbsp;"20px";
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;easyPanel.style.border&nbsp;=&nbsp;"none";

      const container = document.createElement("div");
      container.id = easyContainerId;
      container.style.width = "200px";
      container.style.height = "250px";
      container.style.position = "fixed";
      container.style.top = "10px";
      container.style.right = "10px";
      container.style.zIndex = "10000";
      container.style.boxShadow = "3px 2px 22px 1px rgba(0, 0, 0, 0.24)";
      container.style.borderRadius = "20px";
      container.appendChild(easyPanel);

      document.body.appendChild(container);
    }

    document.addEventListener("mousemove", handleMouseMove);

    document.addEventListener(
      "click",
      function (e) {
        e.stopPropagation();
        e.preventDefault();
        return false;
      },
      true
    );
  }
});</code></pre>

   <h4>点击显示对应颜色</h4>
   <p>在 content_scripts 中加入点击事件，获取当前元素背景颜色，然后传递给 iframe 进行显示。</p>
   <pre class="has"><code class="language-go">document.addEventListener("mousedown",&nbsp;function&nbsp;(e)&nbsp;{
&nbsp;&nbsp;let&nbsp;easyPanel&nbsp;=&nbsp;document.getElementById(easyPanelId);
&nbsp;&nbsp;if&nbsp;(easyPanel&nbsp;==&nbsp;null)&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;return;
&nbsp;&nbsp;}
&nbsp;&nbsp;const&nbsp;cpStyle&nbsp;=&nbsp;window.getComputedStyle(e.target);
&nbsp;&nbsp;easyPanel.contentWindow.postMessage(
&nbsp;&nbsp;&nbsp;&nbsp;{
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type:&nbsp;"computedStyle",
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;data:&nbsp;JSON.stringify({
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;color:&nbsp;cpStyle.color,
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;backgroundColor:&nbsp;cpStyle.backgroundColor,
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}),
&nbsp;&nbsp;&nbsp;&nbsp;},
&nbsp;&nbsp;&nbsp;&nbsp;"*"
&nbsp;&nbsp;);
});</code></pre>
   <p>以上就是我们实现的元素颜色选择插件的核心代码，更多细节可以参考：https://github.com/yangpeng7/ChromeExtensionBestPractice</p>
   <h3>参考资源</h3>
   <p>https://developer.chrome.com/docs/extensions/mv3/</p>
   <p style="text-align:center;">-&nbsp;END&nbsp;-</p>
   <h3>关于奇舞团</h3>
   <p style="text-align:left;">奇舞团是 360 集团最大的大前端团队，代表集团参与 W3C 和 ECMA 会员（TC39）工作。奇舞团非常重视人才培养，有工程师、讲师、翻译官、业务接口人、团队 Leader 等多种发展方向供员工选择，并辅以提供相应的技术力、专业力、通用力、领导力等培训课程。奇舞团以开放和求贤的心态欢迎各种优秀人才关注和加入奇舞团。<br></p>
   <p style="text-align:left;"><img src="https://i-blog.csdnimg.cn/blog_migrate/143be8c0aa6989517a19d0a603c1c7c3.png" alt="b1c4c1c57b487ac428388274b933260e.png"></p>
  </div>
 </div>
</div>
