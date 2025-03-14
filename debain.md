 The repository 'https://ppa.launchpadcontent.net/openjdk-r/ppa/ubuntu bookworm Release' does not have a Release file.
但是这个第三方库并不支持22.04版本的ubuntu，因此要解决这个问题，要么就降低Ubuntu的版本，要么通过如下命令把这个第三方库移除

sudo add-apt-repository --remove ppa:openjdk/ppa

