<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="markdown_views prism-atom-one-dark">
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
  </svg>
  <h2><a id="_0"></a>一、速查表格</h2>
  <table>
   <thead>
    <tr>
     <th align="left">命令</th>
     <th align="left">作用</th>
    </tr>
   </thead>
   <tbody>
    <tr>
     <td align="left">git add &lt;文件名&gt;</td>
     <td align="left">将文件添加到暂存区，可以使用 <code>.</code> 将所有修改的文件添加</td>
    </tr>
    <tr>
     <td align="left">git commit -m “提交信息”</td>
     <td align="left">将暂存区的文件提交到本地仓库</td>
    </tr>
    <tr>
     <td align="left">git branch -d &lt;分支名&gt;</td>
     <td align="left">删除本地分支，使用 <code>-D</code> 强制删除</td>
    </tr>
    <tr>
     <td align="left">git switch -c &lt;新分支名</td>
     <td align="left">基于当前分支创建新分支并切换</td>
    </tr>
    <tr>
     <td align="left">git stash</td>
     <td align="left">临时保存当前未提交的更改</td>
    </tr>
    <tr>
     <td align="left">git stash pop</td>
     <td align="left">将暂存的更改恢复到工作目录</td>
    </tr>
    <tr>
     <td align="left">git fetch</td>
     <td align="left">从远程仓库获取最新的提交、分支和标签信息</td>
    </tr>
    <tr>
     <td align="left">git merge dev</td>
     <td align="left">将dev分支合并到当前分支</td>
    </tr>
    <tr>
     <td align="left">git rm -r --cached &lt;文件夹路径&gt;</td>
     <td align="left">将指定文件夹从 Git 跟踪中删除，但保留本地文件夹</td>
    </tr>
    <tr>
     <td align="left">git push origin &lt;分支名&gt;</td>
     <td align="left">推送代码到远程仓库</td>
    </tr>
    <tr>
     <td align="left">git push origin --delete &lt;分支名&gt;</td>
     <td align="left">删除远程仓库指定分支</td>
    </tr>
   </tbody>
  </table>
  <h2><a id="_21"></a>二、常见操作</h2>
  <p>以下是一些常见的 Git 操作及其用途：</p>
  <h3><a id="1__Git__25"></a>1. 配置 Git 用户信息</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> config <span class="token parameter variable">--global</span> user.name <span class="token string">"你的名字"</span>
<span class="token function">git</span> config <span class="token parameter variable">--global</span> user.email <span class="token string">"你的邮箱"</span>
</code></pre>
  <h3><a id="2__31"></a>2. 初始化仓库</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> init
</code></pre>
  <p>在当前目录下初始化一个新的 Git 仓库。</p>
  <h3><a id="3__37"></a>3. 克隆仓库</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> clone <span class="token operator">&lt;</span>仓库地址<span class="token operator">&gt;</span>
</code></pre>
  <p>从远程仓库克隆一个项目到本地。</p>
  <h3><a id="4__43"></a>4. 查看当前状态</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> status
</code></pre>
  <p>显示工作目录和暂存区的状态，查看哪些文件被修改或准备提交。</p>
  <h3><a id="5__49"></a>5. 添加文件到暂存区</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> <span class="token function">add</span> <span class="token operator">&lt;</span>文件名<span class="token operator">&gt;</span>
</code></pre>
  <p>将文件添加到暂存区，可以使用 <code>.</code> 将所有修改的文件添加。</p>
  <h3><a id="6__55"></a>6. 提交更改</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> commit <span class="token parameter variable">-m</span> <span class="token string">"提交信息"</span>
</code></pre>
  <p>将暂存区的文件提交到本地仓库。</p>
  <h3><a id="7__61"></a>7. 查看提交记录</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> log
</code></pre>
  <p>显示提交历史，<code>git log --oneline</code> 简化输出。</p>
  <h3><a id="8__67"></a>8. 创建分支</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> branch <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span>
</code></pre>
  <p>创建一个新的分支。</p>
  <h3><a id="9__73"></a>9. 切换分支</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> checkout <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span>
</code></pre>
  <p>切换到指定分支。</p>
  <h3><a id="10__79"></a>10. 合并分支</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> merge <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span>
