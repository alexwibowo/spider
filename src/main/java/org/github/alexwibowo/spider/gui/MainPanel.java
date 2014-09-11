/*
 * Created by JFormDesigner on Sat Aug 23 20:13:35 EST 2014
 */

package org.github.alexwibowo.spider.gui;

import java.awt.*;
import java.awt.event.*;
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
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		openFolderMenuItem = new JMenuItem();
		menuItem2 = new JMenuItem();
		simpleInternalFrame1 = new SimpleInternalFrame();
		fileTableContainer = new JScrollPane();
		fileTable = new JTable();
		controlContainer = new JPanel();
		catalogueFileLabel = new JLabel();
		catalogueFileValueLabel = new JTextField();
		controlFileBrowseButton = new JButton();
		targetDirectoryLabel = new JLabel();
		targetDirectoryValueLabel = new JTextField();
		targetDirectoryBrowseButton = new JButton();
		processButton = new JButton();
		stopButton = new JButton();
		outputContainer = new JPanel();
		logScrollPane = new JScrollPane();
		logTextArea = new JTextArea();
		clearLogButton = new JButton();
		statusBarPanel = new JPanel();
		progressLabel = compFactory.createLabel("text");
		processProgressBar = new JProgressBar();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"$lcgap, default, $lcgap, [100dlu,default]:grow, $lcgap",
				"default, $lgap, fill:default:grow, $lgap, default, $lgap, fill:default, $lgap"));

			//======== menuBar1 ========
			{

				//======== menu1 ========
				{
					menu1.setText("File");
					menu1.setMnemonic('F');

					//---- openFolderMenuItem ----
					openFolderMenuItem.setText("Open folder");
					openFolderMenuItem.setIcon(UIManager.getIcon("FileView.directoryIcon"));
					openFolderMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.META_MASK));
					menu1.add(openFolderMenuItem);
					menu1.addSeparator();

					//---- menuItem2 ----
					menuItem2.setText("Exit");
					menu1.add(menuItem2);
				}
				menuBar1.add(menu1);
			}
			panel2.add(menuBar1, CC.xywh(1, 1, 5, 1));

			//======== simpleInternalFrame1 ========
			{
				simpleInternalFrame1.setTitle("Images");
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
			panel2.add(simpleInternalFrame1, CC.xywh(2, 3, 3, 1, CC.FILL, CC.DEFAULT));

			//======== controlContainer ========
			{
				controlContainer.setBorder(new TitledBorder("Settings"));
				controlContainer.setLayout(new FormLayout(
					"right:default, $lcgap, center:default, $lcgap, [100dlu,default], $lcgap, center:default",
					"2*(default, $lgap), default"));

				//---- catalogueFileLabel ----
				catalogueFileLabel.setText("Catalogue File");
				controlContainer.add(catalogueFileLabel, CC.xy(1, 1));

				//---- catalogueFileValueLabel ----
				catalogueFileValueLabel.setEditable(false);
				controlContainer.add(catalogueFileValueLabel, CC.xywh(3, 1, 3, 1, CC.FILL, CC.DEFAULT));

				//---- controlFileBrowseButton ----
				controlFileBrowseButton.setText("Browse");
				controlFileBrowseButton.setIcon(null);
				controlContainer.add(controlFileBrowseButton, CC.xy(7, 1));

				//---- targetDirectoryLabel ----
				targetDirectoryLabel.setText("Save to");
				controlContainer.add(targetDirectoryLabel, CC.xy(1, 3));

				//---- targetDirectoryValueLabel ----
				targetDirectoryValueLabel.setEditable(false);
				controlContainer.add(targetDirectoryValueLabel, CC.xywh(3, 3, 3, 1, CC.FILL, CC.DEFAULT));

				//---- targetDirectoryBrowseButton ----
				targetDirectoryBrowseButton.setText("Browse");
				targetDirectoryBrowseButton.setIcon(null);
				controlContainer.add(targetDirectoryBrowseButton, CC.xy(7, 3));

				//---- processButton ----
				processButton.setText("Process");
				processButton.setIcon(null);
				controlContainer.add(processButton, CC.xy(3, 5, CC.LEFT, CC.DEFAULT));

				//---- stopButton ----
				stopButton.setText("Stop");
				stopButton.setIcon(null);
				stopButton.setEnabled(false);
				controlContainer.add(stopButton, CC.xy(5, 5, CC.LEFT, CC.DEFAULT));
			}
			panel2.add(controlContainer, CC.xy(2, 5, CC.LEFT, CC.FILL));

			//======== outputContainer ========
			{
				outputContainer.setBorder(new TitledBorder("Logs"));
				outputContainer.setLayout(new FormLayout(
					"default, default:grow",
					"fill:default:grow, $nlgap, default"));

				//======== logScrollPane ========
				{

					//---- logTextArea ----
					logTextArea.setEditable(false);
					logScrollPane.setViewportView(logTextArea);
				}
				outputContainer.add(logScrollPane, CC.xywh(1, 1, 2, 1, CC.FILL, CC.DEFAULT));

				//---- clearLogButton ----
				clearLogButton.setText("Clear");
				outputContainer.add(clearLogButton, CC.xy(1, 3));
			}
			panel2.add(outputContainer, CC.xy(4, 5, CC.DEFAULT, CC.FILL));

			//======== statusBarPanel ========
			{
				statusBarPanel.setLayout(new FormLayout(
					"default, $lcgap, default:grow",
					"default"));

				//---- progressLabel ----
				progressLabel.setText("Progress");
				statusBarPanel.add(progressLabel, CC.xy(1, 1));

				//---- processProgressBar ----
				processProgressBar.setString("0");
				statusBarPanel.add(processProgressBar, CC.xy(3, 1));
			}
			panel2.add(statusBarPanel, CC.xywh(1, 7, 4, 1, CC.FILL, CC.DEFAULT));
		}
		add(panel2, CC.xy(1, 1, CC.FILL, CC.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JPanel panel2;
	protected JMenuBar menuBar1;
	protected JMenu menu1;
	protected JMenuItem openFolderMenuItem;
	protected JMenuItem menuItem2;
	protected SimpleInternalFrame simpleInternalFrame1;
	protected JScrollPane fileTableContainer;
	protected JTable fileTable;
	protected JPanel controlContainer;
	protected JLabel catalogueFileLabel;
	protected JTextField catalogueFileValueLabel;
	protected JButton controlFileBrowseButton;
	protected JLabel targetDirectoryLabel;
	protected JTextField targetDirectoryValueLabel;
	protected JButton targetDirectoryBrowseButton;
	protected JButton processButton;
	protected JButton stopButton;
	protected JPanel outputContainer;
	protected JScrollPane logScrollPane;
	protected JTextArea logTextArea;
	protected JButton clearLogButton;
	protected JPanel statusBarPanel;
	protected JLabel progressLabel;
	protected JProgressBar processProgressBar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
