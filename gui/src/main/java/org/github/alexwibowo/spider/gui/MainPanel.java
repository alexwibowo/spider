/*
 * Created by JFormDesigner on Sat Aug 23 20:13:35 EST 2014
 */

package org.github.alexwibowo.spider.gui;

import java.awt.*;
import java.awt.event.*;

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
        exitMenuItem = new JMenuItem();
        mainBodySplitPane = new JSplitPane();
        topLevelSplitPane = new JSplitPane();
        sourceFolderPanel = new JPanel();
        imageFilesContainer = new SimpleInternalFrame();
        fileTableContainer = new JScrollPane();
        fileTable = new JTable();
        targetDirectoryLabel = new JLabel();
        targetDirectoryValueLabel = new JTextField();
        targetDirectoryBrowseButton = new JButton();
        processButton = new JButton();
        stopButton = new JButton();
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
        logsInternalFrameToolbar = new JToolBar();
        clearLogButton = new JButton();
        imageInternalFrameToolbar = new JToolBar();
        toolbarOpenFolderButton = new JButton();
        catalogueInternalFrameToolbar = new JToolBar();
        toolbarOpenCatalogueButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(850, 539));
        setPreferredSize(new Dimension(850, 610));
        setLayout(new FormLayout(
            "default:grow",
            "default, $lgap, fill:default:grow, $lgap, default"));

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

                //---- exitMenuItem ----
                exitMenuItem.setText("Exit");
                exitMenuItem.setIcon(new ImageIcon(getClass().getResource("/exit.png")));
                menu1.add(exitMenuItem);
            }
            menuBar1.add(menu1);
        }
        add(menuBar1, CC.xy(1, 1));

        //======== mainBodySplitPane ========
        {
            mainBodySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            mainBodySplitPane.setBorder(new EmptyBorder(5, 5, 5, 5));

            //======== topLevelSplitPane ========
            {
                topLevelSplitPane.setBorder(null);
                topLevelSplitPane.setMinimumSize(new Dimension(787, 300));
                topLevelSplitPane.setPreferredSize(new Dimension(787, 300));

                //======== sourceFolderPanel ========
                {
                    sourceFolderPanel.setPreferredSize(new Dimension(580, 400));
                    sourceFolderPanel.setMinimumSize(new Dimension(580, 400));
                    sourceFolderPanel.setLayout(new FormLayout(
                        "default, $lcgap, default:grow, 3*($lcgap, default)",
                        "fill:default:grow, $lgap, default"));

                    //======== imageFilesContainer ========
                    {
                        imageFilesContainer.setTitle("Images");
                        imageFilesContainer.setMinimumSize(new Dimension(600, 55));
                        imageFilesContainer.setToolBar(imageInternalFrameToolbar);
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
                    sourceFolderPanel.add(imageFilesContainer, CC.xywh(1, 1, 9, 1));

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

                    //---- processButton ----
                    processButton.setIcon(new ImageIcon(getClass().getResource("/go-next.png")));
                    processButton.setBorder(Borders.createEmptyBorder("2dlu, 2dlu, 2dlu, 2dlu"));
                    processButton.setText("Start Processing");
                    sourceFolderPanel.add(processButton, CC.xy(7, 3));

                    //---- stopButton ----
                    stopButton.setIcon(new ImageIcon(getClass().getResource("/stop.png")));
                    stopButton.setEnabled(false);
                    stopButton.setBorder(Borders.createEmptyBorder("2dlu, 2dlu, 2dlu, 2dlu"));
                    stopButton.setText("Stop Processing");
                    sourceFolderPanel.add(stopButton, CC.xy(9, 3));
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
                        catalogueContainer.setToolBar(catalogueInternalFrameToolbar);
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
                outputContainer.setToolBar(logsInternalFrameToolbar);
                outputContainer.setMinimumSize(new Dimension(66, 125));
                outputContainer.setPreferredSize(new Dimension(66, 125));
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
        add(mainBodySplitPane, CC.xy(1, 3, CC.FILL, CC.FILL));

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
        add(statusBarPanel, CC.xy(1, 5, CC.FILL, CC.DEFAULT));

        //======== logsInternalFrameToolbar ========
        {

            //---- clearLogButton ----
            clearLogButton.setIcon(new ImageIcon(getClass().getResource("/clear.png")));
            clearLogButton.setBorder(Borders.createEmptyBorder("2dlu, 2dlu, 2dlu, 2dlu"));
            logsInternalFrameToolbar.add(clearLogButton);
        }

        //======== imageInternalFrameToolbar ========
        {

            //---- toolbarOpenFolderButton ----
            toolbarOpenFolderButton.setIcon(new ImageIcon(getClass().getResource("/directory.png")));
            toolbarOpenFolderButton.setToolTipText("Open folder that contains the images to be processed");
            toolbarOpenFolderButton.setBorder(Borders.createEmptyBorder("2dlu, 2dlu, 2dlu, 2dlu"));
            imageInternalFrameToolbar.add(toolbarOpenFolderButton);
        }

        //======== catalogueInternalFrameToolbar ========
        {

            //---- toolbarOpenCatalogueButton ----
            toolbarOpenCatalogueButton.setIcon(new ImageIcon(getClass().getResource("/catalogue.png")));
            toolbarOpenCatalogueButton.setToolTipText("Open catalogue file containing the barcode and the corresponding product name");
            toolbarOpenCatalogueButton.setBorder(Borders.createEmptyBorder("2dlu, 2dlu, 2dlu, 2dlu"));
            catalogueInternalFrameToolbar.add(toolbarOpenCatalogueButton);
        }
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    protected JMenuBar menuBar1;
    protected JMenu menu1;
    protected JMenuItem openFolderMenuItem;
    protected JMenuItem openCatalogueMenuItem;
    protected JMenuItem exitMenuItem;
    protected JSplitPane mainBodySplitPane;
    protected JSplitPane topLevelSplitPane;
    protected JPanel sourceFolderPanel;
    protected SimpleInternalFrame imageFilesContainer;
    protected JScrollPane fileTableContainer;
    protected JTable fileTable;
    protected JLabel targetDirectoryLabel;
    protected JTextField targetDirectoryValueLabel;
    protected JButton targetDirectoryBrowseButton;
    protected JButton processButton;
    protected JButton stopButton;
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
    protected JToolBar logsInternalFrameToolbar;
    protected JButton clearLogButton;
    protected JToolBar imageInternalFrameToolbar;
    protected JButton toolbarOpenFolderButton;
    protected JToolBar catalogueInternalFrameToolbar;
    protected JButton toolbarOpenCatalogueButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables


    public JProgressBar getProcessProgressBar() {
        return processProgressBar;
    }

    public JButton getProcessButton() {
        return processButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
}
