/*
 * Created by JFormDesigner on Sat Aug 23 20:13:35 EST 2014
 */

package org.github.alexwibowo.spider.gui;

import java.awt.*;
import javax.swing.border.*;
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
		toolBar1 = new JToolBar();
		openFolderButton = new JButton();
		simpleInternalFrame1 = new SimpleInternalFrame();
		fileTableContainer = new JScrollPane();
		fileTable = new JTable();
		processProgressBar = new JProgressBar();
		controlContainer = new JPanel();
		settingsLabel = compFactory.createSeparator("Output Settings");
		catalogueFileLabel = new JLabel();
		catalogueFileValueLabel = new JLabel();
		controlFileBrowseButton = new JButton();
		targetDirectoryLabel = new JLabel();
		targetDirectoryValueLabel = new JLabel();
		targetDirectoryBrowseButton = new JButton();
		processButton = new JButton();
		stopButton = new JButton();
		statusPanel = new JPanel();
		statusLabel = new JLabel();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"$lcgap, default:grow, $lcgap",
				"$nlgap, $lgap, default, $lgap, fill:default:grow, $lgap, fill:default, $lgap, default, $lgap, $nlgap, fill:default"));

			//======== toolBar1 ========
			{
				toolBar1.setFloatable(false);

				//---- openFolderButton ----
				openFolderButton.setIcon(UIManager.getIcon("Tree.openIcon"));
				openFolderButton.setBorder(new EmptyBorder(5, 5, 5, 5));
				openFolderButton.setText("Open");
				toolBar1.add(openFolderButton);
			}
			panel2.add(toolBar1, CC.xy(2, 3));

			//======== simpleInternalFrame1 ========
			{
				simpleInternalFrame1.setTitle("Source Folder");
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
			panel2.add(simpleInternalFrame1, CC.xy(2, 5, CC.FILL, CC.DEFAULT));

			//---- processProgressBar ----
			processProgressBar.setString("0");
			panel2.add(processProgressBar, CC.xy(2, 7));

			//======== controlContainer ========
			{
				controlContainer.setLayout(new FormLayout(
					"right:default, $lcgap, center:default, $lcgap, default:grow, $lcgap, default",
					"3*(default, $lgap), default"));
				controlContainer.add(settingsLabel, CC.xywh(1, 1, 7, 1));

				//---- catalogueFileLabel ----
				catalogueFileLabel.setText("Catalogue File");
				controlContainer.add(catalogueFileLabel, CC.xy(1, 3));
				controlContainer.add(catalogueFileValueLabel, CC.xywh(3, 3, 3, 1));

				//---- controlFileBrowseButton ----
				controlFileBrowseButton.setText("Browse");
				controlFileBrowseButton.setIcon(null);
				controlContainer.add(controlFileBrowseButton, CC.xy(7, 3));

				//---- targetDirectoryLabel ----
				targetDirectoryLabel.setText("Save to");
				controlContainer.add(targetDirectoryLabel, CC.xy(1, 5));
				controlContainer.add(targetDirectoryValueLabel, CC.xywh(3, 5, 3, 1));

				//---- targetDirectoryBrowseButton ----
				targetDirectoryBrowseButton.setText("Browse");
				targetDirectoryBrowseButton.setIcon(null);
				controlContainer.add(targetDirectoryBrowseButton, CC.xy(7, 5));

				//---- processButton ----
				processButton.setText("Process");
				processButton.setIcon(null);
				controlContainer.add(processButton, CC.xy(3, 7, CC.LEFT, CC.DEFAULT));

				//---- stopButton ----
				stopButton.setText("Stop");
				stopButton.setIcon(null);
				stopButton.setEnabled(false);
				controlContainer.add(stopButton, CC.xy(5, 7, CC.LEFT, CC.DEFAULT));
			}
			panel2.add(controlContainer, CC.xy(2, 9, CC.FILL, CC.DEFAULT));

			//======== statusPanel ========
			{
				statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				statusPanel.setLayout(new FormLayout(
					"default:grow",
					"fill:default:grow"));

				//---- statusLabel ----
				statusLabel.setForeground(Color.darkGray);
				statusPanel.add(statusLabel, CC.xy(1, 1, CC.CENTER, CC.CENTER));
			}
			panel2.add(statusPanel, CC.xywh(1, 12, 3, 1));
		}
		add(panel2, CC.xy(1, 1, CC.FILL, CC.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JPanel panel2;
	protected JToolBar toolBar1;
	protected JButton openFolderButton;
	protected SimpleInternalFrame simpleInternalFrame1;
	protected JScrollPane fileTableContainer;
	protected JTable fileTable;
	protected JProgressBar processProgressBar;
	protected JPanel controlContainer;
	protected JComponent settingsLabel;
	protected JLabel catalogueFileLabel;
	protected JLabel catalogueFileValueLabel;
	protected JButton controlFileBrowseButton;
	protected JLabel targetDirectoryLabel;
	protected JLabel targetDirectoryValueLabel;
	protected JButton targetDirectoryBrowseButton;
	protected JButton processButton;
	protected JButton stopButton;
	protected JPanel statusPanel;
	protected JLabel statusLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
