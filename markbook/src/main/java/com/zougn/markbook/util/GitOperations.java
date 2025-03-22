package com.zougn.markbook.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.eclipse.jgit.transport.CredentialsProvider;
import com.zougn.markbook.conf.GitConfig;

@Service
@AllArgsConstructor
public class GitOperations {
    private final GitConfig gitConfig;
    private final Git git;
    private final CredentialsProvider credentials; 

    public void push() {
        try {
            // 添加文件（示例添加所有文件）
            git.add().addFilepattern(".").call();
            // 提交更改
            git.commit().setMessage("markbook的自动提交").call();
            // 推送至远程仓库
            git.push()
                .setCredentialsProvider(credentials)
                .setRemote("origin")
                .call()
                .forEach(pushResult -> 
                    pushResult.getRemoteUpdates().forEach(update ->
                        System.out.println(update.getRemoteName() + ": " + update.getStatus())
                ));
            
            System.out.println("推送成功！");
        } catch (GitAPIException e) { 
            throw new RuntimeException("推送失败: " + e.getMessage(), e);
        }
    }
}
