package com.auction;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple demo that uses java.util.Timer to schedule a task to execute once 5
 * seconds have passed.
 */

public class AuctionTimer {
	// Toolkit toolkit;
	int productId;
	Timer timer;

	public AuctionTimer(int productId) {
		int seconds = Constants.ALLOWED_AUCTION_TIME_SPAN_IN_SECONDS;
		// toolkit = Toolkit.getDefaultToolkit();
		System.out.println("starting timer for productId " + productId);
		this.productId = productId;
		timer = new Timer();
		timer.schedule(new RemindTask(), seconds * 1000);
	}

	class RemindTask extends TimerTask {

		public void run() {
			System.out.println("Time's up!");
			ProductManagement.getInstance().productTimeInAuctionIsUp(productId);
			// toolkit.beep();
			timer.cancel(); // Not necessary because we call System.exit
			// System.exit(0); //Stops the AWT thread (and everything else)
		}
	}

	// public static void main(String args[]) {
	// System.out.println("About to schedule task.");
	// new AuctionTimer(5);
	// System.out.println("Task scheduled.");
	// }
}