package com.zougn.markbook.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RemoteSetUrlCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import lombok.AllArgsConstructor;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Service;
import lombok.Data;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.Objects;
import java.io.IOException;
import java.net.URISyntaxException;
import org.eclipse.jgit.api.PullCommand;
import com.zougn.markbook.conf.GitConfig;

@Service
@AllArgsConstructor
public class GitOperations {

    // 配置参数
    private final GitConfig gitConfig;
    //private final Git git;



    // 推送至远程仓库
    public  void push() {
        try {
            //本地已存在的文件夹
            File dirFile = new File(gitConfig.getPath());
            if (!dirFile.exists() && !dirFile.mkdirs()) { // 修复3：确保目录存在
                        throw new IOException("Failed to create directory: " + gitConfig.getPath());
            }

            //这里我是加了个判断条件
            boolean hasGitRepo = false;
            File[] files = dirFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    System.out.println("file: "+file.getName());
                    if (file.getName().equals(".git")) {
                        hasGitRepo = true;
                        break;
                    }
                }
            }
            Git git;
            if (!hasGitRepo) {
                //如果文件夹还没有创建git仓库，则调用创建git本地仓库(推送现有文件夹)
                git = Git.init().setDirectory(dirFile).call();

            }else{
                //如果已有git本地仓库，则直接打开（推送现有的 Git 仓库）
                git = Git.open(dirFile);
            }
            // 配置凭证（使用GitHub Token更安全）
            CredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider(gitConfig.getUsername(), gitConfig.getPassword());
            RemoteSetUrlCommand remoteSetUrlCommand = git.remoteSetUrl();
            remoteSetUrlCommand.setUri(new URIish(gitConfig.getRemoteUrl()));
            remoteSetUrlCommand.setName("origin");
            remoteSetUrlCommand.call();

                //判断一下本地是否存在有main分支
            Ref mainRef = retainMainRef(git, "main");

            PullCommand pull = git.pull();

            pull.setCredentialsProvider(credentials).setRemoteBranchName("main").setRemote("origin").call();

            // 添加文件（示例添加所有文件）
            git.add().addFilepattern(".").call();

            // 提交更改
            git.commit().setMessage("markbook的自动提交").call();
            
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

   /**
     * 判断本地仓库是否存在有某分支，如果没有则创建
     */
    private Ref retainMainRef(Git git, String branchName) throws GitAPIException {

        List<Ref> refList = git.branchList().call();
        String branchNameStr = "refs/heads/" + branchName;
        for (Ref ref : refList) {
            if (ref.getName().equals(branchNameStr)) {
                return ref;
            }
        }
        return git.branchCreate().setName(branchName).call();
    }

}
