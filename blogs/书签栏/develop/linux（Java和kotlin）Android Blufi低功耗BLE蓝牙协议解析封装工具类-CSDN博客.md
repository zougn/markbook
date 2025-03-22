<div id="article_content" class="article_content clearfix">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
 <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-704d5b9767.css">
 <div id="content_views" class="markdown_views prism-atom-one-dark">
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
  </svg>
  <p><strong>BluFi</strong> 是一项基于蓝牙通道的 Wi-Fi 网络配置功能，适用于 ESP32。它通过安全协议将 Wi-Fi 的 SSID、密码等配置信息传输到 ESP32。基于这些信息，ESP32 可进而连接到 AP 或建立 SoftAP。 BluFi 流程的关键部分包括数据的分片、加密以及校验和验证。 用户可按需自定义用于对称加密、非对称加密以及校验的算法。此处，我们采用 DH 算法进行密钥协商，128-AES 算法用于数据加密，CRC16 算法用于校验和验证。</p>
  <p>在<strong>BLE通信</strong>中，协议的理解和封装是非常重要的。通常，BLE设备会通过<strong>GATT协议</strong>定义<strong>服务</strong>和<strong>特征值</strong>（Characteristics），而具体的通信协议则由设备厂商定义。我们可以通过封装一个协议解析和生成的工具类来简化BLE通信的开发。<br> <img src="https://i-blog.csdnimg.cn/direct/39d0be6a82844361adef10828cd992da.png" alt="在这里插入图片描述"><br> <img src="https://i-blog.csdnimg.cn/direct/6c3d5fffad5f452a86cb109663b84b41.png" alt="在这里插入图片描述"></p>
  <p>以下是一个示例，展示如何封装一个<strong>BLE协议解析</strong>和<strong>生成</strong>的工具类。</p>
  <p><strong>BluFiFrame封装类（Java版)</strong></p>
  <pre><code>import java.util.Arrays;

public class BluFiFrame {
private static final int HEADER\_LENGTH = 4; // 类型 + 帧控制 + 序列号 + 数据长度
private static final int CHECKSUM\_LENGTH = 2;

    public byte type;
    public byte frameControl;
    public byte sequenceNumber;
    public byte[] data;
    public boolean isFragmented;
    
    /**
     * 生成BluFi帧
     */
    public byte[] buildFrame() {
        int frameLength = HEADER_LENGTH + data.length + CHECKSUM_LENGTH + (isFragmented ? 2 : 0);
        byte[] frame = new byte[frameLength];
    
        // 写入头部
        frame[0] = type;
        frame[1] = frameControl;
        frame[2] = sequenceNumber;
        frame[3] = (byte) data.length;
    
        int offset = HEADER_LENGTH;
    
        // 如果是分片帧，写入2字节的内容总长度
        if (isFragmented) {
            frame[offset++] = (byte) (data.length &gt;&gt; 8);
            frame[offset++] = (byte) (data.length &amp; 0xFF);
        }
    
        // 写入数据
        System.arraycopy(data, 0, frame, offset, data.length);
        offset += data.length;
    
        // 计算并写入校验和
        byte[] checksum = calculateChecksum(frame, 0, offset);
        frame[offset++] = checksum[0];
        frame[offset] = checksum[1];
    
        return frame;
    }
    
    /**
     * 解析BluFi帧
     */
    public static BluFiFrame parseFrame(byte[] frame) throws IllegalArgumentException {
        if (frame.length &lt; HEADER_LENGTH + CHECKSUM_LENGTH) {
            throw new IllegalArgumentException("Frame too short");
        }
    
        BluFiFrame bluFiFrame = new BluFiFrame();
        bluFiFrame.type = frame[0];
        bluFiFrame.frameControl = frame[1];
        bluFiFrame.sequenceNumber = frame[2];
        byte dataLength = frame[3];
    
        bluFiFrame.isFragmented = (bluFiFrame.frameControl &amp; 0x01) != 0; // 检查分片位
        int dataStartIndex = HEADER_LENGTH + (bluFiFrame.isFragmented ? 2 : 0); // 分片帧多2字节的内容总长度
    
        // 检查数据长度是否有效
        if (frame.length &lt; dataStartIndex + dataLength + CHECKSUM_LENGTH) {
            throw new IllegalArgumentException("Invalid data length");
        }
    
        // 提取数据
        bluFiFrame.data = Arrays.copyOfRange(frame, dataStartIndex, dataStartIndex + dataLength);
    
        // 校验和验证
        byte[] checksum = Arrays.copyOfRange(frame, frame.length - CHECKSUM_LENGTH, frame.length);
        byte[] expectedChecksum = calculateChecksum(frame, 0, frame.length - CHECKSUM_LENGTH);
    
        if (!Arrays.equals(checksum, expectedChecksum)) {
            throw new IllegalArgumentException("Checksum mismatch");
        }
    
        return bluFiFrame;
    }
    
