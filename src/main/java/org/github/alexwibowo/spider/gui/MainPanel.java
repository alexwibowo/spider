/*
 * Created by JFormDesigner on Sat Aug 23 20:13:35 EST 2014
 */

package org.github.alexwibowo.spider.gui;

import java.awt.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.*;
import org.github.alexwibowo.spider.gui.model.BarcodeMainPanelPresentationModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author alexwibowo
 */
public class MainPanel extends BasePanel<BarcodeMainPanelPresentationModel> {

	protected void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		panel2 = new JPanel();
		simpleInternalFrame1 = new SimpleInternalFrame();
		fileTableContainer = new JScrollPane();
		fileTable = new JTable();
		processProgressBar = new JProgressBar();
		controlContainer = new JPanel();
		settingsLabel = compFactory.createSeparator("Output Settings");
		referenceFileLabel = new JLabel();
		referenceFileTextField = new JTextField();
		controlFileBrowseButton = new JButton();
		saveToLabel = new JLabel();
		targetDirectoryTextField = new JTextField();
		targetDirectoryBrowseButton = new JButton();
		processButton = new JButton();
		stopButton = new JButton();
		menuToolBar = new JToolBar();
		openFolderButton = new JButton();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"$ugap, default:grow, $ugap",
				"$nlgap, $lgap, fill:default:grow, $lgap, fill:default, $lgap, default, $lgap, $nlgap"));

			//======== simpleInternalFrame1 ========
			{
				simpleInternalFrame1.setTitle("Source Folder");
				simpleInternalFrame1.setToolBar(menuToolBar);
				Container simpleInternalFrame1ContentPane = simpleInternalFrame1.getContentPane();
				simpleInternalFrame1ContentPane.setLayout(new FormLayout(
					"default:grow",
					"fill:default:grow"));

				//======== fileTableContainer ========
				{

					//---- fileTable ----
					fileTable.setRowSelectionAllowed(false);
					fileTable.setGridColor(SystemColor.controlShadow);
					fileTableContainer.setViewportView(fileTable);
				}
				simpleInternalFrame1ContentPane.add(fileTableContainer, CC.xy(1, 1, CC.DEFAULT, CC.FILL));
			}
			panel2.add(simpleInternalFrame1, CC.xy(2, 3, CC.FILL, CC.DEFAULT));

			//---- processProgressBar ----
			processProgressBar.setString("0");
			panel2.add(processProgressBar, CC.xy(2, 5));

			//======== controlContainer ========
			{
				controlContainer.setLayout(new FormLayout(
					"right:default, $lcgap, center:default, $lcgap, default:grow, $lcgap, default",
					"3*(default, $lgap), default"));
				controlContainer.add(settingsLabel, CC.xywh(1, 1, 7, 1));

				//---- referenceFileLabel ----
				referenceFileLabel.setText("Reference File");
				controlContainer.add(referenceFileLabel, CC.xy(1, 3));
				controlContainer.add(referenceFileTextField, CC.xywh(3, 3, 3, 1));

				//---- controlFileBrowseButton ----
				controlFileBrowseButton.setText("Browse");
				controlFileBrowseButton.setIcon(UIManager.getIcon("Tree.openIcon"));
				controlContainer.add(controlFileBrowseButton, CC.xy(7, 3));

				//---- saveToLabel ----
				saveToLabel.setText("Save to");
				controlContainer.add(saveToLabel, CC.xy(1, 5));
				controlContainer.add(targetDirectoryTextField, CC.xywh(3, 5, 3, 1));

				//---- targetDirectoryBrowseButton ----
				targetDirectoryBrowseButton.setText("Browse");
				targetDirectoryBrowseButton.setIcon(UIManager.getIcon("Tree.openIcon"));
				controlContainer.add(targetDirectoryBrowseButton, CC.xy(7, 5));

				//---- processButton ----
				processButton.setText("Process");
//				processButton.setIcon(UIManager.getIcon("Menu.arrowIcon"));
				controlContainer.add(processButton, CC.xy(3, 7, CC.LEFT, CC.DEFAULT));

				//---- stopButton ----
				stopButton.setText("Stop");
				stopButton.setIcon(null);
				stopButton.setEnabled(false);
				controlContainer.add(stopButton, CC.xy(5, 7, CC.LEFT, CC.DEFAULT));
			}
			panel2.add(controlContainer, CC.xy(2, 7, CC.FILL, CC.DEFAULT));
		}
		add(panel2, CC.xy(1, 1, CC.FILL, CC.FILL));

		//======== menuToolBar ========
		{
			menuToolBar.setFloatable(false);

			//---- openFolderButton ----
			openFolderButton.setIcon(UIManager.getIcon("Tree.openIcon"));
			openFolderButton.setBorder(new EmptyBorder(5, 5, 5, 5));
			openFolderButton.setText("Open");
			menuToolBar.add(openFolderButton);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JPanel panel2;
	protected SimpleInternalFrame simpleInternalFrame1;
	protected JScrollPane fileTableContainer;
	protected JTable fileTable;
	protected JProgressBar processProgressBar;
	protected JPanel controlContainer;
	protected JComponent settingsLabel;
	protected JLabel referenceFileLabel;
	protected JTextField referenceFileTextField;
	protected JButton controlFileBrowseButton;
	protected JLabel saveToLabel;
	protected JTextField targetDirectoryTextField;
	protected JButton targetDirectoryBrowseButton;
	protected JButton processButton;
	protected JButton stopButton;
	protected JToolBar menuToolBar;
	protected JButton openFolderButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