</code></pre>
  <p>将指定分支的更改合并到当前分支。</p>
  <h3><a id="11__85"></a>11. 删除分支</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> branch <span class="token parameter variable">-d</span> <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span>
</code></pre>
  <p>删除本地分支，使用 <code>-D</code> 强制删除。</p>
  <h3><a id="12__91"></a>12. 推送代码到远程仓库</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> push origin <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span>
</code></pre>
  <p>将本地分支的更改推送到远程仓库。</p>
  <h3><a id="13__97"></a>13. 拉取远程仓库的更改</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> pull
</code></pre>
  <p>获取并合并远程仓库的更改。</p>
  <h3><a id="14__103"></a>14. 克隆特定分支</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> clone <span class="token parameter variable">-b</span> <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span> <span class="token operator">&lt;</span>仓库地址<span class="token operator">&gt;</span>
</code></pre>
  <p>克隆远程仓库中特定的分支。</p>
  <h3><a id="15__109"></a>15. 创建并切换到新分支</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> checkout <span class="token parameter variable">-b</span> <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span>
</code></pre>
  <p>创建并直接切换到新的分支。</p>
  <h3><a id="16_stash_115"></a>16. 暂存更改（stash）</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> stash
</code></pre>
  <p>临时保存当前未提交的更改。</p>
  <h3><a id="17__121"></a>17. 恢复暂存的更改</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> stash pop
</code></pre>
  <p>将暂存的更改恢复到工作目录。</p>
  <h3><a id="18__127"></a>18. 重置本地修改</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> reset <span class="token parameter variable">--hard</span>
</code></pre>
  <p>将所有本地修改的文件恢复到上次提交时的状态。</p>
  <h3><a id="19__133"></a>19. 查看远程仓库</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> remote <span class="token parameter variable">-v</span>
</code></pre>
  <p>显示当前配置的远程仓库地址。</p>
  <h3><a id="20__139"></a>20. 添加远程仓库</h3>
  <pre><code class="prism language-bash"><span class="token function">git</span> remote <span class="token function">add</span> origin <span class="token operator">&lt;</span>仓库地址<span class="token operator">&gt;</span>
</code></pre>
  <p>将远程仓库地址添加到本地仓库。</p>
  <h2><a id="_145"></a>三、进阶用法</h2>
  <h3><a id="1__146"></a>1. 合并最近两次提交</h3>
  <h4><a id="_rebase_147"></a>方式一 <code>rebase</code></h4>
  <p><strong>Step1 启动交互式 <code>rebase</code></strong><br> 运行以下命令进入交互式 <code>rebase</code> 界面：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> rebase <span class="token parameter variable">-i</span> HEAD~2

<span class="token function">git</span> rebase <span class="token parameter variable">-i</span> 要保留的前一个CommitID

<span class="token function">git</span> rebase <span class="token parameter variable">-i</span> 要保留的CommitID^
</code></pre>

  <p><code>HEAD~2</code> 表示要操作最近的两次提交。</p>
  <p><strong>Step2 修改 <code>pick</code> 为 <code>squash</code>（或 <code>fixup</code>）</strong><br> 在文本编辑器中，会显示类似如下的两行：</p>
  <pre><code>pick &lt;commit-hash-1&gt; First commit message
pick &lt;commit-hash-2&gt; Second commit message
</code></pre>
  <p>fixup 或 squash（两者都用于合并提交，但 squash 会保留合并提交的日志消息，而 fixup 会丢弃它，只保留第一个提交的日志消息）</p>
  <p>将第二行的 <code>pick</code> 改为 <code>squash</code>（或简写 <code>s</code>），如下所示：</p>
  <pre><code>pick &lt;commit-hash-1&gt; First commit message
squash &lt;commit-hash-2&gt; Second commit message
</code></pre>
  <p>这会将第二次提交合并到第一次提交。</p>
  <p><strong>Step3 编辑提交信息</strong><br> 保存并退出编辑器后，Git 会要求你编辑合并后的提交信息。你可以保留或修改提交信息，然后保存并退出。</p>
  <p><strong>Step4 推送更改</strong><br> 如果你已经将这些提交推送到远程仓库，并且想要同步这些更改，需要使用 <code>--force</code>（或简写 <code>-f</code>）推送：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> push origin <span class="token operator">&lt;</span>分支名<span class="token operator">&gt;</span> <span class="token parameter variable">--force</span>
