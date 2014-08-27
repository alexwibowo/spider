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
public class MainPanel extends BasePanel<BarcodeMainPanelPresentationModel> {

	protected void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		menuToolBar = new JToolBar();
		openFolderButton = new JButton();
		panel2 = new JPanel();
		fileTableContainer = new JScrollPane();
		fileTable = new JTable();
		controlContainer = new JPanel();
		saveToLabel = new JLabel();
		targetDirectoryTextField = new JTextField();
		processButton = new JButton();
		progressContainer = new JScrollPane();
		progressTextArea = new JTextArea();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"default, $lgap, fill:default:grow"));

		//======== menuToolBar ========
		{
			menuToolBar.setFloatable(false);

			//---- openFolderButton ----
			openFolderButton.setIcon(UIManager.getIcon("Tree.openIcon"));
			openFolderButton.setBorder(new EmptyBorder(5, 5, 5, 5));
			menuToolBar.add(openFolderButton);
		}
		add(menuToolBar, CC.xy(1, 1));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"$ugap, default:grow, $ugap",
				"fill:default, $lgap, default, $lgap, fill:pref:grow, $lgap, $nlgap"));

			//======== fileTableContainer ========
			{
				fileTableContainer.setViewportView(fileTable);
			}
			panel2.add(fileTableContainer, CC.xy(2, 1, CC.FILL, CC.FILL));

			//======== controlContainer ========
			{
				controlContainer.setLayout(new FormLayout(
					"default, $lcgap, default:grow, $lcgap, default",
					"default"));

				//---- saveToLabel ----
				saveToLabel.setText("Save to");
				controlContainer.add(saveToLabel, CC.xy(1, 1));
				controlContainer.add(targetDirectoryTextField, CC.xy(3, 1));

				//---- processButton ----
				processButton.setText("Process");
				controlContainer.add(processButton, CC.xy(5, 1));
			}
			panel2.add(controlContainer, CC.xy(2, 3, CC.FILL, CC.DEFAULT));

			//======== progressContainer ========
			{

				//---- progressTextArea ----
				progressTextArea.setRows(10);
				progressContainer.setViewportView(progressTextArea);
			}
			panel2.add(progressContainer, CC.xy(2, 5, CC.FILL, CC.FILL));
		}
		add(panel2, CC.xy(1, 3, CC.FILL, CC.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JToolBar menuToolBar;
	protected JButton openFolderButton;
	protected JPanel panel2;
	protected JScrollPane fileTableContainer;
	protected JTable fileTable;
	protected JPanel controlContainer;
	protected JLabel saveToLabel;
	protected JTextField targetDirectoryTextField;
	protected JButton processButton;
	protected JScrollPane progressContainer;
	protected JTextArea progressTextArea;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
