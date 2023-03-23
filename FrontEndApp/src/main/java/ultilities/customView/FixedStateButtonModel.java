package ultilities.customView;

import javax.swing.DefaultButtonModel;

public class FixedStateButtonModel extends DefaultButtonModel    {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2623404054095966008L;

	@Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public boolean isRollover() {
        return false;
    }

    @Override
    public void setRollover(boolean b) {
        //NOOP
    }

}