</code></pre>
  <h4><a id="_reset_190"></a>方式二 <code>reset</code></h4>
  <p>提交历史如下：</p>
  <pre><code class="prism language-bash">提交5 CommitHash5
      提交5信息

提交4 CommitHash4
提交4信息

提交3 CommitHash3
提交3信息

提交2 CommitHash2
提交2信息

提交1 CommitHash1
提交1信息
</code></pre>

  <p>需求是将提交3、提交4、提交5合并为一个提交<br> <strong>Step1 执行回退</strong><br> 先将版本回退到提交且在回退时保留工作区的变动</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> reset CommitHash2
</code></pre>
  <p><strong>Step2 提交代码</strong></p>
  <pre><code class="prism language-cpp">git add 需要提交的文件
git commit <span class="token string">"新的提交信息"</span>
</code></pre>
  <h2><a id="_220"></a>四、指令详解</h2>
  <h3><a id="1_git_reset_221"></a>1. git reset</h3>
  <h4><a id="11__223"></a>1.1 基本作用</h4>
  <p><code>git reset</code> 是 Git 中非常强大的命令，用于将当前分支的提交历史、暂存区（staging area）或工作目录（working directory）重置为指定的状态。它主要有以下几种作用：</p>
  <p><strong>①重置提交历史（改变分支指向）</strong><br> <code>git reset</code> 可以改变当前分支的 HEAD 指向到某个特定的提交。这意味着它可以取消某些提交，将分支指向之前的某个提交。</p>
  <p><strong>②修改暂存区</strong><br> 通过 <code>git reset</code>，你可以将某些文件从暂存区（staging area）中移除，但这些文件的内容不会被修改。这在你已经 <code>git add</code> 了文件，但想撤销这个操作时非常有用。</p>
  <p><strong>③修改工作目录</strong><br> <code>git reset</code> 也可以更改工作目录中的文件状态，将文件恢复到之前的状态（即删除未提交的修改）。</p>
  <h4><a id="12__235"></a>1.2 操作模式</h4>
  <p><code>git reset</code> 有三种常用模式，主要区别在于它们对 <strong>提交历史</strong>、<strong>暂存区</strong> 和 <strong>工作目录</strong> 的影响：</p>
  <ol>
   <li><p><strong><code>git reset --soft &lt;commit&gt;</code></strong></p>
    <ul>
     <li><strong>提交历史</strong>: 重置到指定提交，保留所有修改（提交记录指向更早的一个提交）。</li>
     <li><strong>暂存区</strong>: 保留所有文件的变化，文件仍保持在暂存区中。</li>
     <li><strong>工作目录</strong>: 工作目录不变，所有文件修改依然保留。</li>
    </ul> <p>用途：撤销最近的提交，但保留代码和暂存的文件。</p></li>
  </ol>
  <pre><code class="prism language-bash">   <span class="token function">git</span> reset <span class="token parameter variable">--soft</span> HEAD~1  <span class="token comment"># 撤销最近的一次提交</span>
</code></pre>
  <ol start="2">
   <li><p><strong><code>git reset --mixed &lt;commit&gt;</code></strong>（默认模式）</p>
    <ul>
     <li><strong>提交历史</strong>: 重置到指定提交。</li>
     <li><strong>暂存区</strong>: 暂存区会重置为指定提交的状态，取消 <code>git add</code> 的文件。</li>
     <li><strong>工作目录</strong>: 保留工作目录中的修改，代码仍然存在。</li>
    </ul> <p>用途：撤销最近的提交，并将文件从暂存区中移除，但保留修改。</p></li>
  </ol>
  <pre><code class="prism language-bash"><span class="token function">git</span> reset <span class="token parameter variable">--mixed</span> HEAD~1
</code></pre>
  <ol start="3">
   <li><p><strong><code>git reset --hard &lt;commit&gt;</code></strong></p>
    <ul>
     <li><strong>提交历史</strong>: 重置到指定提交。</li>
     <li><strong>暂存区</strong>: 暂存区会重置为指定提交的状态。</li>
     <li><strong>工作目录</strong>: 工作目录也会重置到指定提交的状态（所有未提交的修改都会丢失）。</li>
    </ul> <p>用途：彻底重置分支，丢弃所有本地的修改和提交。</p></li>
  </ol>
  <pre><code class="prism language-bash">   <span class="token function">git</span> reset <span class="token parameter variable">--hard</span> HEAD~1