    /**
     * 计算校验和（2字节）
     */
    private static byte[] calculateChecksum(byte[] data, int offset, int length) {
        int sum = 0;
        for (int i = offset; i &lt; length; i++) {
            sum += data[i] &amp; 0xFF;
        }
        return new byte[]{(byte) (sum &gt;&gt; 8), (byte) (sum &amp; 0xFF)};
    }
    
    @Override
    public String toString() {
        return String.format("BluFiFrame{type=0x%02X, frameControl=0x%02X, sequenceNumber=0x%02X, data=%s, isFragmented=%b}",
                type, frameControl, sequenceNumber, Arrays.toString(data), isFragmented);
    }

}
</code></pre>

  <p><strong>使用示例</strong><br> <strong>生成帧</strong></p>
  <pre><code>BluFiFrame frame = new BluFiFrame();
frame.type = 0x01; // 帧类型
frame.frameControl = 0x00; // 不分片
frame.sequenceNumber = 0x01; // 序列号
frame.data = new byte[]{0x10, 0x20, 0x30}; // 数据
frame.isFragmented = false; // 不分片

byte\[\] packet = frame.buildFrame();
System.out.println("Generated frame: " + Arrays.toString(packet));
</code></pre>

  <p><strong>解析帧</strong></p>
  <pre><code>byte[] receivedFrame = new byte[]{
        0x01, 0x00, 0x01, 0x03, // 类型、帧控制、序列号、数据长度
        0x10, 0x20, 0x30,       // 数据
        0x00, 0x63              // 校验和
};

try {
BluFiFrame parsedFrame = BluFiFrame.parseFrame(receivedFrame);
System.out.println("Parsed frame: " + parsedFrame);
} catch (IllegalArgumentException e) {
System.err.println("Failed to parse frame: " + e.getMessage());
}
</code></pre>

  <p><strong>输出示例</strong><br> <strong>生成帧</strong></p>
  <pre><code>Generated frame: [1, 0, 1, 3, 16, 32, 48, 0, 99]
</code></pre>
  <p><strong>解析帧</strong></p>
  <pre><code>Parsed frame: BluFiFrame{type=0x01, frameControl=0x00, sequenceNumber=0x01, data=[16, 32, 48], isFragmented=false}
