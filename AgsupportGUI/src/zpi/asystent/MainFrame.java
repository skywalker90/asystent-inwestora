package zpi.asystent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.agsupport.core.service.communication.*;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainFrame extends JFrame implements Observer {

	private JPanel contentPane;
	private JTextField fromField;
	private JTextField toField;
	final JList list;
	static JFreeChart chart;
	static XYPlot plot;
	JComboBox comboBox;
	
	final ControllerInterface controller;

	/**
	 * Create the frame.
	 */
	public MainFrame(final ControllerInterface controller) {
		
		this.controller = controller;
		
		setTitle("Asystent Inwestora Giełdowego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmZakocz = new JMenuItem("Zakończ");
		mntmZakocz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Pobierz giełdy");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.getStockMarketList();
				comboBox.setEnabled(false);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmPobierzInstrPochodne = new JMenuItem("Pobierz instr. pochodne");
		mntmPobierzInstrPochodne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.getDerivativeList();
				comboBox.setEnabled(true);
			}
		});
		mnNewMenu.add(mntmPobierzInstrPochodne);
		mnNewMenu.add(mntmZakocz);
		
		JMenu mnNewMenu_1 = new JMenu("Pomoc");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmOProgramie = new JMenuItem("O programie");
		mntmOProgramie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        AboutFrame about = new AboutFrame();
		        about.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmOProgramie);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(list.getSelectedValue() instanceof JSONDerivative) {
					if(!list.getValueIsAdjusting())
						controller.getDerivativeExpiredDate((JSONDerivative)list.getSelectedValue());
				}
			}
		});

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//contentPane.add(list, BorderLayout.WEST);
		
		JScrollPane listContainer = new JScrollPane(list);
		listContainer.setBackground(Color.WHITE);
		listContainer.setPreferredSize(new Dimension(170, 0));
		contentPane.add(listContainer, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout());
		
		JPanel factorsPanel = new JPanel();
		centerPanel.add(factorsPanel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_2 = new JLabel("TUTAJ STATYSTYKA!!!");
		factorsPanel.add(lblNewLabel_2);
		
		JPanel rangePanel = new JPanel();
		centerPanel.add(rangePanel, BorderLayout.NORTH);
		rangePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Zakres Pobranych Danych");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		rangePanel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel periodPanel = new JPanel();
		rangePanel.add(periodPanel, BorderLayout.SOUTH);
		periodPanel.setPreferredSize(new Dimension(200, 35));
		periodPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(52dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(52dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(61dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblOd = new JLabel("Od");
		periodPanel.add(lblOd, "2, 2, right, default");
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date dateNow = new Date();
		dateNow.setMinutes(0);
		dateNow.setHours(0);
		StringBuilder DateFromString = new StringBuilder( format.format( dateNow ) );
		dateNow.setTime(dateNow.getTime() + 1*24*60*60*1000);
		StringBuilder DateToString = new StringBuilder( format.format( dateNow ) );
		
		fromField = new JTextField();
		periodPanel.add(fromField, "4, 2, fill, default");
		fromField.setColumns(10);
		fromField.setText(DateFromString.toString());
		
		JLabel lblDo = new JLabel("Do");
		periodPanel.add(lblDo, "6, 2, right, default");
		
		toField = new JTextField();
		periodPanel.add(toField, "8, 2, fill, default");
		toField.setColumns(10);
		toField.setText(DateToString.toString());
		
		JButton btnPobierz = new JButton("Pobierz");
		btnPobierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object sv = list.getSelectedValue();
				
				if(sv instanceof JSONStockMarket) {
					controller.getStockIndexList((JSONStockMarket)list
						.getSelectedValue(), fromField.getText(), toField.getText());
				}
				else if(sv instanceof JSONDerivative) {
					controller.getDerivativeValuesList((JSONDerivative)list.getSelectedValue(), fromField.getText(), 
							toField.getText(), comboBox.getSelectedItem().toString());
				}
					
			}
		});
		
		JLabel lblWygasa = new JLabel("Wygasa");
		periodPanel.add(lblWygasa, "10, 2, right, default");
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBackground(Color.WHITE);
		comboBox.setPreferredSize(new Dimension(200, 20));
		periodPanel.add(comboBox, "12, 2, fill, default");
		periodPanel.add(btnPobierz, "14, 2");
		
		JFreeChart chart = createChart("");   
		chart.setBackgroundPaint(new java.awt.Color(238, 238, 238));
        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        centerPanel.add(panel);
	}
	
	private static JFreeChart createChart(String name) {   
	       
        //String title = "Giełda jakaś tam co nie";   
        chart = ChartFactory.createTimeSeriesChart(   
            name,    
            "Okres",    
            "Cena",   
            null,    
            true,   
            true,   
            false   
        );   
        plot = chart.getXYPlot();   
        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();   
        rangeAxis1.setLowerMargin(0.40);  // to leave room for volume bars   
        DecimalFormat format = new DecimalFormat("00.00");   
        rangeAxis1.setNumberFormatOverride(format);   
   
        XYItemRenderer renderer1 = plot.getRenderer();   
        renderer1.setToolTipGenerator(   
            new StandardXYToolTipGenerator(   
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,   
                new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")   
            )   
        );   
        return chart;   
   
    }


	@Override
	public void setStockList(final ListOfStockMarket stockMarkets) {
		
		list.setModel(new AbstractListModel() {
			
			List<JSONStockMarket> values = stockMarkets.getStockMarkets();
			
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});

	}

	@Override
	public void updateStockIndexes(ListOfStockIndex indexes) {
		chart.setTitle(list.getSelectedValue().toString());
			
		TimeSeries series1 = new TimeSeries("Cena", Minute.class);
		
		for(JSONStockIndex si : indexes.getStockIndexes() ) {
			series1.add(new Minute(si.getDateOfAdd()), si.getPrice());
		}
		
		plot.setDataset(new TimeSeriesCollection(series1));
	}

	@Override
	public void updateDerivativeList(final ListOfDerivative derivatives) {
			
		list.setModel(new AbstractListModel() {
			
			List<JSONDerivative> values = derivatives.getDerivatives();
			
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});		
	}

	@Override
	public void updateExpiredDateList(ListOfDate expiredDates) {
		
		comboBox.setModel(new DefaultComboBoxModel(expiredDates.getDates().toArray()));
		
	}

	@Override
	public void updateDerivativeValues(ListOfDerivativeValue derivativeValues) {
		chart.setTitle(list.getSelectedValue().toString());
		
		TimeSeries series1 = new TimeSeries("Cena", Minute.class);
		
		for(JSONDerivativeValue si : derivativeValues.getDerivativeValues() ) {
			series1.add(new Minute(si.getDateOfAdd()), si.getPrice());
		}
		
		plot.setDataset(new TimeSeriesCollection(series1));
		
	}
}