</code></pre>
  <h4><a id="13__273"></a>1.3 用法场景</h4>
  <ol>
   <li><p><strong>撤销最近的提交</strong>：如果你刚提交了某些更改，但意识到有问题，可以使用 <code>git reset --soft</code> 或 <code>--mixed</code> 来撤销提交，保留代码。</p></li>
   <li><p><strong>修改暂存区</strong>：如果你用 <code>git add</code> 暂存了一些文件，但后来不想提交它们，可以使用 <code>git reset &lt;file&gt;</code> 将文件从暂存区移出。</p></li>
   <li><p><strong>完全重置到某个提交</strong>：如果你想丢弃所有未提交的更改并回到之前的某个状态，可以使用 <code>git reset --hard</code>。</p></li>
  </ol>
  <h4><a id="14__283"></a>1.4 注意事项</h4>
  <ul>
   <li><strong><code>--hard</code> 模式会丢失所有未提交的更改</strong>，所以在使用前一定要谨慎，确保这些改动不是你想保留的。</li>
   <li><code>git reset</code> 会修改提交历史，特别是当你已经将提交推送到远程仓库时，使用 <code>reset</code> 可能会造成团队协作问题。</li>
  </ul>
  <h3><a id="2_git_rebase_287"></a>2. git rebase</h3>
  <p><code>git rebase</code> 是 Git 中用于重写提交历史的强大工具，主要用来将一个分支的更改“移动”到另一个分支的基础之上。它通常用于保持线性的提交历史，避免频繁出现分支合并时的 “merge commit”。</p>
  <h4><a id="21__290"></a>2.1 基本原理</h4>
  <p><code>git rebase</code> 的基本思想是：</p>
  <ul>
   <li><strong>将一系列提交从一个基底上“复制”下来</strong>。</li>
   <li><strong>再将这些提交“应用”到另一个基底上</strong>。</li>
  </ul>
  <p>假设有两个分支，<code>feature</code> 和 <code>main</code>，你想将 <code>feature</code> 的工作“重新定位”到 <code>main</code> 的最新提交之上，这时就可以使用 <code>git rebase</code>。</p>
  <h4><a id="22__297"></a>2.2 应用场景</h4>
  <ul>
   <li>将分支变基到另一分支上</li>
   <li>交互式 rebase 用于修改历史</li>
   <li>处理冲突与 rebase 的作用</li>
  </ul>
  <h4><a id="23_rebase_303"></a>2.3 基本的rebase</h4>
  <p><strong>将分支变基到另一分支上</strong><br> 最常见的用法是将一个分支的更改移动到另一个分支的最新状态之上。例如，你正在 <code>feature</code> 分支开发，而 <code>main</code> 分支有了新的更新，你希望将 <code>feature</code> 分支的工作基于最新的 <code>main</code> 分支。</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> checkout feature       <span class="token comment"># 切换到 feature 分支</span>
<span class="token function">git</span> rebase main            <span class="token comment"># 将 feature 分支变基到 main 分支</span>
</code></pre>
  <p><strong>发生的过程：</strong></p>
  <ul>
   <li>Git 会“摘下” <code>feature</code> 分支的所有提交，将它们暂存起来。</li>
   <li>然后，Git 会将 <code>feature</code> 分支“移动”到 <code>main</code> 分支的最新提交。</li>
   <li>最后，Git 会把 <code>feature</code> 分支之前的那些提交一个一个地重新应用在新的基底之上。</li>
  </ul>
  <p>这种操作相当于“更新”了 <code>feature</code> 分支，基于 <code>main</code> 最新的代码，且不产生额外的合并提交。</p>
  <h4><a id="24_rebase_320"></a>2.4 交互式rebase</h4>
  <p><code>git rebase -i</code>（交互式 rebase）允许你对一系列提交进行更复杂的操作，如修改、删除、合并提交等。这对整理提交历史非常有用。</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> rebase <span class="token parameter variable">-i</span> <span class="token operator">&lt;</span>commit<span class="token operator">&gt;</span>
