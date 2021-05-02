package cn.xanderye.config;

/**
 * @author XanderYe
 * @description:
 * @date 2021/5/1 21:45
 */
public class Config {
    private static final Config CONFIG = new Config();

    private String userDir;

    public static Config getInstance() {
        return CONFIG;
    }

    public String getUserDir() {
        return userDir;
    }

    public void setUserDir(String userDir) {
        this.userDir = userDir;
    }
}
