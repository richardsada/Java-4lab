import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class ConfigManager {
    private static final String CONFIG_FILE = "config.properties";
    private Properties properties = new Properties();

    public ConfigManager() {
        this.loadConfig();
    }

    private void loadConfig() {
        try {
            InputStream input = new FileInputStream("config.properties");

            try {
                this.properties.load(input);
                System.out.println("Конфигурация загружена.");
            } catch (Throwable var5) {
                try {
                    ((InputStream)input).close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            ((InputStream)input).close();
        } catch (IOException var6) {
            System.out.println("Файл конфигурации не найден. Создан новый профиль.");
        }

    }

    private void saveConfig() {
        try {
            OutputStream output = new FileOutputStream("config.properties");

            try {
                this.properties.store(output, "Конфигурация пользователя");
                System.out.println("Конфигурация сохранена.");
            } catch (Throwable var5) {
                try {
                    ((OutputStream)output).close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            ((OutputStream)output).close();
        } catch (IOException var6) {
            IOException e = var6;
            System.err.println("Ошибка при сохранении конфигурации: " + e.getMessage());
        }

    }

    private void setUser(String username, String password, String userGroup, boolean debugMode, boolean autoTestMode) {
        this.properties.setProperty("username", username);
        this.properties.setProperty("password", password);
        this.properties.setProperty("userGroup", userGroup);
        this.properties.setProperty("debugMode", String.valueOf(debugMode));
        this.properties.setProperty("autoTestMode", String.valueOf(autoTestMode));
        this.saveConfig();
    }

    private void deleteProfile(String username) {
        if (this.properties.getProperty("username").equals(username)) {
            this.properties.remove("username");
            this.properties.remove("password");
            this.properties.remove("userGroup");
            this.properties.remove("debugMode");
            this.properties.remove("autoTestMode");
            this.saveConfig();
            System.out.println("Профиль пользователя " + username + " удалён.");
        } else {
            System.out.println("Профиль с именем пользователя " + username + " не найден.");
        }

    }

    public void createNewProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите группу пользователя (root/user): ");
        String userGroup = scanner.nextLine();
        System.out.print("Включить режим отладки (true/false): ");
        boolean debugMode = scanner.nextBoolean();
        System.out.print("Включить режим автотестов (true/false): ");
        boolean autoTestMode = scanner.nextBoolean();
        this.setUser(username, password, userGroup, debugMode, autoTestMode);
        System.out.println("Новый профиль создан успешно.");
    }

    public void showProfile() {
        System.out.println("Текущая конфигурация:");
        System.out.println("Имя пользователя: " + this.properties.getProperty("username"));
        System.out.println("Пароль: " + this.properties.getProperty("password"));
        System.out.println("Группа пользователя: " + this.properties.getProperty("userGroup"));
        System.out.println("Режим отладки: " + this.properties.getProperty("debugMode"));
        System.out.println("Режим автотестов: " + this.properties.getProperty("autoTestMode"));
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать текущий профиль");
            System.out.println("2. Создать новый профиль");
            System.out.println("3. Удалить профиль");
            System.out.println("4. Выйти");
            if(this.properties.getProperty("userGroup").equals("root")) {
                System.out.println("5. Изменить режим отладки");
                System.out.println("6. Изменить режим автотестов");
            }
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    this.showProfile();
                    break;
                case 2:
                    this.createNewProfile();
                    break;
                case 3:
                    System.out.print("Введите имя пользователя для удаления: ");
                    String username = scanner.nextLine();
                    this.deleteProfile(username);
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    this.saveConfig();
                    return;
                case 5:
                    if(this.properties.getProperty("userGroup").equals("root")) {
                        if (this.properties.getProperty("debugMode").equals("true")) {
                            this.properties.setProperty("debugMode", "false");
                            break;
                        }
                        this.properties.setProperty("debugMode", "true");
                        break;
                    }
                case 6:
                    if(this.properties.getProperty("userGroup").equals("root")) {
                        if (this.properties.getProperty("autoTestMode").equals("true")) {
                            this.properties.setProperty("autoTestMode", "false");
                            break;
                        }
                        this.properties.setProperty("autoTestMode", "true");
                        break;
                    }
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    public static void main(String[] args) {
        ConfigManager manager = new ConfigManager();
        manager.menu();
    }
}