</code></pre>
  <p><strong>交互式 Rebase 操作的常见步骤：</strong></p>
  <ol>
   <li><p><strong>选择要操作的提交</strong>：<code>git rebase -i</code> 会打开一个编辑器，显示一系列提交。每行代表一个提交，并带有一个操作命令。</p> <p>示例：</p> <pre><code>pick 3a1c5d7 Fix bug A
pick 4b6f7d8 Add feature X
pick 9b4f3e2 Improve documentation
</code></pre></li>
   <li><p><strong>修改提交历史</strong>：你可以将 <code>pick</code> 替换为其他命令来改变提交历史。例如：</p>
    <ul>
     <li><code>pick</code>：保持该提交不变。</li>
     <li><code>reword</code>：修改提交信息。</li>
     <li><code>edit</code>：暂停并允许你修改该提交的内容。</li>
     <li><code>squash</code>：将该提交与前一个提交合并。</li>
     <li><code>drop</code>：删除该提交。</li>
    </ul> <p>示例（将两次提交合并为一个提交）：</p> <pre><code>pick 3a1c5d7 Fix bug A
squash 4b6f7d8 Add feature X
pick 9b4f3e2 Improve documentation
</code></pre></li>
   <li><p><strong>保存退出</strong>：保存并退出编辑器后，Git 会按照指定的操作重新组织这些提交。</p></li>
  </ol>
  <p>交互式 rebase 常用于清理历史，比如将多次小提交合并成一次，或者修正提交信息，确保历史整洁。</p>
  <hr>
  <h4><a id="24__358"></a>2.4 冲突处理</h4>
  <p><strong>冲突发生</strong><br> 当 rebase 的过程中，Git 可能会遇到合并冲突。此时 Git 会暂停并提示你解决冲突。</p>
  <p>例如：</p>
  <pre><code class="prism language-bash">Auto-merging file.txt
CONFLICT <span class="token punctuation">(</span>content<span class="token punctuation">)</span>: Merge conflict <span class="token keyword">in</span> file.txt
</code></pre>
  <p><strong>解决冲突</strong><br> 当冲突发生时，你需要手动编辑冲突文件并解决冲突。然后使用以下命令继续 rebase：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> <span class="token function">add</span> <span class="token operator">&lt;</span>conflicted-file<span class="token operator">&gt;</span>  <span class="token comment"># 标记冲突文件已解决</span>
<span class="token function">git</span> rebase <span class="token parameter variable">--continue</span>      <span class="token comment"># 继续 rebase</span>
</code></pre>
  <p>如果你希望放弃此次 rebase，可以使用：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> rebase <span class="token parameter variable">--abort</span>         <span class="token comment"># 取消 rebase 并回到操作前的状态</span>
</code></pre>
  <p><strong>跳过冲突</strong><br> 如果你不想解决冲突，并且想跳过有冲突的提交，可以使用：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> rebase <span class="token parameter variable">--skip</span>
</code></pre>
  <hr>
  <h4><a id="25__392"></a>2.5 区别</h4>
  <ul>
   <li><code>git rebase</code> 通过将分支的提交“移动”到另一分支之上来更新分支历史，不会产生额外的合并提交。它保持提交历史的“线性”。</li>
   <li><code>git merge</code> 将两个分支的历史合并在一起，并生成一个新的合并提交（merge commit），记录合并操作。</li>
  </ul>
  <p><strong>选择场景：</strong></p>
  <ul>
   <li>如果你想保持历史清晰、线性，可以使用 <code>rebase</code>。</li>
   <li>如果你不介意生成合并提交，且希望保留所有历史信息（包括分支的分叉），可以使用 <code>merge</code>。</li>
  </ul>
  <h4><a id="26__405"></a>2.6 总结</h4>
  <ul>
   <li><strong><code>git rebase</code> 的主要用途</strong>：重写历史，保持线性提交记录，避免不必要的合并提交。</li>
   <li><strong>交互式 rebase</strong>：提供了强大的工具来编辑提交历史，常用于清理和整理提交。</li>
   <li><strong>冲突处理</strong>：rebase 过程中可能会遇到冲突，手动解决冲突并继续 rebase。</li>
   <li><strong>与 <code>git merge</code> 的区别</strong>：<code>rebase</code> 保持历史线性，而 <code>merge</code> 保留所有历史信息并创建合并提交。</li>
  </ul>
  <h3><a id="3_git_revert_413"></a>3. git revert</h3>
  <p><code>git revert</code> 是 Git 中用于撤销已提交的更改的命令。与 <code>git reset</code> 不同，<code>git revert</code> 并不会直接修改提交历史，而是创建一个新的提交，专门用于撤销之前的更改。以下是 <code>git revert</code> 的一些常见用法：</p>
  <h4><a id="31__416"></a>3.1 撤销单个提交</h4>
  <p>要撤销某个特定提交，可以使用以下命令：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> revert <span class="token operator">&lt;</span>commit-hash<span class="token operator">&gt;</span>
