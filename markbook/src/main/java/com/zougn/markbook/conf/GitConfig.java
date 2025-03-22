package com.zougn.markbook.conf;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.RemoteSetUrlCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.Objects;
import java.io.IOException;
import java.net.URISyntaxException;
import org.eclipse.jgit.api.PullCommand;

@Configuration
@ConfigurationProperties(prefix = "git")
@Data
public class GitConfig {
    private String path;
    private String remoteUrl;
    private String username;
    private String password;



//    @Bean
    public Git getGit(){
	    Git git = null;
	    try{
	CredentialsProvider provider = new UsernamePasswordCredentialsProvider(username, password);  //生成身份信息

    //本地已存在的文件夹
    File dirFile = new File(path);
    if (!dirFile.exists() && !dirFile.mkdirs()) { // 修复3：确保目录存在
                throw new IOException("Failed to create directory: " + path);
    }

    //这里我是加了个判断条件
    boolean hasGitRepo = false;
    File[] files = dirFile.listFiles();
    if (files != null) {
    for (File file : files) {
        if (file.getName().equals(".git")) {
            hasGitRepo = true;
            break;
        }
    }}
    if (!hasGitRepo) {
        //如果文件夹还没有创建git仓库，则调用创建git本地仓库(推送现有文件夹)
        git = Git.init().setDirectory(dirFile).call();

    }else{
        //如果已有git本地仓库，则直接打开（推送现有的 Git 仓库）
        git = Git.open(dirFile);
    }

    //设置远程仓库的地址
    RemoteSetUrlCommand remoteSetUrlCommand = git.remoteSetUrl();
    remoteSetUrlCommand.setUri(new URIish(remoteUrl));
    remoteSetUrlCommand.setName("origin");
    remoteSetUrlCommand.call();

    //判断一下本地是否存在有main分支
    Ref mainRef = retainMainRef(git, "main");
   PullCommand pull = git.pull();

   pull.setCredentialsProvider(provider).setRemoteBranchName("main").setRemote("origin").call();
      }
      catch (IOException | URISyntaxException | GitAPIException e){
    throw new RuntimeException("Git initialization failed", e); 
        }
        return Objects.requireNonNull(git, "Git instance should not be null");
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

