package tool;

public class Main {
    public static void main(String[] args) {
        ToolView view = new ToolView();
        ToolModel model = new ToolModel();
        new ToolController(view, model);
    }
}
