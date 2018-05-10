package org.osgi.book.reader.gui;

import javax.swing.JTabbedPane;

import org.osgi.book.reader.api.Mailbox;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class ScannerMailboxTracker extends ServiceTracker {
	private final JTabbedPane tabbedPane;
	
	public ScannerMailboxTracker(BundleContext ctx, JTabbedPane tabbedPane) {
		super(ctx, Mailbox.class.getName(), null);
		this.tabbedPane = tabbedPane;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		// TODO Auto-generated method stub
		return super.addingService(reference);
	}
	
	
}
