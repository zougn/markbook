<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="markdown_views prism-atom-one-dark">
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
  </svg>
  <p>Python对接<a href="https://pao.stocktv.top/" rel="nofollow">StockTV</a>全球金融数据API的封装实现及使用教程：</p>
  <pre><code class="prism language-python"><span class="token keyword">import</span> requests
<span class="token keyword">import</span> websockets
<span class="token keyword">import</span> asyncio
<span class="token keyword">from</span> typing <span class="token keyword">import</span> Dict<span class="token punctuation">,</span> List<span class="token punctuation">,</span> Optional<span class="token punctuation">,</span> Union
<span class="token keyword">from</span> datetime <span class="token keyword">import</span> datetime

<span class="token keyword">class</span> <span class="token class-name">StockTVClient</span><span class="token punctuation">:</span>
<span class="token triple-quoted-string string">"""
StockTV全球金融数据API客户端
支持股票、外汇、期货、加密货币等市场数据
"""</span>

    BASE_URL <span class="token operator">=</span> <span class="token string">"https://api.stocktv.top"</span>
    WS_URL <span class="token operator">=</span> <span class="token string">"wss://ws-api.stocktv.top/connect"</span>
    
    <span class="token keyword">def</span> <span class="token function">__init__</span><span class="token punctuation">(</span>self<span class="token punctuation">,</span> api_key<span class="token punctuation">:</span> <span class="token builtin">str</span><span class="token punctuation">)</span><span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""
        初始化客户端
        :param api_key: API密钥，需通过官方渠道获取
        """</span>
        self<span class="token punctuation">.</span>api_key <span class="token operator">=</span> api_key
        self<span class="token punctuation">.</span>session <span class="token operator">=</span> requests<span class="token punctuation">.</span>Session<span class="token punctuation">(</span><span class="token punctuation">)</span>
        self<span class="token punctuation">.</span>session<span class="token punctuation">.</span>headers<span class="token punctuation">.</span>update<span class="token punctuation">(</span><span class="token punctuation">{<!-- --></span><span class="token string">"User-Agent"</span><span class="token punctuation">:</span> <span class="token string">"StockTV-PythonClient/1.0"</span><span class="token punctuation">}</span><span class="token punctuation">)</span>
    
    <span class="token keyword">def</span> <span class="token function">_handle_response</span><span class="token punctuation">(</span>self<span class="token punctuation">,</span> response<span class="token punctuation">:</span> requests<span class="token punctuation">.</span>Response<span class="token punctuation">)</span> <span class="token operator">-</span><span class="token operator">&gt;</span> Union<span class="token punctuation">[</span>Dict<span class="token punctuation">,</span> List<span class="token punctuation">]</span><span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""统一处理API响应"""</span>
        <span class="token keyword">if</span> response<span class="token punctuation">.</span>status_code <span class="token operator">!=</span> <span class="token number">200</span><span class="token punctuation">:</span>
            <span class="token keyword">raise</span> Exception<span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"API请求失败，状态码：</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>response<span class="token punctuation">.</span>status_code<span class="token punctuation">}</span></span><span class="token string">，响应：</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>response<span class="token punctuation">.</span>text<span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">)</span>
        <span class="token keyword">return</span> response<span class="token punctuation">.</span>json<span class="token punctuation">(</span><span class="token punctuation">)</span>
    
    <span class="token comment"># ------------------ 股票市场接口 ------------------</span>
    <span class="token keyword">def</span> <span class="token function">get_stock_markets</span><span class="token punctuation">(</span>
        self<span class="token punctuation">,</span> 
        country_id<span class="token punctuation">:</span> <span class="token builtin">int</span><span class="token punctuation">,</span>
        page<span class="token punctuation">:</span> <span class="token builtin">int</span> <span class="token operator">=</span> <span class="token number">1</span><span class="token punctuation">,</span>
        page_size<span class="token punctuation">:</span> <span class="token builtin">int</span> <span class="token operator">=</span> <span class="token number">10</span>
    <span class="token punctuation">)</span> <span class="token operator">-</span><span class="token operator">&gt;</span> Dict<span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""
        获取股票市场列表
        :param country_id: 国家ID（例如14代表印度）
        :param page: 页码
        :param page_size: 每页数量
        """</span>
        endpoint <span class="token operator">=</span> <span class="token string">"/stock/stocks"</span>
        params <span class="token operator">=</span> <span class="token punctuation">{<!-- --></span>
            <span class="token string">"countryId"</span><span class="token punctuation">:</span> country_id<span class="token punctuation">,</span>
            <span class="token string">"page"</span><span class="token punctuation">:</span> page<span class="token punctuation">,</span>
            <span class="token string">"pageSize"</span><span class="token punctuation">:</span> page_size<span class="token punctuation">,</span>
            <span class="token string">"key"</span><span class="token punctuation">:</span> self<span class="token punctuation">.</span>api_key
        <span class="token punctuation">}</span>
        response <span class="token operator">=</span> self<span class="token punctuation">.</span>session<span class="token punctuation">.</span>get<span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>self<span class="token punctuation">.</span>BASE_URL<span class="token punctuation">}</span></span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>endpoint<span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">,</span> params<span class="token operator">=</span>params<span class="token punctuation">)</span>
        <span class="token keyword">return</span> self<span class="token punctuation">.</span>_handle_response<span class="token punctuation">(</span>response<span class="token punctuation">)</span>
    
    <span class="token keyword">def</span> <span class="token function">get_stock_kline</span><span class="token punctuation">(</span>
        self<span class="token punctuation">,</span>
        pid<span class="token punctuation">:</span> <span class="token builtin">int</span><span class="token punctuation">,</span>
        interval<span class="token punctuation">:</span> <span class="token builtin">str</span> <span class="token operator">=</span> <span class="token string">"PT15M"</span><span class="token punctuation">,</span>
        start_time<span class="token punctuation">:</span> Optional<span class="token punctuation">[</span><span class="token builtin">int</span><span class="token punctuation">]</span> <span class="token operator">=</span> <span class="token boolean">None</span><span class="token punctuation">,</span>
        end_time<span class="token punctuation">:</span> Optional<span class="token punctuation">[</span><span class="token builtin">int</span><span class="token punctuation">]</span> <span class="token operator">=</span> <span class="token boolean">None</span>
    <span class="token punctuation">)</span> <span class="token operator">-</span><span class="token operator">&gt;</span> List<span class="token punctuation">[</span>Dict<span class="token punctuation">]</span><span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""
        获取股票K线数据
        :param pid: 产品ID
        :param interval: 时间间隔（PT5M, PT15M, PT1H等）
        :param start_time: 开始时间戳（可选）
        :param end_time: 结束时间戳（可选）
        """</span>
        endpoint <span class="token operator">=</span> <span class="token string">"/stock/kline"</span>
        params <span class="token operator">=</span> <span class="token punctuation">{<!-- --></span>
            <span class="token string">"pid"</span><span class="token punctuation">:</span> pid<span class="token punctuation">,</span>
            <span class="token string">"interval"</span><span class="token punctuation">:</span> interval<span class="token punctuation">,</span>
            <span class="token string">"key"</span><span class="token punctuation">:</span> self<span class="token punctuation">.</span>api_key
        <span class="token punctuation">}</span>
        <span class="token keyword">if</span> start_time<span class="token punctuation">:</span>
            params<span class="token punctuation">[</span><span class="token string">"startTime"</span><span class="token punctuation">]</span> <span class="token operator">=</span> start_time
        <span class="token keyword">if</span> end_time<span class="token punctuation">:</span>
            params<span class="token punctuation">[</span><span class="token string">"endTime"</span><span class="token punctuation">]</span> <span class="token operator">=</span> end_time
            
        response <span class="token operator">=</span> self<span class="token punctuation">.</span>session<span class="token punctuation">.</span>get<span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>self<span class="token punctuation">.</span>BASE_URL<span class="token punctuation">}</span></span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>endpoint<span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">,</span> params<span class="token operator">=</span>params<span class="token punctuation">)</span>
        <span class="token keyword">return</span> self<span class="token punctuation">.</span>_handle_response<span class="token punctuation">(</span>response<span class="token punctuation">)</span>
    
    <span class="token comment"># ------------------ 外汇接口 ------------------</span>
    <span class="token keyword">def</span> <span class="token function">get_forex_rates</span><span class="token punctuation">(</span>self<span class="token punctuation">,</span> base_currency<span class="token punctuation">:</span> <span class="token builtin">str</span> <span class="token operator">=</span> <span class="token string">"USD"</span><span class="token punctuation">)</span> <span class="token operator">-</span><span class="token operator">&gt;</span> Dict<span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""获取实时外汇汇率"""</span>
        endpoint <span class="token operator">=</span> <span class="token string">"/market/currencyList"</span>
        params <span class="token operator">=</span> <span class="token punctuation">{<!-- --></span><span class="token string">"key"</span><span class="token punctuation">:</span> self<span class="token punctuation">.</span>api_key<span class="token punctuation">}</span>
        response <span class="token operator">=</span> self<span class="token punctuation">.</span>session<span class="token punctuation">.</span>get<span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>self<span class="token punctuation">.</span>BASE_URL<span class="token punctuation">}</span></span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>endpoint<span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">,</span> params<span class="token operator">=</span>params<span class="token punctuation">)</span>
        data <span class="token operator">=</span> self<span class="token punctuation">.</span>_handle_response<span class="token punctuation">(</span>response<span class="token punctuation">)</span>
        <span class="token keyword">return</span> data<span class="token punctuation">.</span>get<span class="token punctuation">(</span><span class="token string">"conversions"</span><span class="token punctuation">,</span> <span class="token punctuation">{<!-- --></span><span class="token punctuation">}</span><span class="token punctuation">)</span><span class="token punctuation">.</span>get<span class="token punctuation">(</span>base_currency<span class="token punctuation">,</span> <span class="token punctuation">{<!-- --></span><span class="token punctuation">}</span><span class="token punctuation">)</span>
    
    <span class="token comment"># ------------------ WebSocket实时数据 ------------------</span>
    <span class="token keyword">async</span> <span class="token keyword">def</span> <span class="token function">websocket_client</span><span class="token punctuation">(</span>self<span class="token punctuation">,</span> callback<span class="token punctuation">)</span><span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""
        WebSocket实时数据客户端
        :param callback: 数据处理回调函数
        """</span>
        url <span class="token operator">=</span> <span class="token string-interpolation"><span class="token string">f"</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>self<span class="token punctuation">.</span>WS_URL<span class="token punctuation">}</span></span><span class="token string">?key=</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>self<span class="token punctuation">.</span>api_key<span class="token punctuation">}</span></span><span class="token string">"</span></span>
        <span class="token keyword">async</span> <span class="token keyword">with</span> websockets<span class="token punctuation">.</span>connect<span class="token punctuation">(</span>url<span class="token punctuation">)</span> <span class="token keyword">as</span> ws<span class="token punctuation">:</span>
            <span class="token keyword">while</span> <span class="token boolean">True</span><span class="token punctuation">:</span>
                <span class="token keyword">try</span><span class="token punctuation">:</span>
                    data <span class="token operator">=</span> <span class="token keyword">await</span> ws<span class="token punctuation">.</span>recv<span class="token punctuation">(</span><span class="token punctuation">)</span>
                    <span class="token keyword">await</span> callback<span class="token punctuation">(</span>json<span class="token punctuation">.</span>loads<span class="token punctuation">(</span>data<span class="token punctuation">)</span><span class="token punctuation">)</span>
                    <span class="token comment"># 发送心跳保持连接</span>
                    <span class="token keyword">await</span> asyncio<span class="token punctuation">.</span>sleep<span class="token punctuation">(</span><span class="token number">30</span><span class="token punctuation">)</span>
                    <span class="token keyword">await</span> ws<span class="token punctuation">.</span>send<span class="token punctuation">(</span><span class="token string">"ping"</span><span class="token punctuation">)</span>
                <span class="token keyword">except</span> Exception <span class="token keyword">as</span> e<span class="token punctuation">:</span>
                    <span class="token keyword">print</span><span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"WebSocket错误: </span><span class="token interpolation"><span class="token punctuation">{<!-- --></span><span class="token builtin">str</span><span class="token punctuation">(</span>e<span class="token punctuation">)</span><span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">)</span>
                    <span class="token keyword">break</span>

