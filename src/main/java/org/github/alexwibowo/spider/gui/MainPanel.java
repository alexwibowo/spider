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
		splitPane1 = new JSplitPane();
		scrollPane1 = new JScrollPane();
		fileList = new JList();
		panel1 = new JPanel();
		image = new JLabel();
		panel2 = new JPanel();
		barcodeLabel = new JLabel();
		textField1 = new JTextField();
		button1 = new JButton();

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

			//======== panel1 ========
			{
				panel1.setLayout(new FormLayout(
					"default:grow",
					"fill:default:grow, $lgap, default"));
				panel1.add(image, CC.xy(1, 1));

				//======== panel2 ========
				{
					panel2.setLayout(new FormLayout(
						"default, $lcgap, default:grow",
						"default, $lgap, default"));

					//---- barcodeLabel ----
					barcodeLabel.setText("Barcode");
					panel2.add(barcodeLabel, CC.xy(1, 1));
					panel2.add(textField1, CC.xy(3, 1));

					//---- button1 ----
					button1.setText("Save");
					panel2.add(button1, CC.xy(3, 3, CC.LEFT, CC.DEFAULT));
				}
				panel1.add(panel2, CC.xy(1, 3));
			}
			splitPane1.setRightComponent(panel1);
		}
		add(splitPane1, CC.xy(1, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JToolBar toolBar1;
	protected JButton openFolderButton;
	protected JSplitPane splitPane1;
	protected JScrollPane scrollPane1;
	protected JList fileList;
	protected JPanel panel1;
	protected JLabel image;
	protected JPanel panel2;
	protected JLabel barcodeLabel;
	protected JTextField textField1;
	protected JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
