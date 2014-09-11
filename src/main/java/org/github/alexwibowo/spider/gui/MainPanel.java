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
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		openFolderMenuItem = new JMenuItem();
		openCatalogueMenuItem = new JMenuItem();
		menuItem2 = new JMenuItem();
		toolBar1 = new JToolBar();
		processButton = new JButton();
		stopButton = new JButton();
		clearLogButton = new JButton();
		mainBodySplitPane = new JSplitPane();
		topLevelSplitPane = new JSplitPane();
		sourceFolderPanel = new JPanel();
		imageFilesContainer = new SimpleInternalFrame();
		fileTableContainer = new JScrollPane();
		fileTable = new JTable();
		targetDirectoryLabel = new JLabel();
		targetDirectoryValueLabel = new JTextField();
		targetDirectoryBrowseButton = new JButton();
		cataloguePanel = new JPanel();
		catalogueContainer = new SimpleInternalFrame();
		catalogueScrollPane = new JScrollPane();
		catalogueTable = new JTable();
		outputContainer = new SimpleInternalFrame();
		logScrollPane = new JScrollPane();
		logTextArea = new JTextArea();
		statusBarPanel = new JPanel();
		progressLabel = compFactory.createLabel("text");
		processProgressBar = new JProgressBar();

		//======== this ========
		setMinimumSize(new Dimension(850, 539));
		setPreferredSize(new Dimension(850, 610));
		setLayout(new FormLayout(
			"default:grow",
			"default, $lgap, default, $nlgap, fill:default:grow, $lgap, default"));

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

				//---- openCatalogueMenuItem ----
				openCatalogueMenuItem.setText("Open catalogue");
				menu1.add(openCatalogueMenuItem);
				menu1.addSeparator();

				//---- menuItem2 ----
				menuItem2.setText("Exit");
				menu1.add(menuItem2);
			}
			menuBar1.add(menu1);
		}
		add(menuBar1, CC.xy(1, 1));

		//======== toolBar1 ========
		{
			toolBar1.setFloatable(false);

			//---- processButton ----
			processButton.setText("Process");
			processButton.setIcon(null);
			toolBar1.add(processButton);

			//---- stopButton ----
			stopButton.setText("Stop");
			stopButton.setIcon(null);
			stopButton.setEnabled(false);
			toolBar1.add(stopButton);
			toolBar1.addSeparator();

			//---- clearLogButton ----
			clearLogButton.setText("Clear Logs");
			toolBar1.add(clearLogButton);
		}
		add(toolBar1, CC.xy(1, 3));

		//======== mainBodySplitPane ========
		{
			mainBodySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mainBodySplitPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			//======== topLevelSplitPane ========
			{
				topLevelSplitPane.setBorder(null);

				//======== sourceFolderPanel ========
				{
					sourceFolderPanel.setPreferredSize(new Dimension(580, 400));
					sourceFolderPanel.setMinimumSize(new Dimension(580, 400));
					sourceFolderPanel.setLayout(new FormLayout(
						"default, $lcgap, default:grow, $lcgap, default",
						"fill:default:grow, $lgap, default"));

					//======== imageFilesContainer ========
					{
						imageFilesContainer.setTitle("Images");
						imageFilesContainer.setMinimumSize(new Dimension(600, 55));
						Container imageFilesContainerContentPane = imageFilesContainer.getContentPane();
						imageFilesContainerContentPane.setLayout(new FormLayout(
							"default:grow",
							"fill:default:grow"));

						//======== fileTableContainer ========
						{

							//---- fileTable ----
							fileTable.setRowSelectionAllowed(false);
							fileTable.setGridColor(SystemColor.controlShadow);
							fileTableContainer.setViewportView(fileTable);
						}
						imageFilesContainerContentPane.add(fileTableContainer, CC.xy(1, 1, CC.DEFAULT, CC.FILL));
					}
					sourceFolderPanel.add(imageFilesContainer, CC.xywh(1, 1, 5, 1));

					//---- targetDirectoryLabel ----
					targetDirectoryLabel.setText("Save to");
					sourceFolderPanel.add(targetDirectoryLabel, CC.xy(1, 3));

					//---- targetDirectoryValueLabel ----
					targetDirectoryValueLabel.setEditable(false);
					sourceFolderPanel.add(targetDirectoryValueLabel, CC.xy(3, 3, CC.FILL, CC.DEFAULT));

					//---- targetDirectoryBrowseButton ----
					targetDirectoryBrowseButton.setText("Browse");
					targetDirectoryBrowseButton.setIcon(null);
					sourceFolderPanel.add(targetDirectoryBrowseButton, CC.xy(5, 3));
				}
				topLevelSplitPane.setLeftComponent(sourceFolderPanel);

				//======== cataloguePanel ========
				{
					cataloguePanel.setMinimumSize(new Dimension(200, 52));
					cataloguePanel.setPreferredSize(new Dimension(200, 448));
					cataloguePanel.setLayout(new FormLayout(
						"default:grow",
						"fill:default:grow"));

					//======== catalogueContainer ========
					{
						catalogueContainer.setTitle("Catalogue");
						Container catalogueContainerContentPane = catalogueContainer.getContentPane();
						catalogueContainerContentPane.setLayout(new FormLayout(
							"default:grow",
							"fill:default:grow"));

						//======== catalogueScrollPane ========
						{
							catalogueScrollPane.setViewportView(catalogueTable);
						}
						catalogueContainerContentPane.add(catalogueScrollPane, CC.xy(1, 1));
					}
					cataloguePanel.add(catalogueContainer, CC.xy(1, 1));
				}
				topLevelSplitPane.setRightComponent(cataloguePanel);
			}
			mainBodySplitPane.setTopComponent(topLevelSplitPane);

			//======== outputContainer ========
			{
				outputContainer.setTitle("Logs");
				Container outputContainerContentPane = outputContainer.getContentPane();
				outputContainerContentPane.setLayout(new FormLayout(
					"default:grow",
					"fill:default:grow"));

				//======== logScrollPane ========
				{

					//---- logTextArea ----
					logTextArea.setEditable(false);
					logScrollPane.setViewportView(logTextArea);
				}
				outputContainerContentPane.add(logScrollPane, CC.xy(1, 1, CC.FILL, CC.FILL));
			}
			mainBodySplitPane.setBottomComponent(outputContainer);
		}
		add(mainBodySplitPane, CC.xy(1, 5, CC.FILL, CC.FILL));

		//======== statusBarPanel ========
		{
			statusBarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		add(statusBarPanel, CC.xy(1, 7, CC.FILL, CC.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	protected JMenuBar menuBar1;
	protected JMenu menu1;
	protected JMenuItem openFolderMenuItem;
	protected JMenuItem openCatalogueMenuItem;
	protected JMenuItem menuItem2;
	protected JToolBar toolBar1;
	protected JButton processButton;
	protected JButton stopButton;
	protected JButton clearLogButton;
	protected JSplitPane mainBodySplitPane;
	protected JSplitPane topLevelSplitPane;
	protected JPanel sourceFolderPanel;
	protected SimpleInternalFrame imageFilesContainer;
	protected JScrollPane fileTableContainer;
	protected JTable fileTable;
	protected JLabel targetDirectoryLabel;
	protected JTextField targetDirectoryValueLabel;
	protected JButton targetDirectoryBrowseButton;
	protected JPanel cataloguePanel;
	protected SimpleInternalFrame catalogueContainer;
	protected JScrollPane catalogueScrollPane;
	protected JTable catalogueTable;
	protected SimpleInternalFrame outputContainer;
	protected JScrollPane logScrollPane;
	protected JTextArea logTextArea;
	protected JPanel statusBarPanel;
	protected JLabel progressLabel;
	protected JProgressBar processProgressBar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
