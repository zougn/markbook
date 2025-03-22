// 统一请求方法
async function restfulRequest({ method, endpoint, data }) {
  const url = http://43.156.10.17:8080/api
  try {：：
    const response = await fetch(url+endpoint, {
      method: method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: data ? JSON.stringify(data) : undefined
    });

    if (!response.ok) {
      throw new Error(`HTTP ${method}请求失败: ${response.status}`);
    }
  } catch (error) {
    console.error('API请求错误:', error);
  }
}

// 书签创建事件处理
chrome.bookmarks.onCreated.addListener(async (id, bookmark) => {
  const node = await getFullNodeInfo(id);
  const path = await getFolderPath(node.parentId);
  
  if (isFolder(node)) {
    await restfulRequest({
      method: 'POST',
      endpoint: '/folders',
      { title: node.title, path }
    });
  } else {
    await restfulRequest({
      method: 'POST',
      endpoint: '/bookmarks',
      { title: node.title, url: node.url, path }
    });
  }
});

// 书签删除事件处理
chrome.bookmarks.onRemoved.addListener((id, removeInfo) => {
  const resourceType = removeInfo.node.url ? 'bookmarks' : 'folders';
  restfulRequest({
    method: 'DELETE',
    endpoint: `/${resourceType}/${removeInfo.node.id}`
  });
});

// 书签更新事件处理
chrome.bookmarks.onChanged.addListener(async (id, changeInfo) => {
  const node = await getFullNodeInfo(id);
  const path = await getFolderPath(node.parentId);
  const resourceType = node.url ? 'bookmarks' : 'folders';

  await restfulRequest({
    method: 'PUT',
    endpoint: `/${resourceType}/${id}`,
    node.url ? 
      { title: node.title, url: node.url, path } : 
      { title: node.title, path }
  });
});
// 移动事件处理
chrome.bookmarks.onMoved.addListener(async (id, moveInfo) => {
  const node = await getFullNodeInfo(id);
  const newPath = await getFolderPath(moveInfo.parentId);
  const resourceType = node.url ? 'bookmarks' : 'folders';

  await restfulRequest({
    method: 'PATCH',
    endpoint: `/${resourceType}/${id}`,
    { path: newPath }
  });
});

// 其他辅助函数保持不变（isFolder, getFullNodeInfo, getFolderPath）


// 判断是否为文件夹
function isFolder(node) {
  return !node.url; // 无url属性即为文件夹[[19]]
}

// 获取完整节点信息
async function getFullNodeInfo(id) {
  const [node] = await chrome.bookmarks.get(id);
  return node;
}

// 获取文件夹路径
async function getFolderPath(folderId) {
  const path = [];
  let currentId = folderId;
  
  while (currentId !== "0") { // 根节点ID为0
    const [folder] = await chrome.bookmarks.get(currentId);
    path.unshift(folder.title);
    currentId = folder.parentId;
  }
  
  return path.join('/');
}
