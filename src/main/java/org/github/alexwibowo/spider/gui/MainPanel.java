/*
 * Created by JFormDesigner on Sat Aug 23 20:13:35 EST 2014
 */

package org.github.alexwibowo.spider.gui;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author alexwibowo
 */
public class MainPanel extends JPanel {
	public MainPanel() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		toolBar1 = new JToolBar();
		openFolderButton = new JButton();
		addButton = new JButton();
		clearButton = new JButton();
		splitPane1 = new JSplitPane();
		scrollPane1 = new JScrollPane();
		fileList = new JList();
		fileInfoPanel = new JSplitPane();
		imageLabel = new JLabel();
		imageDetailPanel = new JPanel();
		barcodeLabel = new JLabel();
		barcodeTextField = new JTextField();
		saveFileButton = new JButton();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"default, $lgap, fill:default:grow"));

		//======== toolBar1 ========
		{
			toolBar1.setFloatable(false);

			//---- openFolderButton ----
			openFolderButton.setIcon(UIManager.getIcon("Tree.openIcon"));
			openFolderButton.setBorder(new EmptyBorder(5, 5, 5, 5));
			toolBar1.add(openFolderButton);

			//---- addButton ----
			addButton.setText("add");
			toolBar1.add(addButton);

			//---- clearButton ----
			clearButton.setText("clear");
			toolBar1.add(clearButton);
		}
		add(toolBar1, CC.xy(1, 1));

		//======== splitPane1 ========
		{

			//======== scrollPane1 ========
			{

				//---- fileList ----
				fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane1.setViewportView(fileList);
			}
			splitPane1.setLeftComponent(scrollPane1);

			//======== fileInfoPanel ========
			{
				fileInfoPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
				fileInfoPanel.setTopComponent(imageLabel);

				//======== imageDetailPanel ========
				{
					imageDetailPanel.setLayout(new FormLayout(
						"default, $lcgap, default:grow",
						"2*(default, $lgap), default"));

					//---- barcodeLabel ----
					barcodeLabel.setText("Barcode");
					imageDetailPanel.add(barcodeLabel, CC.xy(1, 1));
					imageDetailPanel.add(barcodeTextField, CC.xy(3, 1));

					//---- saveFileButton ----
					saveFileButton.setText("Save");
					imageDetailPanel.add(saveFileButton, CC.xy(3, 5, CC.LEFT, CC.DEFAULT));
				}
				fileInfoPanel.setBottomComponent(imageDetailPanel);
			}
			splitPane1.setRightComponent(fileInfoPanel);
		}
		add(splitPane1, CC.xy(1, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JToolBar toolBar1;
	protected JButton openFolderButton;
	protected JButton addButton;
	protected JButton clearButton;
	protected JSplitPane splitPane1;
	protected JScrollPane scrollPane1;
	protected JList fileList;
	protected JSplitPane fileInfoPanel;
	protected JLabel imageLabel;
	protected JPanel imageDetailPanel;
	protected JLabel barcodeLabel;
	protected JTextField barcodeTextField;
	protected JButton saveFileButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
