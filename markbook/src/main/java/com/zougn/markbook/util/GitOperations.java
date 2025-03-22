package com.zougn.markbook.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service; 
import lombok.AllArgsConstructor;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.api.RemoteSetUrlCommand;
import com.zougn.markbook.conf.GitConfig;
import java.io.File;

@Service
@AllArgsConstructor
public class GitOperations {

    // 配置参数
    private final GitConfig gitConfig;
    //private final Git git;



    // 推送至远程仓库
    public  void push() {
            try {
            // 打开本地仓库
            Git git = Git.open(new File(gitConfig.getPath()));

            // 添加文件（示例添加所有文件）
            git.add().addFilepattern(".").call();

            // 提交更改
	    git.commit().setMessage("markbook的自动提交").call();
            // 配置凭证（使用GitHub Token更安全）
            CredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider(gitConfig.getUsername(), gitConfig.getPassword());
            RemoteSetUrlCommand remoteSetUrlCommand = git.remoteSetUrl();
            remoteSetUrlCommand.setUri(new URIish(gitConfig.getRemoteUrl()));
            remoteSetUrlCommand.setName("origin");
            remoteSetUrlCommand.call();
            // 推送至远程仓库
            git.push()
                .setCredentialsProvider(credentials)
                .setRemote("origin")
                .call();

            System.out.println("推送成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
