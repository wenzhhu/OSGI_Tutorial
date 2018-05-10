package org.osgi.tutorial;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class BundleCounterActivator implements BundleActivator, BundleListener {

	private BundleContext context;
	
	
	public void start(BundleContext context) throws Exception {
		this.context = context;
		
		System.out.println("Registering listener...");	
		context.addBundleListener(this);
		printBundleCount();

	}

	private void printBundleCount() {
		int count = context.getBundles().length;
		System.out.println("There are currently " + count + " bundles.");		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Removing listener...");	
		context.removeBundleListener(this);
	}

	public void bundleChanged(BundleEvent event) {
		switch (event.getType()) {
		case BundleEvent.INSTALLED :
			System.out.println("Bundle installed");
			printBundleCount();
			break;
		case BundleEvent.UNINSTALLED :
			System.out.println("Bundle uninstalled");
			printBundleCount();
			break;
		default:
			System.out.println("Unknown event type: " + event.getType());
				
		}

	}

}