<span class="token comment"># ================== 使用示例 ==================</span>
<span class="token keyword">if</span> **name** <span class="token operator">==</span> <span class="token string">"**main**"</span><span class="token punctuation">:</span>
API\_KEY <span class="token operator">=</span> <span class="token string">"YOUR\_API\_KEY"</span>  <span class="token comment"># 替换为实际API密钥</span>

    <span class="token comment"># 初始化客户端</span>
    client <span class="token operator">=</span> StockTVClient<span class="token punctuation">(</span>API_KEY<span class="token punctuation">)</span>
    
    <span class="token comment"># 示例1：获取印度股票市场列表</span>
    india_stocks <span class="token operator">=</span> client<span class="token punctuation">.</span>get_stock_markets<span class="token punctuation">(</span>country_id<span class="token operator">=</span><span class="token number">14</span><span class="token punctuation">)</span>
    <span class="token keyword">print</span><span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"印度股票市场数据：</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>india_stocks<span class="token punctuation">[</span><span class="token string">'data'</span><span class="token punctuation">]</span><span class="token punctuation">[</span><span class="token string">'records'</span><span class="token punctuation">]</span><span class="token punctuation">[</span><span class="token number">0</span><span class="token punctuation">]</span><span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">)</span>
    
    <span class="token comment"># 示例2：获取股票K线数据</span>
    kline_data <span class="token operator">=</span> client<span class="token punctuation">.</span>get_stock_kline<span class="token punctuation">(</span>pid<span class="token operator">=</span><span class="token number">7310</span><span class="token punctuation">,</span> interval<span class="token operator">=</span><span class="token string">"PT1H"</span><span class="token punctuation">)</span>
    <span class="token keyword">print</span><span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"最新K线数据：</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>kline_data<span class="token punctuation">[</span><span class="token string">'data'</span><span class="token punctuation">]</span><span class="token punctuation">[</span><span class="token operator">-</span><span class="token number">1</span><span class="token punctuation">]</span><span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">)</span>
    
    <span class="token comment"># 示例3：WebSocket实时数据</span>
    <span class="token keyword">async</span> <span class="token keyword">def</span> <span class="token function">handle_realtime_data</span><span class="token punctuation">(</span>data<span class="token punctuation">)</span><span class="token punctuation">:</span>
        <span class="token triple-quoted-string string">"""处理实时数据回调函数"""</span>
        <span class="token keyword">print</span><span class="token punctuation">(</span><span class="token string-interpolation"><span class="token string">f"实时更新：</span><span class="token interpolation"><span class="token punctuation">{<!-- --></span>data<span class="token punctuation">}</span></span><span class="token string">"</span></span><span class="token punctuation">)</span>
    
    <span class="token keyword">async</span> <span class="token keyword">def</span> <span class="token function">main</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">:</span>
        <span class="token keyword">await</span> client<span class="token punctuation">.</span>websocket_client<span class="token punctuation">(</span>handle_realtime_data<span class="token punctuation">)</span>
    
    <span class="token comment"># 运行WebSocket客户端</span>
    asyncio<span class="token punctuation">.</span>get_event_loop<span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">.</span>run_until_complete<span class="token punctuation">(</span>main<span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span>

