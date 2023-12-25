/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
package com.floreantpos.ui.views;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.floreantpos.Messages;
import com.floreantpos.main.Application;
import com.floreantpos.model.CookingInstruction;
import com.floreantpos.model.TicketItemCookingInstruction;
import com.floreantpos.model.dao.CookingInstructionDAO;
import com.floreantpos.model.util.IllegalModelStateException;
import com.floreantpos.swing.QwertyKeyPad;
import com.floreantpos.ui.BeanEditor;
import com.floreantpos.ui.dialog.POSMessageDialog;
import net.miginfocom.swing.MigLayout;

public class CookingInstructionSelectionView extends BeanEditor {
	private JTable table;
	
	private List<TicketItemCookingInstruction> ticketItemCookingInstructions;

	JTextField tfCookingInstruction = new JTextField();

	public CookingInstructionSelectionView() {
		createUI();
	}

	private void createUI() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowHeight(35);
		scrollPane.setViewportView(table);
		
		setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel contentPanel = new JPanel(new MigLayout("fill,wrap 1,inset 0"));
		contentPanel.add(scrollPane, "grow");
		contentPanel.add(tfCookingInstruction, "h 35!,split 2,grow");
		QwertyKeyPad keyPad = new QwertyKeyPad();
		// contentPanel.add(keyPad, "grow");
		add(contentPanel);

		tfCookingInstruction.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) { doFilter(); }

			@Override
			public void keyReleased(KeyEvent e) {
				doFilter();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = table.getSelectedRow();
				if (index < 0)
					return;
				CookingInstructionTableModel model = (CookingInstructionTableModel) table.getModel();
				CookingInstruction cookingInstruction = model.rowsList.get(index);
				tfCookingInstruction.setText(cookingInstruction.getDescription());
			}
		});
	}

	@Override
	public boolean save() {
		String instruction = tfCookingInstruction.getText();
		if (instruction == null || instruction.isEmpty()) {
			POSMessageDialog.showError(Application.getPosWindow(), Messages.getString("CookingInstructionSelectionView.0")); //$NON-NLS-1$
			return false;
		}

		if (ticketItemCookingInstructions == null) {
			ticketItemCookingInstructions = new ArrayList<TicketItemCookingInstruction>(1);
		}

		CookingInstructionTableModel model = (CookingInstructionTableModel) table.getModel();
		TicketItemCookingInstruction cookingInstruction = new TicketItemCookingInstruction();
		cookingInstruction.setDescription(instruction);
		ticketItemCookingInstructions.add(cookingInstruction);

		return true;
	}

	@Override
	protected void updateView() {
		List<CookingInstruction> cookingInstructions = (List<CookingInstruction>) getBean();
		table.setModel(new CookingInstructionTableModel(cookingInstructions));
	}

	@Override
	protected boolean updateModel() throws IllegalModelStateException {
		return true;
	}

	@Override
	public String getDisplayText() {
		return Messages.getString("CookingInstructionSelectionView.1"); //$NON-NLS-1$
	}
	
	public List<TicketItemCookingInstruction> getTicketItemCookingInstructions() {
		return ticketItemCookingInstructions;
	}

	private void doFilter() {
		String text = tfCookingInstruction.getText().toLowerCase();
		if (text.length() < 2) {
			updateView();
			return;
		}

		List<CookingInstruction> filteredInstructions = new ArrayList<>();
		CookingInstructionTableModel model = (CookingInstructionTableModel) table.getModel();

		List<CookingInstruction> cookingInstructions = (List<CookingInstruction>) getBean();
		for (CookingInstruction ci : cookingInstructions) {
			String description = ci.getDescription().toLowerCase();
			if (description.contains(text)) {
				filteredInstructions.add(ci);
			}
		}

		model.rowsList = filteredInstructions;
		model.fireTableDataChanged();
	}

	class CookingInstructionTableModel extends AbstractTableModel {
		private final String[] columns = { Messages.getString("CookingInstructionSelectionView.2") }; //$NON-NLS-1$

		private List<CookingInstruction> rowsList;

		public CookingInstructionTableModel() {
		}

		public CookingInstructionTableModel(List<CookingInstruction> rows) {
			this.rowsList = rows;
		}

		@Override
		public int getRowCount() {
			if (rowsList == null) {
				return 0;
			}

			return rowsList.size();
		}

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public String getColumnName(int column) {
			return columns[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (rowsList == null) {
				return null;
			}

			CookingInstruction row = rowsList.get(rowIndex);

			switch (columnIndex) {
				case 0:
					return row.getDescription();
			}
			return null;
		}
	}
}
