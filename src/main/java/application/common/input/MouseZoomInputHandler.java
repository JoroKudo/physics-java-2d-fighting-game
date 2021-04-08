package application.common.input;

import java.awt.Component;

import application.common.Camera;

public class MouseZoomInputHandler extends AbstractMouseInputHandler implements InputHandler {

	public MouseZoomInputHandler(Component component, Camera camera, int button) {
		super(component, camera, button);
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	protected void onMouseWheel(double rotation) {
		super.onMouseWheel(rotation);
		
		if (rotation > 0) {
			this.camera.scale *= 0.8;
		} else {
			this.camera.scale *= 1.2;
		}
	}
}