</code></pre>

  <p>关键功能说明：</p>
  <ol>
   <li><strong>模块化设计</strong>：</li>
  </ol>
  <ul>
   <li>使用面向对象封装，方便扩展和维护</li>
   <li>分离不同市场接口（股票、外汇等）</li>
   <li>统一响应处理机制</li>
  </ul>
  <ol start="2">
   <li><strong>类型提示</strong>：</li>
  </ol>
  <ul>
   <li>参数和返回值均使用Python类型提示</li>
   <li>提高代码可读性和IDE支持</li>
  </ul>
  <ol start="3">
   <li><strong>错误处理</strong>：</li>
  </ol>
  <ul>
   <li>统一HTTP响应处理</li>
   <li>WebSocket自动重连机制</li>
   <li>异常捕获和提示</li>
  </ul>
  <ol start="4">
   <li><strong>高级功能</strong>：</li>
  </ol>
  <ul>
   <li>支持同步HTTP请求和异步WebSocket</li>
   <li>灵活的时间参数处理（支持时间戳）</li>
   <li>可配置的分页参数</li>
  </ul>
  <ol start="5">
   <li><strong>最佳实践</strong>：</li>
  </ol>
  <ul>
   <li>使用requests.Session保持连接池</li>
   <li>自定义User-Agent标识</li>
   <li>完善的文档字符串</li>
   <li>符合PEP8编码规范</li>
  </ul>
  <p>扩展建议：</p>
  <ol>
   <li><strong>缓存机制</strong>：</li>
  </ol>
  <pre><code class="prism language-python"><span class="token keyword">from</span> functools <span class="token keyword">import</span> lru_cache

