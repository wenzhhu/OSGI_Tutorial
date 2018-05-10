package org.osgi.tutorial;

import org.osgi.book.reader.api.Mailbox;
import org.osgi.book.reader.api.MailboxException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class MessageCountActivator2 implements BundleActivator {
	private ServiceTracker mboxTracker;

	public void start(BundleContext context) throws Exception {

		this.mboxTracker = new ServiceTracker(context, Mailbox.class.getName(),
				null);
		mboxTracker.open();
		printMessageCount();
	}

	private void printMessageCount() throws MailboxException, InterruptedException {
		Mailbox mbox = (Mailbox) mboxTracker.waitForService(5000);
		if (mbox != null) {
			int count = mbox.getAllMessages().length;
			System.out.println("There are " + count + " messages");
		}
	}

	public void stop(BundleContext context) throws Exception {
		mboxTracker.close();
	}

}
