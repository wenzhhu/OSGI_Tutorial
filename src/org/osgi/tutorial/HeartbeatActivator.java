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

public class HeartbeatActivator implements BundleActivator {
	private Thread thread;

	@Override
	public void start(final BundleContext context) throws Exception {
		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						Thread.sleep(1000);
						System.out.println("I'm still here");
					}
				} catch (final InterruptedException e) {
					System.out.println("I'm going now");
				}

			}
		});

		this.thread.start();
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		this.thread.interrupt();
	}

}