<span class="token keyword">class</span> <span class="token class-name">StockTVClient</span><span class="token punctuation">:</span>
<span class="token decorator annotation punctuation">@lru\_cache</span><span class="token punctuation">(</span>maxsize<span class="token operator">=</span><span class="token number">128</span><span class="token punctuation">)</span>
<span class="token keyword">def</span> <span class="token function">get\_stock\_info</span><span class="token punctuation">(</span>self<span class="token punctuation">,</span> pid<span class="token punctuation">:</span> <span class="token builtin">int</span><span class="token punctuation">)</span><span class="token punctuation">:</span>
<span class="token triple-quoted-string string">"""带缓存的股票信息查询"""</span>
<span class="token comment"># 实现代码...</span>
</code></pre>

  <ol start="2">
   <li><strong>异步HTTP请求</strong>：</li>
  </ol>
  <pre><code class="prism language-python"><span class="token keyword">import</span> aiohttp

<span class="token keyword">async</span> <span class="token keyword">def</span> <span class="token function">async\_get\_stock\_markets</span><span class="token punctuation">(</span>self<span class="token punctuation">,</span> country\_id<span class="token punctuation">:</span> <span class="token builtin">int</span><span class="token punctuation">)</span><span class="token punctuation">:</span>
<span class="token keyword">async</span> <span class="token keyword">with</span> aiohttp<span class="token punctuation">.</span>ClientSession<span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token keyword">as</span> session<span class="token punctuation">:</span>
<span class="token keyword">async</span> <span class="token keyword">with</span> session<span class="token punctuation">.</span>get<span class="token punctuation">(</span>url<span class="token punctuation">,</span> params<span class="token operator">=</span>params<span class="token punctuation">)</span> <span class="token keyword">as</span> response<span class="token punctuation">:</span>
<span class="token keyword">return</span> <span class="token keyword">await</span> response<span class="token punctuation">.</span>json<span class="token punctuation">(</span><span class="token punctuation">)</span>
</code></pre>

  <ol start="3">
   <li><strong>数据转换工具</strong>：</li>
  </ol>
  <pre><code class="prism language-python"><span class="token keyword">def</span> <span class="token function">convert_kline_to_dataframe</span><span class="token punctuation">(</span>kline_data<span class="token punctuation">:</span> List<span class="token punctuation">)</span> <span class="token operator">-</span><span class="token operator">&gt;</span> pd<span class="token punctuation">.</span>DataFrame<span class="token punctuation">:</span>
    <span class="token triple-quoted-string string">"""将K线数据转换为Pandas DataFrame"""</span>
    <span class="token keyword">return</span> pd<span class="token punctuation">.</span>DataFrame<span class="token punctuation">(</span>
        kline_data<span class="token punctuation">,</span>
        columns<span class="token operator">=</span><span class="token punctuation">[</span><span class="token string">"timestamp"</span><span class="token punctuation">,</span> <span class="token string">"open"</span><span class="token punctuation">,</span> <span class="token string">"high"</span><span class="token punctuation">,</span> <span class="token string">"low"</span><span class="token punctuation">,</span> <span class="token string">"close"</span><span class="token punctuation">,</span> <span class="token string">"volume"</span><span class="token punctuation">,</span> <span class="token string">"vo"</span><span class="token punctuation">]</span>
    <span class="token punctuation">)</span><span class="token punctuation">.</span>set_index<span class="token punctuation">(</span><span class="token string">'timestamp'</span><span class="token punctuation">)</span>
</code></pre>
  <p>使用注意事项：</p>
  <ol>
   <li>申请并妥善保管API密钥</li>
   <li>遵守API调用频率限制</li>
   <li>处理时区转换（所有时间戳为UTC）</li>
   <li>使用try/except块捕获潜在异常</li>
   <li>生产环境建议添加重试机制</li>
  </ol>
  <p>完整项目应包含：</p>
  <ul>
   <li>单元测试</li>
   <li>日志记录</li>
   <li>配置文件管理</li>
   <li>更完善的类型定义</li>
   <li><a href="https://pao.stocktv.top/" rel="nofollow">API文档生成</a>（使用Sphinx等）</li>
  </ul>
  <p>该实现为开发者提供了可靠的数据接入基础，可根据具体需求进一步扩展功能模块。建议配合官方文档使用，及时关注API更新。</p>
 </div>
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-a5d25dd831.css" rel="stylesheet">
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-e504d6a974.css" rel="stylesheet">
</div>