</code></pre>
  <p>其中，<code>&lt;commit-hash&gt;</code> 是要撤销的提交的哈希值（通过 <code>git log</code> 可以查看提交历史和哈希值）。</p>
  <p>这将创建一个新的提交，内容与指定提交的更改相反，从而撤销该提交。</p>
  <h4><a id="32__425"></a>3.2 撤销多个提交</h4>
  <p>你可以通过指定一段提交历史来撤销多个提交。格式如下：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> revert <span class="token operator">&lt;</span>start-commit<span class="token operator">&gt;</span>^<span class="token punctuation">..</span><span class="token operator">&lt;</span>end-commit<span class="token operator">&gt;</span>
</code></pre>
  <p>这会逐个撤销范围内的每个提交，并为每次撤销生成一个新的提交。</p>
  <h4><a id="33__432"></a>3.3 撤销最近的一次提交</h4>
  <p>如果你想撤销最近的一次提交，可以简便地使用 <code>HEAD</code> 指针：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> revert HEAD
</code></pre>
  <p>这会撤销你最新的提交。</p>
  <h4><a id="34__git_revert__439"></a>3.4 使用 <code>git revert</code> 避免冲突</h4>
  <p>如果你想在撤销提交时跳过交互式的冲突解决（在一些情况下你可能并不关心），可以使用 <code>--no-edit</code> 或者 <code>--no-commit</code> 选项：</p>
  <ul>
   <li><code>--no-edit</code>：不进入编辑模式，直接完成撤销。</li>
   <li><code>--no-commit</code>：仅执行撤销操作，但不会自动提交。</li>
  </ul>
  <p>例如：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> revert HEAD --no-edit
</code></pre>
  <h4><a id="35__449"></a>3.5 撤销已推送的提交</h4>
  <p>如果你已经将提交推送到远程分支，并且需要撤销它，可以使用 <code>git revert</code> 来避免直接修改历史，特别是在公共仓库时，这是推荐的方法。撤销后需要再次推送：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> push origin <span class="token operator">&lt;</span>branch-name<span class="token operator">&gt;</span>
</code></pre>
  <h4><a id="36__455"></a>3.6 处理合并提交的撤销</h4>
  <p>合并提交的撤销比较复杂，需要指定要撤销的父提交。你可以使用 <code>-m</code> 选项来指定父提交。例如，要撤销合并提交并保留主分支的变更：</p>
  <pre><code class="prism language-bash"><span class="token function">git</span> revert <span class="token parameter variable">-m</span> <span class="token number">1</span> <span class="token operator">&lt;</span>merge-commit-hash<span class="token operator">&gt;</span>
</code></pre>
  <p>这里的 <code>-m 1</code> 指的是保留第一个父提交的内容。</p>
  <h4><a id="37__462"></a>3.7 总结</h4>
  <ul>
   <li><code>git revert</code> 创建一个新提交来撤销指定的更改，不会修改提交历史。</li>
   <li>常用于已推送到远程的提交，特别是在协作项目中。</li>
   <li>能处理合并提交的撤销，且相对安全。</li>
  </ul>
  <p>你可以根据具体需求选择不同的用法来撤销提交。如果是未推送到远程的本地更改，<code>git reset</code> 可能会是更合适的工具。</p>
 </div>
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-a5d25dd831.css" rel="stylesheet">
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-e504d6a974.css" rel="stylesheet">
</div>
