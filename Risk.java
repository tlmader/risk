/*	Risk.java
*
*  Description:	This class contains the main() method that creates
*				a model, a view, and a controller, passing the
*				model and the view to the controller.
*
*  Author: Ted Mader, 2/25/2014
*/

import javax.swing.JFrame;

public class Risk
{
	public static void main( String[] args )
	{
		RiskModel model = new RiskModel();
		RiskView view = new RiskView( model );
		RiskController controller = new RiskController( model, view );
	}
}