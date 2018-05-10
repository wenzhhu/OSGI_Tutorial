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

import java.io.File;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class HelloUpdaterActivator implements BundleActivator {
	private static final long INTERVAL = 5000;
	private static final String BUNDLE = "helloworld.jar";

	private final Thread thread = new Thread(new BundleUpdater());
	private volatile BundleContext context;

	@Override
	public void start(final BundleContext context) throws Exception {
		this.context = context;
		this.thread.start();
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		this.thread.interrupt();

	}

	protected Bundle findBundleByLocation(final String location) {
		final Bundle[] bundles = this.context.getBundles();
		for (final Bundle bundle2 : bundles) {
			if (bundle2.getLocation().equals(location)) {
				return bundle2;
			}
		}

		return null;
	}

	private class BundleUpdater implements Runnable {
		@Override
		public void run() {
			try {
				final File file = new File(BUNDLE);
				final String location = "file:" + BUNDLE;
				while (!Thread.currentThread().isInterrupted()) {
					Thread.sleep(INTERVAL);
					final Bundle bundle = findBundleByLocation(location);
					if (bundle != null && file.exists()) {
						final long bundleModified = bundle.getLastModified();
						final long fileModified = file.lastModified();
						if (fileModified > bundleModified) {
							System.out.println("File is newer, updating");
							bundle.update();
						}
					}
				}
			} catch (final InterruptedException e) {
				System.out.println("I'm going now");
			} catch (final BundleException e) {
				System.err.println("Error updating bundle");
				e.printStackTrace();
			}

		}

	}

}

