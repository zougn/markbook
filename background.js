// 递归获取完整文件夹路径
async function getFolderPath(parentId) {
  if (parentId === '0') return '无父文件夹';

  try {
    const [folder] = await chrome.bookmarks.get(parentId);
    if (!folder) return '未知路径';

    // 根文件夹处理（如"书签栏"）
    if (folder.parentId === '0') return folder.title; 

    // 递归获取父级路径
    const parentPath = await getFolderPath(folder.parentId);
    return `${parentPath}/${folder.title}`;
  } catch (error) {
    console.error('获取文件夹路径失败:', error);
    return '路径解析错误';
  }
}

// 监听书签创建事件
chrome.bookmarks.onCreated.addListener(async (id, bookmark) => {
  const { url, title, parentId } = bookmark;
  
  // 获取完整文件夹路径
  const folderPath = await getFolderPath(parentId);

  // 发送数据（添加folderPath字段）
  fetch('https://your-api-endpoint.com/bookmark', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      url,
      title: title || '未命名书签',
      folder: folderPath
    })
  })
  .then(response => response.ok 
    ? console.log('发送成功') 
    : console.error('HTTP错误:', response.status)
  )
  .catch(err => console.error('网络请求失败:', err));
});
