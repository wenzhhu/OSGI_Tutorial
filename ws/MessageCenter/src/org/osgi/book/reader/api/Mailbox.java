package org.osgi.book.reader.api;

public interface Mailbox {
	public static final String NAME_PROPERTY = "mailboxName";
	
	long[] getAllMessages() throws MailboxException;
	
	long[] getMessageSince(long id) throws MailboxException;
	
	void markRead(boolean read, long[] ids) throws MailboxException;
	
	Message[] getMessages(long[] ids) throws MailboxException;
}
