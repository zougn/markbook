package com.zougn;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.api.RemoteSetUrlCommand;
import java.io.File;
import java.io.IOException;

public class GitOperations {
    public static void main(String[] args) {
        String repoPath = "/root/markbook/";
        String remoteUrl = "https://github.com/zougn/markbook.git";
        String username = "zougn";
        String password = "";

        try {
            // 打开本地仓库
            Git git = Git.open(new File(repoPath));

            // 添加文件（示例添加所有文件）
            git.add().addFilepattern(".").call();

            // 提交更改
            git.commit().setMessage("Java实现的自动提交").call();

            // 配置凭证（使用GitHub Token更安全）
            CredentialsProvider credentials = 
                new UsernamePasswordCredentialsProvider(username, password);
	    RemoteSetUrlCommand remoteSetUrlCommand = git.remoteSetUrl();
	    remoteSetUrlCommand.setUri(new URIish(remoteUrl));
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

