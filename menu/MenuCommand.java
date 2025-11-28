package menu;

public interface MenuCommand {
    void execute();
    String getName();
    String getDescription();
}