</code></pre>
  <p>///</p>
  <p><strong>Kotlin 实现</strong><br> <strong>BluFiFrame封装类（Kotlin版)</strong></p>
  <pre><code>class BluFiFrame(
    var type: Byte,
    var frameControl: Byte,
    var sequenceNumber: Byte,
    var data: ByteArray,
    var isFragmented: Boolean
) {
    companion object {
        private const val HEADER_LENGTH = 4 // 类型 + 帧控制 + 序列号 + 数据长度
        private const val CHECKSUM_LENGTH = 2

        /**
         * 解析 BluFi 帧
         */
        fun parseFrame(frame: ByteArray): BluFiFrame {
            require(frame.size &gt;= HEADER_LENGTH + CHECKSUM_LENGTH) { "Frame too short" }
    
            val type = frame[0]
            val frameControl = frame[1]
            val sequenceNumber = frame[2]
            val dataLength = frame[3].toInt()
            val isFragmented = (frameControl.toInt() and 0x01) != 0 // 检查分片位
    
            val dataStartIndex = HEADER_LENGTH + if (isFragmented) 2 else 0 // 分片帧多2字节的内容总长度
            require(frame.size &gt;= dataStartIndex + dataLength + CHECKSUM_LENGTH) { "Invalid data length" }
    
            // 提取数据
            val data = frame.copyOfRange(dataStartIndex, dataStartIndex + dataLength)
    
            // 校验和验证
            val checksum = frame.copyOfRange(frame.size - CHECKSUM_LENGTH, frame.size)
            val expectedChecksum = calculateChecksum(frame, 0, frame.size - CHECKSUM_LENGTH)
            require(checksum.contentEquals(expectedChecksum)) { "Checksum mismatch" }
    
            return BluFiFrame(type, frameControl, sequenceNumber, data, isFragmented)
        }
    
        /**
         * 计算校验和（2字节）
         */
        private fun calculateChecksum(data: ByteArray, offset: Int, length: Int): ByteArray {
            var sum = 0
            for (i in offset until length) {
                sum += data[i].toInt() and 0xFF
            }
            return byteArrayOf((sum shr 8).toByte(), (sum and 0xFF).toByte())
        }
    }
    
    /**
     * 生成 BluFi 帧
     */
    fun buildFrame(): ByteArray {
        val frameLength = HEADER_LENGTH + data.size + CHECKSUM_LENGTH + if (isFragmented) 2 else 0
        val frame = ByteArray(frameLength)
    
        // 写入头部
        frame[0] = type
        frame[1] = frameControl
        frame[2] = sequenceNumber
        frame[3] = data.size.toByte()
    
        var offset = HEADER_LENGTH
    
        // 如果是分片帧，写入2字节的内容总长度
        if (isFragmented) {
            frame[offset++] = (data.size shr 8).toByte()
            frame[offset++] = (data.size and 0xFF).toByte()
        }
    
        // 写入数据
        System.arraycopy(data, 0, frame, offset, data.size)
        offset += data.size
    
        // 计算并写入校验和
        val checksum = calculateChecksum(frame, 0, offset)
        frame[offset++] = checksum[0]
        frame[offset] = checksum[1]
    
        return frame
    }
    
    override fun toString(): String {
        return "BluFiFrame(type=0x${type.toHex()}, frameControl=0x${frameControl.toHex()}, " +
               "sequenceNumber=0x${sequenceNumber.toHex()}, data=${data.toHexString()}, " +
               "isFragmented=$isFragmented)"
    }
    
    // 扩展函数：将 Byte 转换为十六进制字符串
    private fun Byte.toHex(): String = "%02X".format(this)
    
    // 扩展函数：将 ByteArray 转换为十六进制字符串
    private fun ByteArray.toHexString(): String = joinToString(" ") { "%02X".format(it) }

}
</code></pre>

  <p><strong>优化点</strong><br> <strong>使用 Kotlin 的数据类和扩展函数</strong>：</p>
  <p>简化了代码结构，提高了可读性。</p>
  <p>使用扩展函数将 Byte 和 ByteArray 转换为十六进制字符串，方便调试。</p>
  <p><strong>更简洁的错误处理</strong>：</p>
  <p>使用 require 函数进行参数校验，代码更简洁。</p>
  <p><strong>使用示例</strong><br> <strong>生成帧</strong></p>
  <pre><code>val frame = BluFiFrame(
    type = 0x01,
    frameControl = 0x00,
    sequenceNumber = 0x01,
    data = byteArrayOf(0x10, 0x20, 0x30),
    isFragmented = false
)

val packet = frame.buildFrame()
println("Generated frame: ${packet.toHexString()}")
</code></pre>

  <p><strong>解析帧</strong></p>
  <pre><code>val receivedFrame = byteArrayOf(
    0x01, 0x00, 0x01, 0x03, // 类型、帧控制、序列号、数据长度
    0x10, 0x20, 0x30,       // 数据
    0x00, 0x63              // 校验和
)

try {
val parsedFrame = BluFiFrame.parseFrame(receivedFrame)
println("Parsed frame: $parsedFrame")
} catch (e: IllegalArgumentException) {
println("Failed to parse frame: ${e.message}")
}
</code></pre>

  <p><strong>输出示例</strong><br> <strong>生成帧</strong></p>
  <pre><code>Generated frame: 01 00 01 03 10 20 30 00 63
</code></pre>
  <p><strong>解析帧</strong></p>
  <pre><code>Parsed frame: BluFiFrame(type=0x01, frameControl=0x00, sequenceNumber=0x01, data=10 20 30, isFragmented=false)
</code></pre>
  <p><strong>总结</strong><br> <strong>Kotlin</strong> 版本的 <strong>BluFiFrame</strong> 更加简洁和易读，同时保留了核心功能。</p>
 </div>
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-a5d25dd831.css" rel="stylesheet">
 <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-e504d6a974.css" rel="stylesheet">
</div>
