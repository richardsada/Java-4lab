import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Config {
    private String username;
    private String password;
    private String userGroup;
    private boolean debugMode;
    private boolean autoTestMode;

    public Config(String filePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.userGroup = props.getProperty("userGroup");
            this.debugMode = Boolean.parseBoolean(props.getProperty("debugMode"));
            this.autoTestMode = Boolean.parseBoolean(props.getProperty("autoTestMode"));
        } catch (IOException e) {
            System.err.println("Ошибка загрузки файла настроек: " + e.getMessage());
            ConfigManager a = new ConfigManager();
            a.createNewProfile();
            System.out.print("После создания профиля нужно перезапустить программу. Завершение работы");
            System.exit(111);

        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public boolean isAutoTestMode() {
        return autoTestMode;
    }
}
