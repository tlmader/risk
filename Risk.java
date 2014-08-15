import javax.swing.JFrame;

/**
 * This class contains the main() method that creates a model, a view, and a controller, passing the
 * model and the view to the controller.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class Risk {

	public static void main(String[] args) {
		RiskModel model = new RiskModel();
		RiskView view = new RiskView();
		RiskController controller = new RiskController(model, view);
	}
}