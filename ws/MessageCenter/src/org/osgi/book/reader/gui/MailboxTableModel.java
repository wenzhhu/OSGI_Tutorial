package org.osgi.book.reader.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.osgi.book.reader.api.Mailbox;
import org.osgi.book.reader.api.MailboxException;
import org.osgi.book.reader.api.Message;

public class MailboxTableModel extends AbstractTableModel {
	private static final String ERROR = "ERROR";
	private final Mailbox mailbox;
	private final List<Message> messages;

	
	public MailboxTableModel(Mailbox mailbox) throws MailboxException {
		this.mailbox = mailbox;
		long[] messageIds = mailbox.getAllMessages();
		messages = new ArrayList<Message>(messageIds.length);
		Message[] messageArray = mailbox.getMessages(messageIds);
		
		for (Message message : messageArray) {
			messages.add(message);
		}
		
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return messages.size();
	}

	public synchronized Object getValueAt(int rowIndex, int columnIndex) {
		Message message = messages.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return Long.toString(message.getId());
		case 1:
			return message.getSummary();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Subject";
		}
		
		return ERROR;
	}
	
	

}
