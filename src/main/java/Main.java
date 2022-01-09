import io.qt.widgets.QApplication;
import ui.windows.MainWindow;

public class Main {
    public static void main(String[] args) {
        QApplication.initialize(new String[0]);
        MainWindow window = new MainWindow();
        window.show();
        int returnCode = QApplication.exec();
        QApplication.shutdown();
        System.exit(returnCode);
    }
}
