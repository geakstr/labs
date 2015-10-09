public class Main {
	static abstract class AbstractSender {
		public static final int TEMPORARY = 1;
		public static final int LOCAL = 2;
		public static final int REMOTE = 3;

		protected AbstractSender nextSender;
		protected int type;

		public void setNextSender(final AbstractSender nextSender) {
			this.nextSender = nextSender;
		}

		public void sendMessage(final int type, final String message) {
			if (this.type <= type) {
				send(message);
			}

			if (nextSender != null) {
				nextSender.sendMessage(type, message);
			}
		}

		protected abstract void send(final String message);
	}
	
	static class DropboxSender extends AbstractSender {
		public DropboxSender() {
			this.type = AbstractSender.REMOTE;
		}
		
		@Override
		public void send(final String message) {
			System.out.println("To dropbox : " + message);
		}
	}

	static class ConsoleSender extends AbstractSender {
		public ConsoleSender() {
			this.type = AbstractSender.TEMPORARY;
		}
		
		@Override
		public void send(final String message) {
			System.out.println("To console : " + message);
		}
	}
	
	static class FileSender extends AbstractSender {
		public FileSender() {
			this.type = AbstractSender.LOCAL;
		}
		
		@Override
		public void send(final String message) {
			System.out.println("To file : " + message);
		}
	}
	
	private static AbstractSender getChainOfSenders() {
		final AbstractSender dropboxSender = new DropboxSender();
		final AbstractSender fileSender = new FileSender();
		final AbstractSender consoleSender = new ConsoleSender();
		
		dropboxSender.setNextSender(fileSender);
		fileSender.setNextSender(consoleSender);
		
		return dropboxSender;
	}
	
	public static void main(String[] args) {
		final AbstractSender chain = getChainOfSenders();
		
		chain.sendMessage(AbstractSender.TEMPORARY, "This is message");
		System.out.println("\n");
		chain.sendMessage(AbstractSender.LOCAL, "This is message");
		System.out.println("\n");
		chain.sendMessage(AbstractSender.REMOTE, "This is message");
	}
}
