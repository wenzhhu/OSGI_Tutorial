/*
 * Copyright (c) Orchestral Developments Ltd and the Orion Health group of companies (2001 - 2017).
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
package org.osgi.tutorial;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class BundleCounterActivator implements BundleActivator, BundleListener {

	private BundleContext context;
	@Override
	public void bundleChanged(final BundleEvent event) {
		String eventString;
		switch (event.getType()) {
			case BundleEvent.INSTALLED:
				eventString = "installed";
				break;
			case BundleEvent.UNINSTALLED:
				eventString = "uninstalled";
				break;
			default:
				eventString = "unknown event";
		}
		System.out.println("Bundle " + eventString);
		printBundleCount();
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		this.context = context;
		context.addBundleListener(this);
		printBundleCount();

	}

	private void printBundleCount() {
		System.out.println(
				"There are currently " + this.context.getBundles().length + " bundles installed.");

	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		context.removeBundleListener(this);
	}

}

